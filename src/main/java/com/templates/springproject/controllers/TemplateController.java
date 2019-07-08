package com.templates.springproject.controllers;

import com.templates.springproject.entities.Template;
import com.templates.springproject.repositories.TemplateRepository;
import com.templates.springproject.requests.TemplateRequest;
import com.templates.springproject.responses.UploadFileResponse;
import com.templates.springproject.services.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.templates.springproject.responses.OperationsResponses.*;

@RestController
public class TemplateController {


    @Autowired
    private TemplateRepository templateRepository;

    @Autowired
    private FileStorageService fileStorageService;


    @GetMapping("/client/templates")
    public List<Template> getTemplates(){
        return templateRepository.findAll();
    }

    @PostMapping("/admin/template/add")
    public UploadFileResponse addTemplate(@RequestParam("file") MultipartFile file) {

        String filePath = fileStorageService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile?fileName=")
                .path(StringUtils.cleanPath(file.getOriginalFilename()))
                .toUriString();

        return new UploadFileResponse(filePath, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PutMapping("/admin/template/updatePath")
    public String updatePath(@RequestBody TemplateRequest templateRequest) throws JSONException {

        Optional<Template> template = templateRepository.findById(templateRequest.getId().longValue());
        if(template.isPresent()){
            template.get().setPath(templateRequest.getPath());
            return sendSuccess();
        }
        return sendError("Template with id " + templateRequest.getId() + " doesnt exist" );

    }

    @GetMapping("/downloadFile")
    public ResponseEntity<Resource> getTemplate(@RequestParam String fileName,HttpServletRequest request,HttpServletResponse response) throws IOException {

        return new ResponseEntity<>(fileStorageService.loadFileAsResource(fileName),prepareHeaderForFileReturn(fileName, request, response), HttpStatus.OK);
    }

    private HttpHeaders prepareHeaderForFileReturn(String fileName, HttpServletRequest request,
                                                   HttpServletResponse response) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, getContentTypeForAttachment(fileName));
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        return headers;
    }

    private String getContentTypeForAttachment(String fileName) {

        return fileName.endsWith("pptx") ? "application/pptx" : fileName.endsWith("ppt")? "application/ppt" : "";
    }


}
