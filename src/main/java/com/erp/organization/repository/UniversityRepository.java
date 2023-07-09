package com.erp.organization.repository;

import com.erp.organization.domain.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long> {

    Optional<University> findByUniversityName(String universityName);

    Optional<University> findByContactNumber(String contactNumber);

    Optional<University> findOneByEmail(String email);
}
