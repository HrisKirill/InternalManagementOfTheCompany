package com.internalmanagementofthecompany.mapper;

import com.internalmanagementofthecompany.dto.project.ProjectDTO;
import com.internalmanagementofthecompany.entities.project.Project;

import java.util.Set;
import java.util.stream.Collectors;

public class ProjectToProjectDTOMapper {
    public static Set<ProjectDTO> toDTOs(Set<Project> projects) {
        return projects.stream().map(project -> toDTO(project)).collect(Collectors.toSet());
    }

    public static ProjectDTO toDTO(Project project) {
        return ProjectDTO.builder()
                .id(project.getId())
                .name(project.getName())
                .build();
    }
}
