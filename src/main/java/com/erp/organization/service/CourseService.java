package com.erp.organization.service;

import com.erp.organization.domain.Course;
import com.erp.organization.dto.CourseDTO;
import com.erp.organization.exception.CustomParameterizedException;
import com.erp.organization.exception.ErrorConstantsOrganization;
import com.erp.organization.repository.CollegeRepository;
import com.erp.organization.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Slf4j
@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CourseDTO save(CourseDTO courseDTO) {
        log.info("Request to save course with:{}", courseDTO);

        Course course = modelMapper.map(courseDTO, Course.class);
        course.setCollege(collegeRepository.findById(courseDTO.getCollegeId()).get());
        return modelMapper.map(courseRepository.save(course), CourseDTO.class);
    }


    public CourseDTO update(CourseDTO courseDTO) {
        log.info("Request to update course with:{}", courseDTO);
        return modelMapper
                .map(courseRepository
                        .save(modelMapper.map(courseDTO, Course.class)), CourseDTO.class);
    }

    public CourseDTO findOne(Long courseId) {
        log.info("Request to find course with:{}", courseId);
        return modelMapper
                .map(courseRepository
                        .findById(courseId)
                        .orElseThrow(() -> new CustomParameterizedException(ErrorConstantsOrganization.COURSE_NOT_FOUND, courseId)), CourseDTO.class);
    }

    @Transactional(readOnly = true)
    public Page<CourseDTO> findAllCoursesInCollege(Pageable pageable, Long collegeId) {
        log.debug("Request to get all Courses");
        return courseRepository.findByCollege(pageable, collegeRepository.findById(collegeId).get()).map(course -> {
            return modelMapper.map(course, CourseDTO.class);
        });
    }

}
