package com.erp.organization.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class CollegeDTO implements Serializable {
    private Long collegeId;
    private Long universityId;
    private String collegeName;
    private String contactNumber;
    private String city;
    private String state;
    private String landMark;
    private String email;
}
