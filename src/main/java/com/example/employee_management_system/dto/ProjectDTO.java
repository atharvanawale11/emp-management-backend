package com.example.employee_management_system.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectDTO {
    private Long id;
    private String name;
    private String location;
    private LocalDate startDate;
    private Long employeeId;
    private String employeeName; // read-only, for frontend display
}