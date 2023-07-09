package com.erp.organization.service;

import com.erp.organization.domain.Student;
import com.erp.organization.dto.StudentDTO;
import com.erp.organization.exception.CustomParameterizedException;
import com.erp.organization.exception.ErrorConstantsOrganization;
import com.erp.organization.repository.BranchRepository;
import com.erp.organization.repository.StudentRepository;
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
public class StudentService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BranchRepository branchRepository;


    public StudentDTO save(StudentDTO subjectDTO) {
        log.info("Request to save student with:{}", subjectDTO);

        Student student = modelMapper.map(subjectDTO, Student.class);
        student.setBranch(branchRepository.findById(subjectDTO.getBranchId()).get());

        return modelMapper.map(studentRepository.save(student), StudentDTO.class);
    }


    public StudentDTO update(StudentDTO studentDTO) {
        log.info("Request to update student with:{}", studentDTO);
        return modelMapper
                .map(studentRepository
                        .save(modelMapper.map(studentDTO, Student.class)), StudentDTO.class);
    }

    public StudentDTO findOne(Long studentId) {
        log.info("Request to find student with:{}", studentId);
        return modelMapper
                .map(studentRepository
                        .findById(studentId)
                        .orElseThrow(() -> new CustomParameterizedException(ErrorConstantsOrganization.STUDENT_NOT_FOUND, studentId)), StudentDTO.class);
    }

    public void doActivate(Long studentId) {
        log.info("Request to activate student with:{}", studentId);
        Student student = studentRepository.findById(studentId).get();
        student.setActivated(true);
        studentRepository.save(student);
    }

    public Page<StudentDTO> findAllStudentsInBranch(Pageable pageable, Long branchId) {
        log.debug("Request to get all students in branch:{}", branchId);
        return studentRepository.findByBranch(pageable, branchRepository.findById(branchId).get()).map(student -> {
            return modelMapper.map(student, StudentDTO.class);
        });
    }
}
