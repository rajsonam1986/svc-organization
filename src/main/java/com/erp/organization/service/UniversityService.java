package com.erp.organization.service;

import com.erp.organization.domain.University;
import com.erp.organization.dto.UniversityDTO;
import com.erp.organization.exception.CustomParameterizedException;
import com.erp.organization.exception.ErrorConstantsOrganization;
import com.erp.organization.exception.ParameterizedErrorVM;
import com.erp.organization.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UniversityService {
    @Autowired
    private UniversityRepository universityRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UniversityDTO save(UniversityDTO universityDTO) {

        log.info("Request to save University :{}", universityDTO);

        return modelMapper
                .map(universityRepository
                        .save(modelMapper.map(universityDTO, University.class)), UniversityDTO.class);
    }

    public UniversityDTO update(UniversityDTO universityDTO) {

        log.info("Request to save University :{}", universityDTO);

        return modelMapper
                .map(universityRepository
                        .save(modelMapper.map(universityDTO, University.class)), UniversityDTO.class);
    }

    public UniversityDTO findOne(Long universityId) {

        log.info("Request to save University :{}", universityId);

        return modelMapper
                .map(universityRepository
                        .findById(universityId)
                        .orElseThrow(() -> new CustomParameterizedException(ErrorConstantsOrganization.UNIVERSITY_NOT_FOUND, universityId)), UniversityDTO.class);
    }

    @Transactional(readOnly = true)
    public Page<UniversityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Universities");
        return universityRepository.findAll(pageable).map(university -> {
            return modelMapper.map(university, UniversityDTO.class);
        });
    }
}
