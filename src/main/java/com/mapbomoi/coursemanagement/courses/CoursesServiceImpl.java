package com.mapbomoi.coursemanagement.courses;

import com.mapbomoi.coursemanagement.common.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoursesServiceImpl implements CoursesService {
    @Autowired
    private CoursesRepository coursesRepository;

    @Override
    public CourseDTO findCourseById(Long id) {
        Course course = coursesRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course with ID: " + id + " does not exist in our system"));
        return null;
    }
}
