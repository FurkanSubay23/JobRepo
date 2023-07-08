package com.jobapp.service;

import com.jobapp.entity.Employee;
import com.jobapp.entity.Job;
import com.jobapp.exception.ErrorHandling;
import com.jobapp.repository.EmployeeDao;
import com.jobapp.request.EmployeePostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class EmployeeServiceImp implements EmployeeService {

    EmployeeDao employeeDao;
    JobService jobService;

    Logger logger = Logger.getLogger(EmployeeServiceImp.class.getName());

    @Autowired
    public EmployeeServiceImp(EmployeeDao employeeDao, JobService jobService) {
        this.employeeDao = employeeDao;
        this.jobService = jobService;
    }

    @Override
    public List<Employee> getAllEmployee(Optional<Long> jobId) {
        List<Employee> list;
        if (jobId.isPresent()) {
            logger.info("Get All Employee By JobId");
            list = employeeDao.findByJobId(jobId.get());
            list.sort(Comparator.comparing(Employee::getUserName));
//            return list;
//            return employeeDao.findByJobIdOrderByUserNameAsc(jobId.get());
        } else {
            logger.info("Get All Employeee");
            list = employeeDao.findAll(Sort.by("userName"));

        }
        return list;

    }

    @Override
    public Employee getOneEmployee(long id) {
        logger.info("Get One Job Baybe");
        return employeeDao.findById(id)
                .orElseThrow(() -> new ErrorHandling("Can not find Employee By Id"));
    }

    @Override
    public Employee createEmployee(EmployeePostRequest employeePostRequest) {
        logger.info("Creating Employeee");
        Optional<Job> job = Optional.ofNullable(jobService.getOneJob(employeePostRequest.getJobId()));
        Employee employee = new Employee();
        if (job.isPresent()) {
            logger.info("Job found");
            employee.setJob(job.get());
        }
        employee.setUserName(employeePostRequest.getUserName());
        employee.setLastName(employeePostRequest.getLastName());
        employee.setEmail(employeePostRequest.getEmail());
        employee.setAge(employeePostRequest.getAge());
        employee.setCountry(employeePostRequest.getCountry());
        if (employeePostRequest.getStartDate() != null) {
            logger.info("Start date receive by end user");
            employee.setStartDate(employeePostRequest.getStartDate());
        } else {
            logger.info("Start date do not receive by end user");
            employee.setStartDate(LocalDate.now());
        }
        return this.employeeDao.save(employee);

    }
}
