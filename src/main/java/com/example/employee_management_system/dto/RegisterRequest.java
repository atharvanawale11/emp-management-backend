package com.example.employee_management_system.dto;

import com.example.employee_management_system.entity.Role;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private Role role; // ADMIN or EMPLOYEE
}