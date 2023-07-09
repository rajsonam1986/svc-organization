package com.erp.organization.domain;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "college")
@NoArgsConstructor
@Data
public class College extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "college_id")
    private Long collegeId;

    @ManyToOne
    private University university;

    @OneToMany(mappedBy = "college")
    private Set<Course> courses;

    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "college_name")
    private String collegeName;

    @NotNull
    @Column(name = "contact_number")
    private String contactNumber;

    @NotNull
    @Column(name = "city")
    private String city;

    @NotNull
    @Column(name = "state")
    private String state;

    @NotNull
    @Column(name = "land_mark")
    private String landMark;
}
