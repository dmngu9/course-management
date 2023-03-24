package com.mapbomoi.coursemanagement.courses;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapbomoi.coursemanagement.courses.dto.CourseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(CoursesController.class)
class CoursesControllerTest {
    @Autowired
    private CoursesController coursesController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void courseById_whenNonNumericCourseId_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/api/v1/courses/abc"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("[\"courseId must be a positive number\"]"));
    }

    @Test
    void courseById_whenNonPositiveCourseId_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(get("/api/v1/courses/0"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("[\"courseId must be a positive number\"]"));
    }

    @Test
    void courseById_whenValidCourseId_shouldReturnOk() throws Exception {
        CourseDTO mockResponse = new CourseDTO(
                (long) 10,
                "Data structures",
                "upskill in data structure",
                LocalDate.of(2023, 11, 1),
                LocalDate.of(2023, 12, 1)
        );
        mockMvc.perform(get("/api/v1/courses/10"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(mockResponse)));
    }
}