package com.jobapp.controller;

import com.jobapp.entity.Employee;
import com.jobapp.request.EmployeePostRequest;
import com.jobapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployee(@RequestParam Optional<Long> jobId) {
        return ResponseEntity.ok(employeeService.getAllEmployee(jobId));
    }

    @GetMapping("/{Id}")
    public ResponseEntity<Employee> getOneEmployee(@PathVariable long Id) {
        return new ResponseEntity<>(employeeService.getOneEmployee(Id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeePostRequest employeePostRequest) {
        System.out.println("Controller class");
        return new ResponseEntity<>(employeeService.createEmployee(employeePostRequest), HttpStatus.CREATED);
    }

}
