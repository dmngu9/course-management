package com.mapbomoi.coursemanagement.courses;

import com.mapbomoi.coursemanagement.courses.dto.CourseDTO;
import com.mapbomoi.coursemanagement.courses.entity.Course;
import com.mapbomoi.coursemanagement.instructors.InstructorMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(uses = { InstructorMapper.class }, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CourseMapper {
    @Mapping(target = "department", source = "course.department.name")
    CourseDTO toCourseDTO(Course course);
}
