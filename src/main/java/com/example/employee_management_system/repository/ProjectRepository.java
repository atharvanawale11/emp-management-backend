package com.example.employee_management_system.repository;

import com.example.employee_management_system.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByEmployeeId(Long employeeId);
}