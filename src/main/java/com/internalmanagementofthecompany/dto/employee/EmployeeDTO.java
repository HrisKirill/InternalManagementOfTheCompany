package com.internalmanagementofthecompany.dto.employee;

import com.internalmanagementofthecompany.dto.project.ProjectDTO;
import com.internalmanagementofthecompany.entities.employee.Level;
import com.internalmanagementofthecompany.entities.employee.Specialty;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String name;
    private Specialty specialty;
    private Level level;
    private String typeOfSpecialty;
    private Set<ProjectDTO> projects = new HashSet<>();
}
