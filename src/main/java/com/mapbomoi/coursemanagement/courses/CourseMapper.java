package com.mapbomoi.coursemanagement.courses;

import com.mapbomoi.coursemanagement.courses.dto.CourseDTO;
import com.mapbomoi.coursemanagement.courses.entity.Course;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    public CourseDTO toCourseDTO(Course course);
}
