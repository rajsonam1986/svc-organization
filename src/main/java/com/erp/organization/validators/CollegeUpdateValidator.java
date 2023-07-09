package com.erp.organization.validators;

import com.erp.organization.dto.CollegeDTO;
import com.erp.organization.exception.CustomParameterizedException;
import com.erp.organization.exception.ErrorConstantsOrganization;
import com.erp.organization.repository.CollegeRepository;
import com.erp.organization.service.CollegeService;
import com.erp.organization.service.UniversityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CollegeUpdateValidator {
    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private UniversityService universityService;

    @Autowired
    private CollegeService collegeService;

    public void validateCollegeId(Long collegeId) {

        if (collegeId != null) {
            throw new CustomParameterizedException("A college can only be updated with its id", collegeId);
        }
    }

    public void validateCollegeName(String collegeName) {

        if (StringUtils.isEmpty(collegeName)) {
            throw new CustomParameterizedException(ErrorConstantsOrganization.NAME_EMPTY, collegeName);
        }
    }

    public void validateIsAvailable(String collegeName, Long collegeId) {

        if (!collegeService.findOne(collegeId).getCollegeName().equalsIgnoreCase(collegeName.trim())) {

            if (collegeRepository.findByCollegeName(collegeName).isPresent()) {
                throw new CustomParameterizedException(ErrorConstantsOrganization.COLLEGE_EXISTS, collegeName);
            }
        }
    }

    public void validateContactNumber(String contactNumber) {

        if (StringUtils.isEmpty(contactNumber)) {
            throw new CustomParameterizedException(ErrorConstantsOrganization.CONTACT_NUMBER_EMPTY, contactNumber);
        }
    }

    public void validateContactNumberAvailable(String contactNumber, Long collegeId) {

        if (!collegeService.findOne(collegeId).getContactNumber().equalsIgnoreCase(contactNumber.trim())) {

            if (collegeRepository.findByContactNumber(contactNumber).isPresent()) {
                throw new CustomParameterizedException(ErrorConstantsOrganization.CONTACT_NUMBER_ALREADY_IN_USE, contactNumber);
            }
        }
    }

    public void validateEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            throw new CustomParameterizedException(ErrorConstantsOrganization.EMAIL_EMPTY, email);
        }
    }

    private void validateEmailAvailable(String email, Long collegeId) {

        if (!collegeService.findOne(collegeId).getEmail().equalsIgnoreCase(email.trim())) {

            if (collegeRepository.findByEmail(email).isPresent()) {
                throw new CustomParameterizedException(ErrorConstantsOrganization.EMAIL_ALREADY_IN_USE, email);
            }
        }
    }

    public void validateCity(String city) {

        if (StringUtils.isEmpty(city)) {
            throw new CustomParameterizedException(ErrorConstantsOrganization.CITY_EMPTY, city);
        }
    }

    public void validateState(String state) {

        if (StringUtils.isEmpty(state)) {
            throw new CustomParameterizedException(ErrorConstantsOrganization.STATE_EMPTY, state);
        }
    }

    public void validateLandMark(String landMark) {

        if (StringUtils.isEmpty(landMark)) {
            throw new CustomParameterizedException(ErrorConstantsOrganization.LAND_MARK_EMPTY, landMark);
        }
    }

    public void validateUniversity(Long universityId) {
        if (universityId == null) {
            throw new CustomParameterizedException(ErrorConstantsOrganization.UNIVERSITY_ID_EMPTY, universityId);
        }
    }

    public void validateUniversityAvailable(Long universityId) {
        universityService.findOne(universityId);
    }


    public void validate(CollegeDTO collegeDTO) {

        validateUniversity(collegeDTO.getUniversityId());
        validateUniversityAvailable(collegeDTO.getUniversityId());

        validateCollegeId(collegeDTO.getCollegeId());

        validateCollegeName(collegeDTO.getCollegeName());
        validateIsAvailable(collegeDTO.getCollegeName(), collegeDTO.getCollegeId());

        validateContactNumber(collegeDTO.getContactNumber());
        validateContactNumberAvailable(collegeDTO.getContactNumber(), collegeDTO.getCollegeId());

        validateEmail(collegeDTO.getEmail());
        validateEmailAvailable(collegeDTO.getEmail(), collegeDTO.getCollegeId());

        validateCity(collegeDTO.getCity());
        validateState(collegeDTO.getState());
        validateLandMark(collegeDTO.getLandMark());
    }
}
