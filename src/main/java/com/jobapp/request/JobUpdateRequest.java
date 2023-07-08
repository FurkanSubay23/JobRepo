package com.jobapp.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobUpdateRequest {
    private String title;
    private String definition;
    private LocalDate startDate;
    private LocalDate endDate;

}
