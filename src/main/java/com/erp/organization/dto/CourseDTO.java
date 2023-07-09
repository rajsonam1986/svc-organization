package com.erp.organization.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class CourseDTO implements Serializable {
    private Long courseId;
    private Long collegeId;
    private String courseName;
    private String courseType;

}
