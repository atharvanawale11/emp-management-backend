package com.example.employee_management_system.service;

import com.example.employee_management_system.dto.ProjectDTO;
import java.util.List;

public interface ProjectService {
    ProjectDTO createProject(ProjectDTO dto);
    ProjectDTO getProjectById(Long id);
    List<ProjectDTO> getAllProjects();
    List<ProjectDTO> getProjectsByEmployee(Long employeeId);
    ProjectDTO updateProject(Long id, ProjectDTO dto);
    void deleteProject(Long id);
}