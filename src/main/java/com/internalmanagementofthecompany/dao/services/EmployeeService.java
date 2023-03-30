package com.internalmanagementofthecompany.dao.services;

import com.internalmanagementofthecompany.dto.employee.EmployeeDTO;
import com.internalmanagementofthecompany.entities.employee.Employee;
import com.internalmanagementofthecompany.dao.interfaces.IEmployeeDao;
import com.internalmanagementofthecompany.dao.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeDao {

    private final EmployeeRepository employeeRepository;


    @Autowired
    public EmployeeService(EmployeeRepository repository) {
        this.employeeRepository = repository;
    }

    @Override
    public Employee create(Employee entity) {
        Optional<Employee> employeeForCreateOptional = employeeRepository.findById(entity.getId());
        if (employeeForCreateOptional.isEmpty()) {
            return employeeRepository.save(entity);
        } else {
            throw new IllegalArgumentException("Employee is already created");
        }
    }

    @Override
    public Employee update(Employee entity) {
        Optional<Employee> employeeForUpdateOptional = employeeRepository.findById(entity.getId());
        if (employeeForUpdateOptional.isPresent()) {
            Employee target = employeeForUpdateOptional.get();
            target.setName(entity.getName());
            target.setLevel(entity.getLevel());
            target.setSpecialty(entity.getSpecialty());
            target.setTypeOfSpecialty(entity.getTypeOfSpecialty());
            target.setProjects(entity.getProjects());

            employeeRepository.save(target);
            return target;
        } else {
            throw new IllegalArgumentException("Unable to update employee");
        }
    }

    @Override
    public Employee getById(Long id) {
        Optional<Employee> employeeGettingByIdOptional = employeeRepository.findById(id);
        if (employeeGettingByIdOptional.isPresent()) {
            return employeeGettingByIdOptional.get();
        } else {
            throw new IllegalArgumentException("Unable to get employee");
        }
    }

    @Override
    public String delete(Long entityId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(entityId);
        if (employeeOptional.isPresent()) {
            employeeRepository.delete(employeeOptional.get());
            return "Employee with id:" + entityId + " deleted successfully";
        } else {
            throw new IllegalArgumentException("Unable to delete employee");
        }
    }


}
