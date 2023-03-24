package com.mapbomoi.coursemanagement.courses;

import com.mapbomoi.coursemanagement.courses.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CoursesServiceImpl implements CoursesService {
    @Autowired
    private CoursesRepository coursesRepository;

    @Override
    public Optional<Course> findCourseById(Long id) {
        return coursesRepository.findById(id);
    }
}
