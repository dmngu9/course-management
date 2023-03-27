package com.mapbomoi.coursemanagement.courses;

import com.mapbomoi.coursemanagement.courses.entity.Course;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoursesRepository extends CrudRepository<Course, Long> {
    @EntityGraph(attributePaths = {"department", "instructor"})
    Optional<Course> findById(Long id);
}
