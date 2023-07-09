package com.erp.organization.service;

import com.erp.organization.domain.College;
import com.erp.organization.dto.CollegeDTO;
import com.erp.organization.exception.CustomParameterizedException;
import com.erp.organization.exception.ErrorConstantsOrganization;
import com.erp.organization.repository.CollegeRepository;
import com.erp.organization.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j

public class CollegeService {
    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private ModelMapper modelMapper;

    public CollegeDTO save(CollegeDTO collegeDTO) {
        log.info("Request to save college with:{}", collegeDTO);
        College college = modelMapper.map(collegeDTO, College.class);
        college.setUniversity(universityRepository.findById(collegeDTO.getUniversityId()).get());

        return modelMapper.map(collegeRepository.save(college), CollegeDTO.class);
    }

    public CollegeDTO update(CollegeDTO collegeDTO) {
        log.info("Request to update college with:{}", collegeDTO);
        return modelMapper
                .map(collegeRepository
                        .save(modelMapper.map(collegeDTO, College.class)), CollegeDTO.class);
    }

    public CollegeDTO findOne(Long collegeId) {
        log.info("Request to find college with:{}", collegeId);
        return modelMapper
                .map(collegeRepository
                        .findById(collegeId)
                        .orElseThrow(() -> new CustomParameterizedException(ErrorConstantsOrganization.COLLEGE_NOT_FOUND, collegeId)), CollegeDTO.class);
    }

    public Page<CollegeDTO> findAllInUniversity(Pageable pageable, Long universityId) {
        log.debug("Request to get all Colleges in:{}", universityId);

        return collegeRepository.findByUniversity(pageable, universityRepository.findById(universityId).get())
                .map(college -> {
                    return modelMapper.map(college, CollegeDTO.class);
                });
    }
}
