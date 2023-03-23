package com.mapbomoi.coursemanagement.courses;

import java.time.LocalDate;

public record CourseDTO(
        Long id,
        String name,
        String description,
        LocalDate startDate,
        LocalDate endDate
) {
}
