package com.erp.organization.validators;

import com.erp.organization.dto.CourseDTO;
import com.erp.organization.exception.CustomParameterizedException;
import com.erp.organization.exception.ErrorConstantsOrganization;
import com.erp.organization.repository.CollegeRepository;
import com.erp.organization.repository.CourseRepository;
import com.erp.organization.service.CollegeService;
import com.erp.organization.service.CourseService;
import com.erp.organization.service.enums.CourseType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseUpdateValidator {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CollegeRepository collegeRepository;

    public void validateCourseId(Long courseId) {

        if (courseId == null) {
            throw new CustomParameterizedException(ErrorConstantsOrganization.COURSE_ID_EMPTY, courseId);
        }
    }

    public void validateCollegeId(Long collegeId) {
        if (collegeId == null) {
            throw new CustomParameterizedException(ErrorConstantsOrganization.COLLEGE_ID_EMPTY, collegeId);
        }
    }

    public void validateCourseIsAvailable(Long courseId) {
        courseService.findOne(courseId);
    }

    public void validateCollegeIsAvailable(Long collegeId) {
        collegeRepository
                .findById(collegeId)
                .orElseThrow(() -> new CustomParameterizedException(ErrorConstantsOrganization.COLLEGE_NOT_FOUND, collegeId));

    }

    public void validateCourseName(String courseName) {

        if (StringUtils.isEmpty(courseName)) {
            throw new CustomParameterizedException(ErrorConstantsOrganization.NAME_EMPTY, courseName);
        }
    }

    public void validateCourseIsAvailable(String courseName, Long courseId, Long collegeId) {

        if (!courseService.findOne(courseId).getCourseName().equalsIgnoreCase(courseName.trim())) {

            if (courseRepository.findByCourseNameAndCollege(courseName, collegeRepository.findById(collegeId).get()).isPresent()) {
                throw new CustomParameterizedException(ErrorConstantsOrganization.COURSE_EXISTS, courseName);
            }
        }
    }

    public void validateCourseType(String courseType) {
        if (StringUtils.isEmpty(courseType)) {
            throw new CustomParameterizedException(ErrorConstantsOrganization.COURSE_TYPE_EMPTY, courseType);
        }
    }

    public void validateCourseTypeIsValid(String courseType) {
        if (!CourseType.getCourseTypes().contains(courseType)) {
            throw new CustomParameterizedException(ErrorConstantsOrganization.COURSE_TYPE_INVALID, courseType);
        }
    }


    public void validateCourse(CourseDTO courseDTO) {

        validateCourseId(courseDTO.getCourseId());
        validateCollegeId(courseDTO.getCollegeId());
        validateCourseIsAvailable(courseDTO.getCourseId());
        validateCollegeIsAvailable(courseDTO.getCollegeId());
        validateCourseName(courseDTO.getCourseName());
        validateCourseIsAvailable(courseDTO.getCourseName(), courseDTO.getCourseId(), courseDTO.getCollegeId());
        validateCourseType(courseDTO.getCourseType());
        validateCourseTypeIsValid(courseDTO.getCourseType());
    }
}
