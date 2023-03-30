package com.internalmanagementofthecompany.controllers.project;

import com.internalmanagementofthecompany.dao.entities.project.Project;
import com.internalmanagementofthecompany.dao.interfaces.IProjectDao;
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
    ResponseEntity<Project> addEvent(@RequestBody Project project) {
        return new ResponseEntity<>(dao.create(project), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Project> getEmployeeById(@PathVariable Long id) {
        return new ResponseEntity<>(dao.getById(id).get(), HttpStatus.OK);
    }

    @PutMapping
    ResponseEntity<Project> updateEmployee(@RequestBody Project project) {
        return new ResponseEntity<>(dao.update(project), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        return new ResponseEntity<>(dao.delete(id), HttpStatus.OK);
    }
}
