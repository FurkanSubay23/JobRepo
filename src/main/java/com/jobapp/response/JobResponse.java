package com.jobapp.response;

import com.jobapp.entity.Employee;
import com.jobapp.entity.Job;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class JobResponse {
    private long Id;
    private long userId;
    private String title;
    private String definiton;
    private LocalDate startDate;
    private LocalDate endDate;
    private long durationDate;
    private List<Employee> employeeList;

    public JobResponse(Job entity) {
        this.Id = entity.getId();
        this.userId = entity.getUser().getId();
        this.title = entity.getTitle();
        this.definiton = entity.getDefinition();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
        this.durationDate = entity.getDurationInDays();
        this.employeeList = entity.getEmployeeList();
    }
}
