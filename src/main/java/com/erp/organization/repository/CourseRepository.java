package com.erp.organization.repository;

import com.erp.organization.domain.College;
import com.erp.organization.domain.Course;
import com.erp.organization.domain.University;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByCourseNameAndCollege(String courseName, College college);

    Page<Course> findByCollege(Pageable pageable, College college);

}
