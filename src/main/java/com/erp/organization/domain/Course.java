package com.erp.organization.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Entity
@Table(name = "course")
@NoArgsConstructor

@Data
public class Course extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")

    private Long courseId;

    @ManyToOne
    private College college;

    @Column(name = "course_name")
    private String courseName;

    @Column(name = "course_type")
    private String courseType;

}
