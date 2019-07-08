package com.templates.springproject.responses;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

public class OperationsResponses {

    public static String sendError(String message) throws JSONException {

        JSONObject jo = new JSONObject();
        jo.put("error",message);

        return jo.toString();
    }

    public static String sendSuccess() throws JSONException{

        JSONObject response = new JSONObject();
        response.put("statut", "200");
        response.put("message", "user updated successfully");

        return response.toString();
    }
}
