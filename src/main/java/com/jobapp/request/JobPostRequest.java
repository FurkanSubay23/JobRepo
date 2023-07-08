package com.jobapp.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPostRequest {
    private long userId;
    private String title;
    private String definition;
    private LocalDate startDate;
    private LocalDate endDate;

}
