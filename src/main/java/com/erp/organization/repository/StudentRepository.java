package com.erp.organization.repository;

import com.erp.organization.domain.Branch;
import com.erp.organization.domain.Student;
import com.erp.organization.domain.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {
    Optional<Student> findByEmailIgnoreCase(String email);

    Optional<Student> findByMobileNumber(String email);

    Page<Student> findByBranch(Pageable pageable, Branch branch);
}
