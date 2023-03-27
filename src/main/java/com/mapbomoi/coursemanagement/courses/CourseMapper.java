package com.mapbomoi.coursemanagement.courses;

import com.mapbomoi.coursemanagement.courses.dto.CourseDTO;
import com.mapbomoi.coursemanagement.courses.entity.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CourseMapper {
    @Mapping(target = "department", source = "course.department.name")
    public CourseDTO toCourseDTO(Course course);
}
