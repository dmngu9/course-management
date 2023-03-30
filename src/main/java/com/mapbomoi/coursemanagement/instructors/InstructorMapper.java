package com.mapbomoi.coursemanagement.instructors;

import com.mapbomoi.coursemanagement.instructors.dto.InstructorDTO;
import com.mapbomoi.coursemanagement.instructors.entity.Instructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface InstructorMapper {
    @Mapping(target = "fullName", source = ".", qualifiedByName = { "FirstAndLastNameToFullName" })
    @Mapping(target = "employmentType", expression = "java(instructor.getEmploymentType().getValue())")
    @Mapping(target = "contractStatus", expression = "java(instructor.getContractStatus().getValue())")
    InstructorDTO toInstructorDTO(Instructor instructor);

    @Named("FirstAndLastNameToFullName")
    static String toFullName(Instructor instructor) {
        return instructor.getFirstName() + " " + instructor.getLastName();
    }
}
