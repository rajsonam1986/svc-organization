package com.erp.organization.rest;

import com.erp.organization.dto.CourseDTO;
import com.erp.organization.rest.util.PaginationUtil;
import com.erp.organization.validators.CourseCreateValidator;
import com.erp.organization.validators.CourseUpdateValidator;
import com.erp.organization.service.CourseService;
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

public class CourseResource {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseCreateValidator courseCreateValidator;

    @Autowired
    private CourseUpdateValidator courseUpdateValidator;

    @PostMapping("/course")
    //    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<CourseDTO> createCourse(@RequestBody CourseDTO courseDTO) throws URISyntaxException {

        log.info("REST Request to save Course :{}", courseDTO);
        courseCreateValidator.validateCourse(courseDTO);
        return new ResponseEntity<>(courseService.save(courseDTO), HttpStatus.CREATED);

    }

    @PutMapping("/course")
    //    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<CourseDTO> updateCourse(@RequestBody CourseDTO courseDTO) throws URISyntaxException {

        log.debug("REST request to update Course with :{}", courseDTO);
        courseUpdateValidator.validateCourse(courseDTO);
        return new ResponseEntity<>(courseService.update(courseDTO), HttpStatus.OK);

    }


    @GetMapping("/course/{courseId}")
    //    @Secured({ AuthoritiesConstants.USER})
    public ResponseEntity<CourseDTO> getCourse(@PathVariable Long courseId) throws URISyntaxException {

        log.info("REST request to get an Course: {}", courseId);
        return new ResponseEntity<>(courseService.findOne(courseId), HttpStatus.OK);

    }

    @GetMapping("/courses-in-college/{collegeId}")
    //    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<List<CourseDTO>> findAllCoursesInCollege(Pageable pageable, @PathVariable Long collegeId) {
        log.debug("REST request to get all courses in college:{}", collegeId);

        courseCreateValidator.validateCollegeIsAvailable(collegeId);

        Page<CourseDTO> page = courseService.findAllCoursesInCollege(pageable, collegeId);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/course");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
