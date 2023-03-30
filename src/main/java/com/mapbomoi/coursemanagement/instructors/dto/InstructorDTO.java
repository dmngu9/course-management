package com.mapbomoi.coursemanagement.instructors.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
public class InstructorDTO {
    @NotNull
    private Long id;

    @NotNull
    @Length(min = 1)
    private String fullName;

    @NotNull
    @Length(min = 1, max = 10)
    private String phone;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String employmentType;

    @NotNull
    private String contractStatus;
}
