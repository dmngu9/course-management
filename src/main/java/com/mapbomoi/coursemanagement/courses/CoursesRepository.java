package com.mapbomoi.coursemanagement.courses;

import com.mapbomoi.coursemanagement.courses.entity.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends CrudRepository<Course, Long> {
}
