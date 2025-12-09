package com.ltc.helioslessonspring.service;

import com.ltc.helioslessonspring.dto.StudentRequestDTO;
import com.ltc.helioslessonspring.dto.StudentResponseDTO;

import java.util.List;

public interface StudentService {

    void deleteById(Long id);
    List<StudentResponseDTO> getAll();
    StudentResponseDTO getById(Long id);
    StudentResponseDTO create(StudentRequestDTO studentRequestDTO);
    StudentResponseDTO update(Long id,StudentRequestDTO studentRequestDTO);
}
