package com.mapbomoi.coursemanagement.courses.entity;

import com.mapbomoi.coursemanagement.departments.entity.Department;
import com.mapbomoi.coursemanagement.instructors.entity.Instructor;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.sql.Date;

@Entity
@Table(name = Course.TABLE)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    public static final String TABLE = "course";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    private Long id;

    @Column(name = "name")
    @NotNull
    @Length(min = 1, max = 256)
    private String name;

    @Column(name = "description")
    @NotNull
    @Length(min = 1, max = 256)
    private String description;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    @NotNull
    private Department department;

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    @NotNull
    private Instructor instructor;

    @Column(name = "start_date")
    @NotNull
    @Length(min = 10, max = 10)
    private Date startDate;

    @Column(name = "end_date")
    @NotNull
    @Length(min = 10, max = 10)
    private Date endDate;
}
