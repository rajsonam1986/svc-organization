package com.erp.organization.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
public class SubjectDTO implements Serializable {

    private Long branchId;
    private Long subjectId;
    private String subjectName;
    private Integer mark;
    private Integer externalMark;
    private Integer internalMark;
    private Integer passingMark;

}
