package com.ltc.helioslessonspring.service;

import com.ltc.helioslessonspring.dto.CourseRequestDTO;
import com.ltc.helioslessonspring.dto.CourseResponseDTO;

import java.util.List;

public interface CourseService {


    CourseResponseDTO create (CourseRequestDTO courseRequestDTO);
    CourseResponseDTO findById(Long id);
    List<CourseResponseDTO> getAll();
    CourseResponseDTO update(Long id,CourseRequestDTO courseRequestDTO);
    void deleteById(Long id);
}
