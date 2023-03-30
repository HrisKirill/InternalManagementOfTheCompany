package com.internalmanagementofthecompany.dao.services;

import com.internalmanagementofthecompany.dao.entities.employee.Employee;
import com.internalmanagementofthecompany.dao.interfaces.IEmployeeDao;
import com.internalmanagementofthecompany.dao.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeDao {

    private final EmployeeRepository repository;

    @Autowired
    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Employee create(Employee entity) {
        Optional<Employee> employeeForCreateOptional = repository.findById(entity.getId());
        if (employeeForCreateOptional.isEmpty()) {
            return repository.save(entity);
        } else {
            throw new IllegalArgumentException("Employee is already created");
        }
    }

    @Override
    public Employee update(Employee entity) {
        Optional<Employee> employeeForUpdateOptional = repository.findById(entity.getId());
        if (employeeForUpdateOptional.isPresent()) {
            Employee target = employeeForUpdateOptional.get();
            target.setName(entity.getName());
            target.setLevel(entity.getLevel());
            target.setSpecialty(entity.getSpecialty());
            target.setTypeOfSpecialty(entity.getTypeOfSpecialty());
            target.setProjects(entity.getProjects());

            repository.save(target);
            return target;
        } else {
            throw new IllegalArgumentException("Unable to update employee");
        }
    }

    @Override
    public Optional<Employee> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public String delete(Long entityId) {
        Optional<Employee> employeeOptional = repository.findById(entityId);
        if (employeeOptional.isPresent()) {
            repository.delete(employeeOptional.get());
            return "Employee with id:" + entityId + " deleted successfully";
        } else {
            throw new IllegalArgumentException("Unable to delete employee");
        }
    }
}
