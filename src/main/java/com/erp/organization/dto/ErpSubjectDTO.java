package com.erp.organization.dto;

import com.erp.organization.domain.Gender;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString(callSuper = true)
public abstract class ErpSubjectDTO implements Serializable {
    private String uuid;
    private Long branchId;
    private String firstName;
    private String lastName;
    private String userRef;
    private String salutation;
    private String email;
    private Gender gender;
    private String mobileNumber;
    private String religion;
    private String nationality;
    private String category;
    private String bloodGroup;
    private LocalDate birthDate;
    private boolean activated;
    private boolean disabled;

    @JsonIgnore
    public String fullName() {
        String result = StringUtils.trimToEmpty(salutation) + " " + StringUtils.trimToEmpty(firstName) + " "
                + StringUtils.trimToEmpty(lastName);
        return StringUtils.trimToEmpty(result);
    }
}
