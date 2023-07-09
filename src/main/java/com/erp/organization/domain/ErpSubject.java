package com.erp.organization.domain;

import com.erp.organization.validators.ValidErpEmail;
import com.erp.organization.validators.ValidErpMobileNumber;
import jakarta.persistence.*;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@MappedSuperclass
@Data
public class ErpSubject implements Serializable {
    private String uuid;

    @ManyToOne
    private Branch branch;
    @Size(max = 100)
    @Column(name = "salutation", length = 100)
    private String salutation;

    @Size(max = 50)
    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @Size(max = 50)
    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "user_ref", nullable = false)
    private String userRef;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    @NotNull
    private Gender gender;

    @ValidErpEmail
    @Column(name = "email")
    private String email;

    @ValidErpMobileNumber
    @Column(name = "mobile_number")
    private String mobileNumber;

    @NotNull
    private String religion;
    @NotNull
    private String nationality;
    @NotNull
    private String category;

    @NotNull
    @Column(name = "blood_group")
    private String bloodGroup;

    @NotNull
    @Column(name = "birth_date")
    private LocalDate birthDate;

    @NotNull
    @Column(nullable = false)
    private boolean activated = false;

    @NotNull
    @Column(nullable = false)
    private boolean disabled = false;

    public String fullName() {
        String result = StringUtils.trimToEmpty(salutation) + " " + StringUtils.trimToEmpty(firstName) + " "
                + StringUtils.trimToEmpty(lastName);
        return StringUtils.trimToEmpty(result);
    }
}
