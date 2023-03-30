package com.internalmanagementofthecompany.dao.services;

import com.internalmanagementofthecompany.entities.project.Project;
import com.internalmanagementofthecompany.dao.interfaces.IProjectDao;
import com.internalmanagementofthecompany.dao.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProjectService implements IProjectDao {

    private final ProjectRepository repository;

    @Autowired
    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public Project create(Project entity) {
        Optional<Project> projectForCreateOptional = repository.findById(entity.getId());
        if (projectForCreateOptional.isEmpty()) {
            return repository.save(entity);
        } else {
            throw new IllegalArgumentException("Project is already created");
        }
    }

    @Override
    public Project update(Project entity) {
        Optional<Project> projectForUpdateOptional = repository.findById(entity.getId());
        if (projectForUpdateOptional.isPresent()) {
            Project target = projectForUpdateOptional.get();
            target.setName(entity.getName());
            target.setEmployees(entity.getEmployees());

            repository.save(target);
            return target;
        } else {
            throw new IllegalArgumentException("Unable to update project");
        }
    }

    @Override
    public Project getById(Long id) {
        Optional<Project> projectGettingByIdOptional = repository.findById(id);
        if (projectGettingByIdOptional.isPresent()) {
            return projectGettingByIdOptional.get();
        } else {
            throw new IllegalArgumentException("Unable to get project");
        }
    }

    @Override
    public String delete(Long entityId) {
        Optional<Project> projectOptional = repository.findById(entityId);
        if (projectOptional.isPresent()) {
            repository.delete(projectOptional.get());
            return "Project with id:" + entityId + " deleted successfully";
        } else {
            throw new IllegalArgumentException("Unable to delete project");
        }
    }
}
