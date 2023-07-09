package com.erp.organization.validators;

import com.erp.organization.dto.UniversityDTO;
import com.erp.organization.exception.CustomParameterizedException;
import com.erp.organization.exception.ErrorConstantsOrganization;
import com.erp.organization.repository.UniversityRepository;
import com.erp.organization.service.UniversityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniversityUpdateValidator {
    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private UniversityService universityService;

    public void validateUniversityId(Long universityId) {

        if (universityId == null) {
            throw new CustomParameterizedException("An university can only be updated with its id", universityId);
        }
    }

    public void validateUniversityName(String universityName) {

        if (StringUtils.isEmpty(universityName)) {
            throw new CustomParameterizedException(ErrorConstantsOrganization.NAME_EMPTY, universityName);
        }
    }

    public void validateIsAvailable(String universityName, Long universityId) {
        if (!universityService.findOne(universityId).getUniversityName().equalsIgnoreCase(universityName.trim())) {
            if (universityRepository.findByUniversityName(universityName).isPresent()) {
                throw new CustomParameterizedException(ErrorConstantsOrganization.UNIVERSITY_EXISTS, universityName);
            }
        }
    }

    public void validateContactNumber(String contactNumber) {

        if (StringUtils.isEmpty(contactNumber)) {
            throw new CustomParameterizedException(ErrorConstantsOrganization.CONTACT_NUMBER_EMPTY, contactNumber);
        }
    }

    public void validateContactNumberAvailable(String contactNumber, Long universityId) {

        if (!universityService.findOne(universityId).getContactNumber().equalsIgnoreCase(contactNumber.trim())) {
            if (universityRepository.findByContactNumber(contactNumber).isPresent()) {
                throw new CustomParameterizedException(ErrorConstantsOrganization.CONTACT_NUMBER_ALREADY_IN_USE, contactNumber);
            }
        }
    }

    public void validateEmail(String email) {

        if (StringUtils.isEmpty(email)) {
            throw new CustomParameterizedException(ErrorConstantsOrganization.EMAIL_EMPTY, email);
        }
    }

    private void validateEmailAvailable(String email, Long universityId) {
        if (!universityService.findOne(universityId).getEmail().equalsIgnoreCase(email.trim())) {

            if (universityRepository.findOneByEmail(email).isPresent()) {
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

    public void validate(UniversityDTO universityDTO) {

        validateUniversityId(universityDTO.getUniversityId());

        validateUniversityName(universityDTO.getUniversityName());
        validateIsAvailable(universityDTO.getUniversityName(), universityDTO.getUniversityId());

        validateContactNumber(universityDTO.getContactNumber());
        validateContactNumberAvailable(universityDTO.getContactNumber(), universityDTO.getUniversityId());

        validateEmail(universityDTO.getEmail());
        validateEmailAvailable(universityDTO.getEmail(), universityDTO.getUniversityId());

        validateCity(universityDTO.getCity());
        validateState(universityDTO.getState());
        validateLandMark(universityDTO.getLandMark());
    }
}
