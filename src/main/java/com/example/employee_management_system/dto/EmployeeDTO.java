package com.example.employee_management_system.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String designation;
    private Double salary;
    private LocalDate dateOfJoining;
    private Long departmentId;
    private String departmentName; // read-only, useful for frontend display
}