package com.erp.organization.rest;

import com.erp.organization.dto.StudentDTO;
import com.erp.organization.rest.util.PaginationUtil;
import com.erp.organization.service.StudentService;
import com.erp.organization.validators.StudentCreateValidator;
import com.erp.organization.validators.StudentUpdateValidator;
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
public class StudentResource {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentCreateValidator studentCreateValidator;

    @Autowired
    private StudentUpdateValidator studentUpdateValidator;

    @PostMapping("/student")
    //    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) throws URISyntaxException {

        log.info("REST Request to save student :{}", studentDTO);
        studentCreateValidator.validateStudent(studentDTO);
        return new ResponseEntity<>(studentService.save(studentDTO), HttpStatus.CREATED);

    }

    @PostMapping("/student-activate/{studentId}")
    //    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<Void> studentActivate(@PathVariable Long studentId) throws URISyntaxException {

        log.info("REST Request to activate student :{}", studentId);
        studentUpdateValidator.validateStudentIsAvailable(studentId);
        studentService.doActivate(studentId);
        return new ResponseEntity<>(HttpStatus.CREATED);

    }

    @PutMapping("/student")
    //    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO studentDTO) throws URISyntaxException {

        log.debug("REST request to update student :{}", studentDTO);
        studentUpdateValidator.validateStudent(studentDTO);
        return new ResponseEntity<>(studentService.update(studentDTO), HttpStatus.OK);

    }

    @GetMapping("/student/{studentId}")
    //    @Secured({ AuthoritiesConstants.USER})
    public ResponseEntity<StudentDTO> getStudent(@PathVariable Long studentId) throws URISyntaxException {

        log.info("REST request to get an student: {}", studentId);
        return new ResponseEntity<>(studentService.findOne(studentId), HttpStatus.OK);

    }

    @GetMapping("/students-in-branch/{branchId}")
    //    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<List<StudentDTO>> getAllStudentsInBranch(Pageable pageable, @PathVariable Long branchId) {

        log.debug("REST request to get a page of students");
        studentCreateValidator.validateBranchIsAvailable(branchId);

        Page<StudentDTO> page = studentService.findAllStudentsInBranch(pageable, branchId);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/student");

        return ResponseEntity.ok().headers(headers).body(page.getContent());

    }
}
