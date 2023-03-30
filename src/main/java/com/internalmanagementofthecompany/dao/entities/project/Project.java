package com.internalmanagementofthecompany.dao.entities.project;

import com.internalmanagementofthecompany.dao.entities.employee.Employee;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity(name = "PROJECTS")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "projects", cascade = CascadeType.ALL)
    private Set<Employee> employees;
}
