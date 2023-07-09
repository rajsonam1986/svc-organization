package com.erp.organization.rest;

import com.erp.organization.dto.UniversityDTO;
import com.erp.organization.rest.util.PaginationUtil;
import com.erp.organization.validators.UniversityCreateValidator;
import com.erp.organization.validators.UniversityUpdateValidator;
import com.erp.organization.service.UniversityService;
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
public class UniversityResource {
    @Autowired
    private UniversityService universityService;

    @Autowired
    private UniversityCreateValidator universityCreateValidator;

    @Autowired
    private UniversityUpdateValidator universityUpdateValidator;

    @PostMapping("/university")
    //    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<UniversityDTO> createUniversity(@RequestBody UniversityDTO universityDTO) throws URISyntaxException {

        log.info("REST Request to save University :{}", universityDTO);
        universityCreateValidator.validate(universityDTO);
        return new ResponseEntity<>(universityService.save(universityDTO), HttpStatus.CREATED);

    }

    @PutMapping("/university")
    //    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<UniversityDTO> updateUniversity(@RequestBody UniversityDTO universityDTO) throws URISyntaxException {

        log.debug("REST request to update University with :{}", universityDTO);
        universityUpdateValidator.validate(universityDTO);
        return new ResponseEntity<>(universityService.update(universityDTO), HttpStatus.OK);
    }


    @GetMapping("/university/{universityId}")
    //    @Secured({ AuthoritiesConstants.USER})
    public ResponseEntity<UniversityDTO> getPatient(@PathVariable Long universityId) throws URISyntaxException {

        log.info("REST request to get an University: {}", universityId);
        UniversityDTO result = universityService.findOne(universityId);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/university")
    //    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<List<UniversityDTO>> getAllPatientActivity(Pageable pageable) {
        log.debug("REST request to get a page of University");

        Page<UniversityDTO> page = universityService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/university");

        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
