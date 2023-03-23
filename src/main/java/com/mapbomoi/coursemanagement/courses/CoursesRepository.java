package com.mapbomoi.coursemanagement.courses;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoursesRepository extends CrudRepository<Course, Long> {
    Optional<Course> findById(Long id);
}
