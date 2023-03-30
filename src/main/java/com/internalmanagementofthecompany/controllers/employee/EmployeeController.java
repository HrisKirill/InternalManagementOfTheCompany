package com.internalmanagementofthecompany.controllers.employee;

import com.internalmanagementofthecompany.dao.entities.employee.Employee;
import com.internalmanagementofthecompany.dao.interfaces.IEmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final IEmployeeDao dao;

    @Autowired
    public EmployeeController(IEmployeeDao dao) {
        this.dao = dao;
    }


    @PostMapping
    ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(dao.create(employee), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        return new ResponseEntity<>(dao.getById(id).get(), HttpStatus.OK);
    }

    @PutMapping
    ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(dao.update(employee), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        return new ResponseEntity<>(dao.delete(id), HttpStatus.OK);
    }



}
