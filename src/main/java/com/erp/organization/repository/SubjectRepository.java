package com.erp.organization.repository;

import com.erp.organization.domain.Branch;
import com.erp.organization.domain.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long> {
    Optional<Subject> findBySubjectNameAndBranch(String subjectName, Branch branch);

    Optional<Subject> findBySubjectIdAndBranch(Long subjectId, Branch branch);

    Page<Subject> findAllByBranch(Pageable pageable, Branch branch);

}

