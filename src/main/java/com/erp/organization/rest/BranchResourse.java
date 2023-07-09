package com.erp.organization.rest;

import com.erp.organization.dto.BranchDTO;
import com.erp.organization.rest.util.PaginationUtil;
import com.erp.organization.validators.BranchCreateValidator;
import com.erp.organization.validators.BranchUpdateValidator;
import com.erp.organization.service.BranchService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/organization")
@AllArgsConstructor
@Slf4j
public class BranchResourse {

    @Autowired
    private BranchService branchService;

    @Autowired
    private BranchCreateValidator branchCreateValidator;

    @Autowired
    private BranchUpdateValidator branchUpdateValidator;

    @PostMapping("/branch")
    //    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<BranchDTO> createBranch(@RequestBody BranchDTO branchDTO) throws URISyntaxException {

        log.info("REST Request to save branch :{}", branchDTO);
        branchCreateValidator.validateBranch(branchDTO);
        return new ResponseEntity<>(branchService.save(branchDTO), HttpStatus.CREATED);

    }

    @PutMapping("/branch")
    //    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<BranchDTO> updateBranch(@RequestBody BranchDTO branchDTO) throws URISyntaxException {

        log.debug("REST request to update branch :{}", branchDTO);
        branchUpdateValidator.validateBranch(branchDTO);
        return new ResponseEntity<>(branchService.update(branchDTO), HttpStatus.OK);

    }


    @GetMapping("/branch/{branchId}")
    //    @Secured({ AuthoritiesConstants.USER})
    public ResponseEntity<BranchDTO> getBranch(@PathVariable Long branchId) throws URISyntaxException {

        log.info("REST request to get an branch: {}", branchId);
        return new ResponseEntity<>(branchService.findOne(branchId), HttpStatus.OK);

    }

    @GetMapping("/branches-in-course/{courseId}")
    //    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<List<BranchDTO>> getAllBranches(Pageable pageable, @PathVariable Long courseId) {
        log.debug("REST request to get a page of branches");

        branchCreateValidator.validateCourseIsAvailable(courseId);

        Page<BranchDTO> page = branchService.findAllBranchesInCourse(pageable, courseId);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/branch");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
