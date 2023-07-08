package com.jobapp.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeePostRequest {
    @NotBlank(message = "Can not be null or blank")
    private String userName;

    @NotBlank(message = "Can not be null or blank")
    private String lastName;

    @Email(message = "Email can not be null")
    private String email;

    @Min(value = 18, message = "Age can not bu null")
    private int age;

    @NotBlank(message = "Can not be null or blank")
    private String country;

    private LocalDate startDate;
    private long jobId;


}
