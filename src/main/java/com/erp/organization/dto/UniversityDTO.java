package com.erp.organization.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class UniversityDTO implements Serializable {
    private Long universityId;
    private String universityName;
    private String contactNumber;
    private String email;
    private String city;
    private String state;
    private String landMark;
    private String createdBy;
    private Instant createdDate;
}
