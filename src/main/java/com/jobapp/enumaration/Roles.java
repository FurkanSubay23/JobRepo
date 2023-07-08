package com.jobapp.enumaration;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum Roles {
    ADMIN,
    EMPLOYEE,
    @JsonEnumDefaultValue
    USER
}
