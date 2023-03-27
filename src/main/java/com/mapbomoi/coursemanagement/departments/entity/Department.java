package com.mapbomoi.coursemanagement.departments.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = Department.TABLE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Department {
    public static final String TABLE = "department";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    private Long id;

    @Column(name = "name")
    @NotNull
    @Length(min = 1, max = 256)
    private String name;
}
