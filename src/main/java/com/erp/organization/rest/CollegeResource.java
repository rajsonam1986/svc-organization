package com.erp.organization.rest;

import com.erp.organization.dto.CollegeDTO;
import com.erp.organization.rest.util.PaginationUtil;
import com.erp.organization.validators.CollegeCreateValidator;
import com.erp.organization.validators.CollegeUpdateValidator;
import com.erp.organization.service.CollegeService;
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
public class CollegeResource {

    @Autowired
    private CollegeService collegeService;

    @Autowired
    private CollegeCreateValidator collegeCreateValidator;

    @Autowired
    private CollegeUpdateValidator collegeUpdateValidator;

    @PostMapping("/college")
    //    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<CollegeDTO> createCollege(@RequestBody CollegeDTO collegeDTO) throws URISyntaxException {

        log.info("REST Request to save College :{}", collegeDTO);
        collegeCreateValidator.validate(collegeDTO);
        return new ResponseEntity<>(collegeService.save(collegeDTO), HttpStatus.CREATED);

    }

    @PutMapping("/college")
    //    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<CollegeDTO> updateCollege(@RequestBody CollegeDTO collegeDTO) throws URISyntaxException {

        log.debug("REST request to update College with :{}", collegeDTO);
        collegeUpdateValidator.validate(collegeDTO);

        return new ResponseEntity<>(collegeService.update(collegeDTO), HttpStatus.OK);
    }


    @GetMapping("/college/{collegeId}")
    //    @Secured({ AuthoritiesConstants.USER})
    public ResponseEntity<CollegeDTO> getCollege(@PathVariable Long collegeId) throws URISyntaxException {

        log.info("REST request to get an College: {}", collegeId);

        return new ResponseEntity<>(collegeService.findOne(collegeId), HttpStatus.OK);
    }

    @GetMapping("/college-in-university/{universityId}")
    //    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<List<CollegeDTO>> findAllInUniversity(Pageable pageable, @PathVariable Long universityId) {

        log.debug("REST request to get All Colleges of University:{}",universityId);

        collegeCreateValidator.validateUniversityAvailable(universityId);

        Page<CollegeDTO> page = collegeService.findAllInUniversity(pageable, universityId);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/college");

        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
