package com.erp.organization.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "subject")
@NoArgsConstructor
@Data
public class Subject extends AbstractAuditingEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private Long subjectId;

    @ManyToOne
    private Branch branch;

    @Column(name = "subject_name")
    private String subjectName;

    private Integer mark;

    @Column(name = "external_mark")
    private Integer externalMark;

    @Column(name = "internal_mark")
    private Integer internalMark;

    @Column(name = "passing_mark")
    private Integer passingMark;

}
