package com.internalmanagementofthecompany.dao.services;

import com.internalmanagementofthecompany.entities.project.Project;
import com.internalmanagementofthecompany.dao.repositories.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    ProjectRepository repository = mock(ProjectRepository.class);
    ProjectService service = new ProjectService(repository);

    private Project getTestProject() {
        return new Project(1L, "TestProject", Collections.emptySet());
    }

    @BeforeEach
    void init_mocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTest() {
        when(repository.findById(getTestProject().getId())).thenReturn(Optional.empty());
        when(repository.save(getTestProject())).thenReturn(getTestProject());
        assertEquals(getTestProject(), service.create(getTestProject()));
    }

    @Test
    void createBadTest() {
        when(repository.findById(1L)).thenReturn(Optional.of(getTestProject()));
        assertThrows(IllegalArgumentException.class, () -> service.create(getTestProject()));
    }

    @Test
    void updateTest() {
        when(repository.findById(getTestProject().getId())).thenReturn(Optional.of(getTestProject()));
        when(repository.save(getTestProject())).thenReturn(getTestProject());
        assertEquals(getTestProject(), service.update(getTestProject()));
    }

    @Test
    void updateBadTest() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> service.update(getTestProject()));
    }

    @Test
    void getByIdTest() {
        when(repository.findById(getTestProject().getId())).thenReturn(Optional.of(getTestProject()));
        assertEquals(getTestProject(), service.getById(getTestProject().getId()));
    }

    @Test
    void findByIdBadTest() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> service.getById(getTestProject().getId()));
    }

    @Test
    void deleteTest() {
        when(repository.findById(getTestProject().getId())).thenReturn(Optional.of(getTestProject()));
        assertDoesNotThrow(() -> service.delete(getTestProject().getId()));
    }

    @Test
    void deleteBadTest() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> service.delete(getTestProject().getId()));
    }
}