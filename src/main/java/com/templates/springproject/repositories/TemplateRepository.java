package com.templates.springproject.repositories;

import com.templates.springproject.entities.Template;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateRepository extends JpaRepository<Template,Long> {


}
