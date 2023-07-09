package com.erp.organization.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "student")
@NoArgsConstructor
@Data
public class Student extends ErpSubject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;

    @Size(max = 50)
    @NotNull
    @Column(name = "father_name")
    private String fatherName;

    @Size(max = 50)
    @NotNull
    @Column(name = "mother_name")
    private String motherName;

}
