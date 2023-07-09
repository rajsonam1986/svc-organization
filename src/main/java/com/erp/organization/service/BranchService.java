package com.erp.organization.service;

import com.erp.organization.domain.Branch;
import com.erp.organization.dto.BranchDTO;
import com.erp.organization.exception.CustomParameterizedException;
import com.erp.organization.exception.ErrorConstantsOrganization;
import com.erp.organization.repository.BranchRepository;
import com.erp.organization.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Slf4j
@Service
public class BranchService {
    @Autowired
    private BranchRepository branchRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ModelMapper modelMapper;

    public BranchDTO save(BranchDTO branchDTO) {
        log.info("Request to save branch with:{}", branchDTO);

        Branch branch = modelMapper.map(branchDTO, Branch.class);
        branch.setCourse(courseRepository.findById(branchDTO.getCourseId()).get());

        return modelMapper.map(branchRepository.save(branch), BranchDTO.class);
    }


    public BranchDTO update(BranchDTO branchDTO) {
        log.info("Request to update branch with:{}", branchDTO);
        return modelMapper
                .map(branchRepository
                        .save(modelMapper.map(branchDTO, Branch.class)), BranchDTO.class);
    }

    public BranchDTO findOne(Long branchId) {
        log.info("Request to find branch with:{}", branchId);
        return modelMapper
                .map(branchRepository
                        .findById(branchId)
                        .orElseThrow(() -> new CustomParameterizedException(ErrorConstantsOrganization.BRANCH_NOT_FOUND, branchId)), BranchDTO.class);
    }

    @Transactional(readOnly = true)
    public Page<BranchDTO> findAllBranchesInCourse(Pageable pageable, Long courseId) {
        log.debug("Request to get all branches of course:{}", courseId);
        return branchRepository.findByCourse(pageable, courseRepository.findById(courseId).get()).map(branch -> {
            return modelMapper.map(branch, BranchDTO.class);
        });
    }

}
