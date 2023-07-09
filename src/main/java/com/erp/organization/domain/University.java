package com.erp.organization.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "university")
@NoArgsConstructor
@Data

public class University extends AbstractAuditingEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "university_id")
    private Long universityId;

    @OneToMany(mappedBy = "university")
    private Set<College> colleges;

    @NotNull
    @Column(name = "university_name")
    private String universityName;

    @NotNull
    @Column(name = "contact_number")
    private String contactNumber;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private String email;

    @NotNull
    @Column(name = "land_mark")
    private String landMark;

}
