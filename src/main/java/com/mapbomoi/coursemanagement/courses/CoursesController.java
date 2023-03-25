package com.mapbomoi.coursemanagement.courses;

import com.mapbomoi.coursemanagement.common.exceptions.InvalidRequestException;
import com.mapbomoi.coursemanagement.common.exceptions.ResourceNotFoundException;
import com.mapbomoi.coursemanagement.courses.dto.CourseDTO;
import com.mapbomoi.coursemanagement.courses.entity.Course;
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

@RestController
@RequestMapping("/api/v1/courses")
@Validated
@Setter
public class CoursesController {
    @Autowired
    private CoursesService coursesService;

    @Autowired
    private CourseMapper courseMapper;

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
        Course course = coursesService.findCourseById(id).orElseThrow(() -> new ResourceNotFoundException("Course with ID: " + id + " does not exist in our system"));
        CourseDTO courseDTO = courseMapper.toCourseDTO(course);
        return ResponseEntity
                .ok()
                .body(courseDTO);
    }
}
