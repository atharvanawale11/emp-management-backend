package com.example.employee_management_system.service.impl;

import com.example.employee_management_system.dto.EmployeeDTO;
import com.example.employee_management_system.entity.Department;
import com.example.employee_management_system.entity.Employee;
import com.example.employee_management_system.exception.DuplicateResourceException;
import com.example.employee_management_system.exception.ResourceNotFoundException;
import com.example.employee_management_system.repository.DepartmentRepository;
import com.example.employee_management_system.repository.EmployeeRepository;
import com.example.employee_management_system.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public EmployeeDTO createEmployee(EmployeeDTO dto) {
        if (employeeRepository.existsByEmail(dto.getEmail())) {
            throw new DuplicateResourceException("Employee with this email already exists");
        }
        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + dto.getDepartmentId()));

        Employee employee = Employee.builder()
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .email(dto.getEmail())
                .phoneNumber(dto.getPhoneNumber())
                .designation(dto.getDesignation())
                .salary(dto.getSalary())
                .dateOfJoining(dto.getDateOfJoining())
                .department(department)
                .build();

        Employee saved = employeeRepository.save(employee);
        return mapToDTO(saved);
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        return mapToDTO(employee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> getEmployeesByDepartment(Long departmentId) {
        return employeeRepository.findByDepartmentId(departmentId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));

        Department department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + dto.getDepartmentId()));

        employee.setFirstName(dto.getFirstName());
        employee.setLastName(dto.getLastName());
        employee.setEmail(dto.getEmail());
        employee.setPhoneNumber(dto.getPhoneNumber());
        employee.setDesignation(dto.getDesignation());
        employee.setSalary(dto.getSalary());
        employee.setDateOfJoining(dto.getDateOfJoining());
        employee.setDepartment(department);

        Employee updated = employeeRepository.save(employee);
        return mapToDTO(updated);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + id));
        employeeRepository.delete(employee);
    }

    private EmployeeDTO mapToDTO(Employee employee) {
        return EmployeeDTO.builder()
                .id(employee.getId())
                .firstName(employee.getFirstName())
                .lastName(employee.getLastName())
                .email(employee.getEmail())
                .phoneNumber(employee.getPhoneNumber())
                .designation(employee.getDesignation())
                .salary(employee.getSalary())
                .dateOfJoining(employee.getDateOfJoining())
                .departmentId(employee.getDepartment().getId())
                .departmentName(employee.getDepartment().getName())
                .build();
    }
}