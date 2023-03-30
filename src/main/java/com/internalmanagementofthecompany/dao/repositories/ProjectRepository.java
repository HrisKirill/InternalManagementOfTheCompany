package com.internalmanagementofthecompany.dao.repositories;

import com.internalmanagementofthecompany.dao.entities.project.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {
}
