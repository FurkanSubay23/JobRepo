package com.jobapp.repository;

import com.jobapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeDao extends JpaRepository<Employee, Long> {

    public List<Employee> findByJobIdOrderByUserNameAsc(Long JobId);

    public List<Employee> findByJobId(Long jobId);
}
