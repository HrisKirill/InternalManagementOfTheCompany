package com.internalmanagementofthecompany.controllers.project;

import com.internalmanagementofthecompany.dto.project.ProjectDTO;
import com.internalmanagementofthecompany.entities.project.Project;
import com.internalmanagementofthecompany.dao.interfaces.IProjectDao;
import com.internalmanagementofthecompany.mapper.ProjectToProjectDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private final IProjectDao dao;

    @Autowired
    public ProjectController(IProjectDao dao) {
        this.dao = dao;
    }

    @PostMapping
    ResponseEntity<ProjectDTO> addProject(@RequestBody Project project) {
        Project projectDAO = dao.create(project);
        return new ResponseEntity<>(ProjectToProjectDTOMapper.toDTO(projectDAO), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<ProjectDTO> getEmployeeById(@PathVariable Long id) {
        Project projectDAO = dao.getById(id);
        return new ResponseEntity<>(ProjectToProjectDTOMapper.toDTO(projectDAO), HttpStatus.OK);
    }

    @PutMapping
    ResponseEntity<ProjectDTO> updateEmployee(@RequestBody Project project) {
        Project projectDAO = dao.update(project);
        return new ResponseEntity<>(ProjectToProjectDTOMapper.toDTO(projectDAO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        return new ResponseEntity<>(dao.delete(id), HttpStatus.OK);
    }
}
