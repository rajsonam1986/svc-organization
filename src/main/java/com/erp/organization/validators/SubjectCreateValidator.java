package com.erp.organization.validators;

import com.erp.organization.domain.Branch;
import com.erp.organization.dto.SubjectDTO;
import com.erp.organization.exception.CustomParameterizedException;
import com.erp.organization.exception.ErrorConstantsOrganization;
import com.erp.organization.repository.BranchRepository;
import com.erp.organization.repository.SubjectRepository;
import com.erp.organization.service.BranchService;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubjectCreateValidator {
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private BranchService branchService;

    @Autowired
    private ModelMapper modelMapper;

    public void validateSubjectId(Long subjectId) {

        if (subjectId != null) {
            throw new CustomParameterizedException("A new subject cannot already have an ID", subjectId);
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

    public void validateSubjectName(String subjectName) {

        if (StringUtils.isEmpty(subjectName)) {
            throw new CustomParameterizedException(ErrorConstantsOrganization.NAME_EMPTY, subjectName);
        }
    }

    public void validateSubjectIsAvailable(String subjectName, Long branchId) {

        if (subjectRepository.findBySubjectNameAndBranch(subjectName,modelMapper.map(branchService.findOne(branchId), Branch.class)).isPresent()) {
            throw new CustomParameterizedException(ErrorConstantsOrganization.BRANCH_EXISTS, subjectName);
        }
    }

    public void validateSubjectMark(Integer mark) {

        if (mark == null) {
            throw new CustomParameterizedException(ErrorConstantsOrganization.SUBJECT_MARK_EMPTY, mark);
        }
    }

    public void validateSubjectExternalMark(Integer externalMark) {

        if (externalMark == null) {
            throw new CustomParameterizedException(ErrorConstantsOrganization.EXTERNAL_MARK_EMPTY, externalMark);
        }
    }

    public void validateSubjectInternalMark(Integer internalMark) {

        if (internalMark == null) {
            throw new CustomParameterizedException(ErrorConstantsOrganization.INTERNAL_MARK_EMPTY, internalMark);
        }
    }

    public void validateSubjectPassingMark(Integer passingMark) {

        if (passingMark == null) {
            throw new CustomParameterizedException(ErrorConstantsOrganization.PASSING_MARK_EMPTY, passingMark);
        }
    }

    public void validateSubject(SubjectDTO subjectDTO) {

        validateSubjectId(subjectDTO.getSubjectId());
        validateBranchId(subjectDTO.getBranchId());
        validateBranchIsAvailable(subjectDTO.getBranchId());
        validateSubjectName(subjectDTO.getSubjectName());
        validateSubjectIsAvailable(subjectDTO.getSubjectName(), subjectDTO.getBranchId());
        validateSubjectMark(subjectDTO.getMark());
        validateSubjectExternalMark(subjectDTO.getExternalMark());
        validateSubjectInternalMark(subjectDTO.getInternalMark());
        validateSubjectPassingMark(subjectDTO.getPassingMark());
    }
}
