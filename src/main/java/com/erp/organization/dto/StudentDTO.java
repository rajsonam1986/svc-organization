package com.erp.organization.dto;

import com.erp.organization.security.AuthoritiesConstants;
import lombok.Data;

import java.util.Set;

@Data
public class StudentDTO extends ErpSubjectDTO {

    private Long studentId;
    private String fatherName;
    private String motherName;
    private final Set<String> rolesForSubject = AuthoritiesConstants.patientAuthorities();
}
