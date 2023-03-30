package com.internalmanagementofthecompany.dao.services;

import com.internalmanagementofthecompany.entities.employee.Employee;
import com.internalmanagementofthecompany.entities.employee.Level;
import com.internalmanagementofthecompany.entities.employee.Specialty;
import com.internalmanagementofthecompany.dao.repositories.EmployeeRepository;
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
class EmployeeServiceTest {
    EmployeeRepository repository = mock(EmployeeRepository.class);
    EmployeeService service = new EmployeeService(repository);

    private Employee getTestEmployee(){
        return Employee.builder()
                .id(1L)
                .level(Level.JUN)
                .name("Test")
                .typeOfSpecialty("DevOps")
                .specialty(Specialty.MANAGER)
                .projects(Collections.emptySet())
                .build();

    }
    @BeforeEach
    void init_mocks() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void createTest() {
        when(repository.findById(getTestEmployee().getId())).thenReturn(Optional.empty());
        when(repository.save(getTestEmployee())).thenReturn(getTestEmployee());
        assertEquals(getTestEmployee(), service.create(getTestEmployee()));
    }

    @Test
    void createBadTest() {
        when(repository.findById(1L)).thenReturn(Optional.of(getTestEmployee()));
        assertThrows(IllegalArgumentException.class, () -> service.create(getTestEmployee()));
    }

    @Test
    void updateTest() {
        when(repository.findById(getTestEmployee().getId())).thenReturn(Optional.of(getTestEmployee()));
        when(repository.save(getTestEmployee())).thenReturn(getTestEmployee());
        assertEquals(getTestEmployee(), service.update(getTestEmployee()));
    }

    @Test
    void updateBadTest() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> service.update(getTestEmployee()));
    }

    @Test
    void getByIdTest() {
        when(repository.findById(getTestEmployee().getId())).thenReturn(Optional.of(getTestEmployee()));
        assertEquals(getTestEmployee(), service.getById(getTestEmployee().getId()));
    }

    @Test
    void findByIdBadTest() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> service.getById(getTestEmployee().getId()));
    }

    @Test
    void deleteTest() {
        when(repository.findById(getTestEmployee().getId())).thenReturn(Optional.of(getTestEmployee()));
        assertDoesNotThrow(() -> service.delete(getTestEmployee().getId()));
    }

    @Test
    void deleteBadTest() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> service.delete(getTestEmployee().getId()));
    }
}