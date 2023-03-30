package com.internalmanagementofthecompany.entities.employee;

import com.internalmanagementofthecompany.entities.project.Project;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "employees")
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Specialty specialty;

    @Column(nullable = false)
    private Level level;

    @Column(nullable = false)
    private String typeOfSpecialty;

    @ManyToMany(mappedBy = "employees")
    @EqualsAndHashCode.Exclude
    private Set<Project> projects = new HashSet<>();
}
