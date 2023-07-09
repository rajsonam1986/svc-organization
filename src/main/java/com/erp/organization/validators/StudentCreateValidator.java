package com.erp.organization.validators;

import com.erp.organization.dto.StudentDTO;
import com.erp.organization.exception.CustomParameterizedException;
import com.erp.organization.exception.ErrorConstantsOrganization;
import com.erp.organization.repository.StudentRepository;
import com.erp.organization.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentCreateValidator {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private BranchService branchService;

    public void validateStudentId(Long studentId) {
        if (studentId != null) {
            throw new CustomParameterizedException("A new student cannot already have an ID", studentId);
        }
    }

    public void validateBranchId(Long branchId) {
        if (branchId == null) {
            throw new CustomParameterizedException(ErrorConstantsOrganization.BRANCH_ID_EMPTY, branchId);
        }
    }

    public void validateBranchIsAvailable(Long branchId) {
        branchService.findOne(branchId);
    }


    public void validateEmailIsAvailable(String email) {
        studentRepository
                .findByEmailIgnoreCase(email)
                .ifPresent(professional -> {
                    throw new CustomParameterizedException(ErrorConstantsOrganization.EMAIL_ALREADY_IN_USE,
                            email);
                });
    }

    public void validateMobileNumberIsAvailable(String mno) {
        studentRepository
                .findByMobileNumber(mno)
                .ifPresent(professional -> {
                    throw new CustomParameterizedException(ErrorConstantsOrganization.MOBILE_NUMBER_ALREADY_EXISTS,
                            mno);
                });
    }

    public void validateStudent(StudentDTO studentDTO) {

        validateStudentId(studentDTO.getStudentId());
        validateBranchId(studentDTO.getBranchId());
        validateBranchIsAvailable(studentDTO.getBranchId());
        validateEmailIsAvailable(studentDTO.getEmail());
        validateMobileNumberIsAvailable(studentDTO.getMobileNumber());

    }
}
