package com.erp.organization.validators;

import com.erp.organization.dto.BranchDTO;
import com.erp.organization.exception.CustomParameterizedException;
import com.erp.organization.exception.ErrorConstantsOrganization;
import com.erp.organization.repository.BranchRepository;
import com.erp.organization.repository.CourseRepository;
import com.erp.organization.service.BranchService;
import com.erp.organization.service.CourseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BranchUpdateValidator {
    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private BranchService branchService;

    @Autowired
    private CourseRepository courseRepository;

    public void validateBranchId(Long branchId) {

        if (branchId == null) {
            throw new CustomParameterizedException(ErrorConstantsOrganization.BRANCH_ID_EMPTY, branchId);
        }
    }

    public void validateCourseId(Long courseId) {
        if (courseId == null) {
            throw new CustomParameterizedException(ErrorConstantsOrganization.COURSE_ID_EMPTY, courseId);
        }
    }

    public void validateCourseIsAvailable(Long courseId) {
        courseRepository
                .findById(courseId)
                .orElseThrow(() -> new CustomParameterizedException(ErrorConstantsOrganization.COURSE_NOT_FOUND, courseId));

    }

    public void validateBranchName(String branchName) {

        if (StringUtils.isEmpty(branchName)) {
            throw new CustomParameterizedException(ErrorConstantsOrganization.NAME_EMPTY, branchName);
        }
    }

    public void validateBranchIsAvailable(String branchName, Long branchId, Long courseId) {

        if (!branchService.findOne(branchId).getBranchName().equalsIgnoreCase(branchName.trim())) {

            if (branchRepository.findByBranchNameAndCourse(branchName, courseRepository.findById(courseId).get())
                    .isPresent()) {
                throw new CustomParameterizedException(ErrorConstantsOrganization.BRANCH_EXISTS, branchName);
            }
        }
    }

    public void validateBranch(BranchDTO branchDTO) {

        validateBranchId(branchDTO.getBranchId());
        validateCourseId(branchDTO.getCourseId());
        validateCourseIsAvailable(branchDTO.getCourseId());
        validateBranchName(branchDTO.getBranchName());
        validateBranchIsAvailable(branchDTO.getBranchName(), branchDTO.getBranchId(), branchDTO.getCourseId());

    }
}
