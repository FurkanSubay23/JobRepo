package com.jobapp.request;

import com.jobapp.enumaration.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPostRequest {
    private String userName;
    private String password;
    private String email;
    private int age;
    private Roles roles;
}
