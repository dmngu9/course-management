package com.mapbomoi.coursemanagement.instructors.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.stream.Stream;

@Entity
@Table(name = Instructor.TABLE)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Instructor {
    public static final String TABLE = "instructor";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull
    private Long id;

    @Column(name = "first_name")
    @NotNull
    @Length(min = 1, max = 128)
    private String firstName;

    @Column(name = "last_name")
    @NotNull
    @Length(min = 1, max = 128)
    private String lastName;

    @Column(name = "phone")
    @NotNull
    @Length(min = 1, max = 10)
    private String phone;

    @Column(name = "email")
    @Email
    private String email;

    @Column(name = "employment_type")
    @Convert(converter = Instructor.EmploymentTypeConvert.class)
    @NotNull
    private EmploymentType employmentType;

    @Column(name = "contract_status")
    @Convert(converter = Instructor.ContractStatusConverter.class)
    @NotNull
    private ContractStatus contractStatus;

    @AllArgsConstructor
    @Getter
    public enum EmploymentType {
        FULL_TIME("full-time"),
        PART_TIME("part-time"),
        CASUAL("casual");

        private final String employmentType;
    }

    @Converter(autoApply = true)
    public static class EmploymentTypeConvert implements AttributeConverter<EmploymentType, String> {

        @Override
        public String convertToDatabaseColumn(EmploymentType employmentType) {
            return employmentType == null ? null : employmentType.getEmploymentType();
        }

        @Override
        public EmploymentType convertToEntityAttribute(String employmentType) {
            if (employmentType == null) return null;
            return Stream.of(EmploymentType.values())
                    .filter(e -> e.getEmploymentType().equals(employmentType))
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        }
    }

    @AllArgsConstructor
    @Getter
    public enum ContractStatus {
        ACTIVE("active"),
        INACTIVE("inactive");

        private final String status;
    }

    @Converter(autoApply = true)
    public static class ContractStatusConverter implements AttributeConverter<ContractStatus, String> {

        @Override
        public String convertToDatabaseColumn(ContractStatus contractStatus) {
            return contractStatus == null ? null : contractStatus.getStatus();
        }

        @Override
        public ContractStatus convertToEntityAttribute(String s) {
            if (s == null) return null;
            return Stream.of(ContractStatus.values())
                    .filter(c -> c.getStatus().equals(s))
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        }
    }
}
