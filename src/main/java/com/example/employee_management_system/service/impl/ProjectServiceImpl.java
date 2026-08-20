package com.example.employee_management_system.service.impl;

import com.example.employee_management_system.dto.ProjectDTO;
import com.example.employee_management_system.entity.Employee;
import com.example.employee_management_system.entity.Project;
import com.example.employee_management_system.repository.EmployeeRepository;
import com.example.employee_management_system.repository.ProjectRepository;
import com.example.employee_management_system.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public ProjectDTO createProject(ProjectDTO dto) {
        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + dto.getEmployeeId()));

        Project project = Project.builder()
                .name(dto.getName())
                .location(dto.getLocation())
                .startDate(dto.getStartDate())
                .employee(employee)
                .build();

        Project saved = projectRepository.save(project);
        return mapToDTO(saved);
    }

    @Override
    public ProjectDTO getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
        return mapToDTO(project);
    }

    @Override
    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProjectDTO> getProjectsByEmployee(Long employeeId) {
        return projectRepository.findByEmployeeId(employeeId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDTO updateProject(Long id, ProjectDTO dto) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));

        Employee employee = employeeRepository.findById(dto.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + dto.getEmployeeId()));

        project.setName(dto.getName());
        project.setLocation(dto.getLocation());
        project.setStartDate(dto.getStartDate());
        project.setEmployee(employee);

        Project updated = projectRepository.save(project);
        return mapToDTO(updated);
    }

    @Override
    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + id));
        projectRepository.delete(project);
    }

    private ProjectDTO mapToDTO(Project project) {
        return ProjectDTO.builder()
                .id(project.getId())
                .name(project.getName())
                .location(project.getLocation())
                .startDate(project.getStartDate())
                .employeeId(project.getEmployee().getId())
                .employeeName(project.getEmployee().getFirstName() + " " + project.getEmployee().getLastName())
                .build();
    }
}