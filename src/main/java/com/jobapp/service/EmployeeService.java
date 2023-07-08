package com.jobapp.service;

import com.jobapp.entity.Employee;
import com.jobapp.request.EmployeePostRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface EmployeeService {

    List<Employee> getAllEmployee(Optional<Long> jobId);

    Employee getOneEmployee(long id);

    Employee createEmployee(EmployeePostRequest employeePostRequest);
}
