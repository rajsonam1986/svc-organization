package com.erp.organization.service;

import com.erp.organization.domain.Subject;
import com.erp.organization.dto.SubjectDTO;
import com.erp.organization.exception.CustomParameterizedException;
import com.erp.organization.exception.ErrorConstantsOrganization;
import com.erp.organization.repository.BranchRepository;
import com.erp.organization.repository.SubjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class SubjectService {
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private ModelMapper modelMapper;

    public SubjectDTO save(SubjectDTO subjectDTO) {
        log.info("Request to save subject with:{}", subjectDTO);

        Subject subject = modelMapper.map(subjectDTO, Subject.class);
        subject.setBranch(branchRepository.findById(subjectDTO.getBranchId()).get());

        return modelMapper.map(subjectRepository.save(subject), SubjectDTO.class);
    }


    public SubjectDTO update(SubjectDTO subjectDTO) {
        log.info("Request to update subject with:{}", subjectDTO);
        return modelMapper
                .map(subjectRepository
                        .save(modelMapper.map(subjectDTO, Subject.class)), SubjectDTO.class);
    }

    public SubjectDTO findOne(Long subjectId) {
        log.info("Request to find subject with:{}", subjectId);
        return modelMapper
                .map(subjectRepository
                        .findById(subjectId)
                        .orElseThrow(() -> new CustomParameterizedException(ErrorConstantsOrganization.SUBJECT_NOT_FOUND, subjectId)), SubjectDTO.class);
    }

    public Page<SubjectDTO> findAll(Pageable pageable, Long branchId) {
        log.debug("Request to get all subjects");
        return subjectRepository.findAllByBranch(pageable, branchRepository.findById(branchId).get()).map(subject -> {
            return modelMapper.map(subject, SubjectDTO.class);
        });
    }

    public SubjectDTO findByBranchIdAndSubjectId(Long branchId, Long subjectId) {
        log.info("Request to find branch with:{},{}", branchId, subjectId);
        return modelMapper
                .map(subjectRepository
                        .findBySubjectIdAndBranch(subjectId, branchRepository.findById(branchId).get())
                        .orElseThrow(() -> new CustomParameterizedException(ErrorConstantsOrganization.SUBJECT_NOT_FOUND, subjectId)), SubjectDTO.class);
    }
}
