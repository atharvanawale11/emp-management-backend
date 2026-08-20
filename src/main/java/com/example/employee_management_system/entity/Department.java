package com.example.employee_management_system.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity //tells Hibernate this class maps to a table.
@Table(name = "departments") // table name in MySQL
@Data //auto-generates getters/setters/toString/equals/hashCode, so we don't write boilerplate.
@NoArgsConstructor //It generates a constructor with no parameters
@AllArgsConstructor //It generates a constructor containing all fields:
@Builder //It allows you to create objects using the Builder Pattern.
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    //one Department has many Employees.
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL) //The department field inside the Employee entity owns/manages this relationship
    @ToString.Exclude
    @Builder.Default
    private List<Employee> employees = new ArrayList<>();
}