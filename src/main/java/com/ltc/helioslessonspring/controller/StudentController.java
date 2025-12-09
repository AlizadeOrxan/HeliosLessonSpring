package com.ltc.helioslessonspring.controller;

import com.ltc.helioslessonspring.dto.StudentRequestDTO;
import com.ltc.helioslessonspring.dto.StudentResponseDTO;
import com.ltc.helioslessonspring.service.impl.StudentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentServiceImpl studentService;

    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }


    @PostMapping("/create")
    public ResponseEntity<String> create (@RequestBody StudentRequestDTO studentRequestDTO) {

        StudentResponseDTO studentResponseDTO = studentService.create(studentRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Istifadechi id-si -> " +  studentResponseDTO.getId()
        + "\n Istifadechi adi -> " +  studentResponseDTO.getName() );
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update (@PathVariable Long id , @RequestBody StudentRequestDTO studentRequestDTO) {
        StudentResponseDTO studentResponseDTO = studentService.update(id, studentRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body("Istifadechi id-si -> " +  studentResponseDTO.getId() +  "\n Istifadechi adi -> " +  studentResponseDTO.getName() );
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getById(@PathVariable Long id) {
        StudentResponseDTO studentResponseDTO = studentService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(studentResponseDTO);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<StudentResponseDTO>> getAll() {
        List<StudentResponseDTO> studentResponseDTOS = studentService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(studentResponseDTOS);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> deleteById(@PathVariable Long id) {
        studentService.deleteById(id);
        return ResponseEntity.notFound().build();

    }



}
