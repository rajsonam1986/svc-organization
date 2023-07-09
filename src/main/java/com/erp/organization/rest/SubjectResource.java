package com.erp.organization.rest;

import com.erp.organization.dto.SubjectDTO;
import com.erp.organization.rest.util.PaginationUtil;
import com.erp.organization.validators.SubjectCreateValidator;
import com.erp.organization.validators.SubjectUpdateValidator;
import com.erp.organization.service.SubjectService;
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
public class SubjectResource {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SubjectCreateValidator subjectCreateValidator;

    @Autowired
    private SubjectUpdateValidator subjectUpdateValidator;

    @PostMapping("/subject")
    //    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<SubjectDTO> createSubject(@RequestBody SubjectDTO subjectDTO) throws URISyntaxException {

        log.info("REST Request to save subject :{}", subjectDTO);
        subjectCreateValidator.validateSubject(subjectDTO);
        return new ResponseEntity<>(subjectService.save(subjectDTO), HttpStatus.CREATED);

    }

    @PutMapping("/subject")
    //    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<SubjectDTO> updateSubject(@RequestBody SubjectDTO subjectDTO) throws URISyntaxException {

        log.debug("REST request to update subject :{}", subjectDTO);
        subjectUpdateValidator.validateSubject(subjectDTO);
        return new ResponseEntity<>(subjectService.update(subjectDTO), HttpStatus.OK);

    }


    @GetMapping("/subject/{subjectId}")
    //    @Secured({ AuthoritiesConstants.USER})
    public ResponseEntity<SubjectDTO> getSubject(@PathVariable Long subjectId) throws URISyntaxException {

        log.info("REST request to get an subject: {}", subjectId);

        return new ResponseEntity<>(subjectService.findOne(subjectId), HttpStatus.OK);

    }

    @GetMapping("/subjects-in-branch/{branchId}")
    //    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<List<SubjectDTO>> getAllSubjectInBranch(Pageable pageable, @PathVariable Long branchId) {

        log.debug("REST request to get a page of subjects in branch:{}", branchId);

        subjectCreateValidator.validateBranchIsAvailable(branchId);

        Page<SubjectDTO> page = subjectService.findAll(pageable, branchId);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/subject");

        return ResponseEntity.ok().headers(headers).body(page.getContent());

    }
}
