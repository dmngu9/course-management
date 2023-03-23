package com.mapbomoi.coursemanagement.courses;

import com.mapbomoi.coursemanagement.common.exceptions.InvalidRequestException;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/courses")
@Validated
public class CoursesController {
    @Autowired
    CoursesService coursesService;

    @GetMapping(path = "/{courseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseDTO> courseById(
            @Pattern(regexp = "^[1-9]\\d*", message = "courseId must be a positive number")
            @PathVariable
            String courseId
    ) {
        Long id;
        try {
            id = Long.valueOf(courseId);
        } catch (NumberFormatException e) {
            throw new InvalidRequestException("courseId must be a number");
        }
        CourseDTO courseDTO = coursesService.findCourseById(id);
        return ResponseEntity
                .ok()
                .body(courseDTO);
    }
}
