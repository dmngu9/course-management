package com.mapbomoi.coursemanagement.courses;

import com.mapbomoi.coursemanagement.courses.entity.Course;

import java.util.Optional;

public interface CoursesService {
    public Optional<Course> findCourseById(Long id);
}
