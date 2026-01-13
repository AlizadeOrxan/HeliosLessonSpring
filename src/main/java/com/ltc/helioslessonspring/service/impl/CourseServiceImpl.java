package com.ltc.helioslessonspring.service.impl;

import com.ltc.helioslessonspring.dto.CourseRequestDTO;
import com.ltc.helioslessonspring.dto.CourseResponseDTO;
import com.ltc.helioslessonspring.exception.CourseNotFoundException;
import com.ltc.helioslessonspring.model.CourseEntity;
import com.ltc.helioslessonspring.repository.CourseRepository;
import com.ltc.helioslessonspring.repository.StudentRepository;
import com.ltc.helioslessonspring.service.CourseService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public CourseResponseDTO create(CourseRequestDTO courseRequestDTO) {
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setCourseName(courseRequestDTO.getCourseName());
        courseEntity.setCourseCode(courseRequestDTO.getCourseCode());
        courseEntity.setStudentName(courseRequestDTO.getStudentName());
        courseEntity.setStudentSurname(courseRequestDTO.getStudentSurname());

        CourseEntity dbSaving = courseRepository.save(courseEntity);

        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();

        courseResponseDTO.setId(dbSaving.getId());
        courseResponseDTO.setCourseName(dbSaving.getCourseName());
        courseResponseDTO.setCourseCode(dbSaving.getCourseCode());
        courseResponseDTO.setStudentName(dbSaving.getStudentName());
        courseResponseDTO.setStudentSurname(dbSaving.getStudentSurname());

        return courseResponseDTO;



    }

    @Override
    public CourseResponseDTO findById(Long id) {
        CourseEntity courseEntity = courseRepository.findById(id).orElseThrow(()->
                new CourseNotFoundException("Istifadechi tapilmadi " + id));

        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();

        courseResponseDTO.setId(courseEntity.getId());
        courseResponseDTO.setCourseName(courseEntity.getCourseName());
        courseResponseDTO.setCourseCode(courseEntity.getCourseCode());
        courseResponseDTO.setStudentName(courseEntity.getStudentName());
        courseResponseDTO.setStudentSurname(courseEntity.getStudentSurname());

        return courseResponseDTO;

    }

    @Override
    public Page<CourseResponseDTO> getAll(Pageable pageable) {
        return courseRepository.findAll(pageable)
                .map(course -> new CourseResponseDTO(
                        course.getId(),
                        course.getCourseName(),
                        course.getCourseCode(),
                        course.getStudentName(),
                        course.getStudentSurname()
                ));
    }

    @Override
    public CourseResponseDTO update(Long id, CourseRequestDTO courseRequestDTO) {
        CourseEntity existDatabase = courseRepository.findById(id).orElseThrow(
                ()-> new CourseNotFoundException("Teessuf ki istifadechi tapilmadi " + id)
        );

        existDatabase.setCourseName(courseRequestDTO.getCourseName());
        existDatabase.setCourseCode(courseRequestDTO.getCourseCode());
        existDatabase.setStudentName(courseRequestDTO.getStudentName());
        existDatabase.setStudentSurname(courseRequestDTO.getStudentSurname());

        CourseEntity dbSaving = courseRepository.save(existDatabase);

        CourseResponseDTO courseResponseDTO = new CourseResponseDTO();

        courseResponseDTO.setId(dbSaving.getId());
        courseResponseDTO.setCourseName(dbSaving.getCourseName());
        courseResponseDTO.setCourseCode(dbSaving.getCourseCode());
        courseResponseDTO.setStudentName(dbSaving.getStudentName());
        courseResponseDTO.setStudentSurname(dbSaving.getStudentSurname());

        return courseResponseDTO;

    }

    @Override
    public void deleteById(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException("XETA ! Bu id-si olan istifadechi yoxdur " + id);
        }
        courseRepository.deleteById(id);
    }
}
