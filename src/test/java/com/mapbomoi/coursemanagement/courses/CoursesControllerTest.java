package com.mapbomoi.coursemanagement.courses;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapbomoi.coursemanagement.courses.dto.CourseDTO;
import com.mapbomoi.coursemanagement.courses.entity.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(CoursesController.class)
class CoursesControllerTest {
    @Autowired
    private CoursesController coursesController;

    @MockBean
    private CoursesService coursesService;

    @MockBean
    private CourseMapper courseMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        coursesController.setCourseMapper(Mappers.getMapper(CourseMapper.class));
    }

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
    void courseById_whenNoCourseFound_shouldReturnNotFoundError() throws Exception {
        when(coursesService.findCourseById(anyLong())).thenReturn(Optional.empty());

        mockMvc.perform((get("/api/v1/courses/10")))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Course with ID: 10 does not exist in our system"));
    }

    @Test
    void courseById_whenValidCourseId_shouldReturnOk() throws Exception {
        Long courseId = 10L;
        CourseDTO courseDTO = new CourseDTO(
                courseId,
                "Data structures",
                "upskill in data structure",
                LocalDate.of(2023, 11, 1),
                LocalDate.of(2023, 12, 1)
        );
        Course course = new Course(
                courseId,
                "Data structures",
                "upskill in data structure",
                Date.valueOf(LocalDate.of(2023, 11, 1)),
                Date.valueOf(LocalDate.of(2023, 12, 1))
        );
        when(coursesService.findCourseById(courseId)).thenReturn(Optional.of(course));

        mockMvc.perform(get("/api/v1/courses/10"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(courseDTO)));
    }
}