package com.mapbomoi.coursemanagement.instructors.entity;

import com.mapbomoi.coursemanagement.common.enums.PersistableEnum;
import com.mapbomoi.coursemanagement.common.enums.PersistableEnumConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

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
    public enum EmploymentType implements PersistableEnum<String> {
        FULL_TIME("full-time"),
        PART_TIME("part-time"),
        CASUAL("casual");

        private final String value;
    }

    @Converter(autoApply = true)
    public static class EmploymentTypeConvert extends PersistableEnumConverter<EmploymentType, String> {

        public EmploymentTypeConvert() {
            super(EmploymentType.class);
        }
    }

    @AllArgsConstructor
    @Getter
    public enum ContractStatus implements PersistableEnum<String> {
        ACTIVE("active"),
        INACTIVE("inactive");

        private final String value;
    }

    @Converter(autoApply = true)
    public static class ContractStatusConverter extends PersistableEnumConverter<ContractStatus, String> {
        public ContractStatusConverter() {
            super(ContractStatus.class);
        }
    }
}
