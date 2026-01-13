package com.ltc.helioslessonspring.service;

import com.ltc.helioslessonspring.dto.CourseRequestDTO;
import com.ltc.helioslessonspring.dto.CourseResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CourseService {


    CourseResponseDTO create (CourseRequestDTO courseRequestDTO);
    CourseResponseDTO findById(Long id);
    Page<CourseResponseDTO> getAll(Pageable pageable);
    CourseResponseDTO update(Long id,CourseRequestDTO courseRequestDTO);
    void deleteById(Long id);
}
