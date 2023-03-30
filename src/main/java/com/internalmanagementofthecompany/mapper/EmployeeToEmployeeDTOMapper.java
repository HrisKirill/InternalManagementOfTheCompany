package com.internalmanagementofthecompany.mapper;

import com.internalmanagementofthecompany.dto.employee.EmployeeDTO;
import com.internalmanagementofthecompany.entities.employee.Employee;

public class EmployeeToEmployeeDTOMapper {
    public static EmployeeDTO toDTO(Employee employee) {
        return EmployeeDTO.builder()
                .id(employee.getId())
                .name(employee.getName())
                .level(employee.getLevel())
                .specialty(employee.getSpecialty())
                .typeOfSpecialty(employee.getTypeOfSpecialty())
                .projects(ProjectToProjectDTOMapper.toDTOs(employee.getProjects()))
                .build();
    }
}
