package com.erp.organization.validators;

import com.erp.organization.dto.CollegeDTO;
import com.erp.organization.dto.CourseDTO;
import com.erp.organization.exception.CustomParameterizedException;
import com.erp.organization.exception.ErrorConstantsOrganization;
import com.erp.organization.repository.CollegeRepository;
import com.erp.organization.repository.CourseRepository;
import com.erp.organization.service.CollegeService;
import com.erp.organization.service.enums.CourseType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseCreateValidator {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CollegeRepository collegeRepository;

    public void validateCourseId(Long courseId) {

        if (courseId != null) {
            throw new CustomParameterizedException("A new course cannot already have an ID", courseId);
        }
    }

    public void validateCollegeId(Long collegeId) {
        if (collegeId == null) {
            throw new CustomParameterizedException(ErrorConstantsOrganization.COLLEGE_ID_EMPTY, collegeId);
        }
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

    public void validateCourseIsAvailable(String courseName, Long collegeId) {

        if (courseRepository
                .findByCourseNameAndCollege(courseName, collegeRepository.findById(collegeId).get())
                .isPresent()) {
            throw new CustomParameterizedException(ErrorConstantsOrganization.COURSE_EXISTS, courseName);
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
        validateCollegeIsAvailable(courseDTO.getCollegeId());
        validateCourseName(courseDTO.getCourseName());
        validateCourseIsAvailable(courseDTO.getCourseName(), courseDTO.getCollegeId());
        validateCourseType(courseDTO.getCourseType());
        validateCourseTypeIsValid(courseDTO.getCourseType());
    }
}
