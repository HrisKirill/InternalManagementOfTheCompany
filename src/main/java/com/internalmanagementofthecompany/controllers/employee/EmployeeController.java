package com.internalmanagementofthecompany.controllers.employee;

import com.internalmanagementofthecompany.dao.interfaces.IEmployeeDao;
import com.internalmanagementofthecompany.dto.employee.EmployeeDTO;
import com.internalmanagementofthecompany.entities.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.internalmanagementofthecompany.mapper.EmployeeToEmployeeDTOMapper.toDTO;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final IEmployeeDao dao;

    @Autowired
    public EmployeeController(IEmployeeDao dao) {
        this.dao = dao;
    }


    @PostMapping
    ResponseEntity<EmployeeDTO> addEmployee(@RequestBody Employee employee) {
        Employee employeeDao = dao.create(employee);
        return new ResponseEntity<>(toDTO(employeeDao), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        Employee employeeDao = dao.getById(id);
        return new ResponseEntity<>(toDTO(employeeDao), HttpStatus.OK);
    }

    @PutMapping
    ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody Employee employee) {
        Employee employeeDao = dao.update(employee);
        return new ResponseEntity<>(toDTO(employeeDao), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        return new ResponseEntity<>(dao.delete(id), HttpStatus.NO_CONTENT);
    }


}
