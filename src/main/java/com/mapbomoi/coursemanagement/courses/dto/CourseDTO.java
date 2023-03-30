package com.mapbomoi.coursemanagement.courses.dto;

import com.mapbomoi.coursemanagement.instructors.dto.InstructorDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CourseDTO {
    @NotNull
    private Long id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    @NotBlank
    private String department;

    @NotNull
    private InstructorDTO instructor;

    @NotNull
    @NotBlank
    private LocalDate startDate;

    @NotNull
    @NotBlank
    private LocalDate endDate;
}
