package com.erp.organization.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.Instant;


@Data
public class BranchDTO implements Serializable {
    private Long courseId;
    private Long branchId;
    private String branchName;

}
