package com.erp.organization.repository;

import com.erp.organization.domain.Branch;
import com.erp.organization.domain.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long> {
    Optional<Branch> findByBranchNameAndCourse(String branchName, Course course);

    Page<Branch> findByCourse(Pageable pageable, Course course);
}
