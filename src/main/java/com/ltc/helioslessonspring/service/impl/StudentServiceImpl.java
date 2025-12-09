package com.ltc.helioslessonspring.service.impl;

import com.ltc.helioslessonspring.dto.StudentRequestDTO;
import com.ltc.helioslessonspring.dto.StudentResponseDTO;
import com.ltc.helioslessonspring.exception.UserNotFoundException;
import com.ltc.helioslessonspring.model.StudentEntity;
import com.ltc.helioslessonspring.repository.StudentRepository;
import com.ltc.helioslessonspring.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;


    @Override
    public StudentResponseDTO create(StudentRequestDTO studentRequestDTO) {

        StudentEntity student =  new StudentEntity();
        student.setName(studentRequestDTO.getName());
        student.setSurname(studentRequestDTO.getSurname());
        StudentEntity dbStudent = studentRepository.save(student);

        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
        studentResponseDTO.setId(dbStudent.getId());
        studentResponseDTO.setName(dbStudent.getName());
        studentResponseDTO.setSurname(dbStudent.getSurname());

        return studentResponseDTO;

    }


    @Override
    public List<StudentResponseDTO> getAll() {

        return studentRepository.findAll().stream()
                .map(student -> new StudentResponseDTO(
                        student.getId(),
                        student.getName(),
                        student.getSurname()))
                .toList();
    }


    @Override
    public StudentResponseDTO getById(Long id) {
        StudentEntity studentEntity = studentRepository.findById(id).orElseThrow(
                ()-> new UserNotFoundException("Istifadechi tapilmadi ")
        );

        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
        studentResponseDTO.setId(studentEntity.getId());
        studentResponseDTO.setName(studentEntity.getName());
        studentResponseDTO.setSurname(studentEntity.getSurname());

        return studentResponseDTO;
    }


    @Override
    public StudentResponseDTO update(Long id, StudentRequestDTO studentRequestDTO) {
        StudentEntity existStudent = studentRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("Istifadechi artiq var  ")
        );

        existStudent.setName(studentRequestDTO.getName());
        existStudent.setSurname(studentRequestDTO.getSurname());
        StudentEntity updatedStudent = studentRepository.save(existStudent);

        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
        studentResponseDTO.setId(updatedStudent.getId());
        studentResponseDTO.setName(updatedStudent.getName());
        studentResponseDTO.setSurname(updatedStudent.getSurname());

        return studentResponseDTO;

    }


    @Override
    public void deleteById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new UserNotFoundException("Istifadechi yoxdur " + id);
        }
        studentRepository.deleteById(id);
    }
}
