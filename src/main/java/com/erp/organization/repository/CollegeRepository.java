package com.erp.organization.repository;

import com.erp.organization.domain.College;
import com.erp.organization.domain.University;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CollegeRepository extends JpaRepository<College, Long> {

    Optional<College> findByCollegeName(String collegeName);

    Optional<College> findByContactNumber(String contactNumber);

    Optional<College> findByEmail(String email);

    Page<College> findByUniversity(Pageable pageable, University university);

}