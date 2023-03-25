package com.mapbomoi.coursemanagement.courses.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

@Entity
@Table(name = Course.TABLE)
@Data
@AllArgsConstructor
public class Course {
    public static final String TABLE = "course";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    private Long id;

    @Column(name = "name")
    @NotNull
    @NotBlank
    @Max(256)
    private String name;

    @Column(name = "description")
    @NotNull
    @NotBlank
    @Max(256)
    private String description;

    @Column(name = "start_date")
    @NotNull
    @NotBlank
    @Max(10)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date startDate;

    @Column(name = "end_date")
    @NotNull
    @NotBlank
    @Max(10)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date endDate;
}
