package com.ltc.helioslessonspring.service.impl;

import com.ltc.helioslessonspring.dto.StudentRequestDTO;
import com.ltc.helioslessonspring.dto.StudentResponseDTO;
import com.ltc.helioslessonspring.exception.CourseNotFoundException;
import com.ltc.helioslessonspring.exception.UserNotFoundException;
import com.ltc.helioslessonspring.model.CourseEntity;
import com.ltc.helioslessonspring.model.StudentEntity;
import com.ltc.helioslessonspring.repository.CourseRepository;
import com.ltc.helioslessonspring.repository.StudentRepository;
import com.ltc.helioslessonspring.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;


//    @Override
//    public StudentResponseDTO create(StudentRequestDTO studentRequestDTO) {
//
//        StudentEntity student =  new StudentEntity();
//        student.setName(studentRequestDTO.getName());
//        student.setSurname(studentRequestDTO.getSurname());
//        student.setCourseStatus(studentRequestDTO.getCourseStatus());
//        student.setId(studentRequestDTO.getCourseId()); // course-un id-si verilmelidir
//        StudentEntity dbStudent = studentRepository.save(student);
//
//        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
//        studentResponseDTO.setId(dbStudent.getId());
//        studentResponseDTO.setName(dbStudent.getName());
//        studentResponseDTO.setSurname(dbStudent.getSurname());
//        studentResponseDTO.setCourseStatus(dbStudent.getCourseStatus());
//        studentResponseDTO.setCourseId(dbStudent.getCourse().getId());
//
//        return studentResponseDTO;
//
//    }


    @Override
    public StudentResponseDTO create(StudentRequestDTO studentRequestDTO) {

        // Course databazadan tapilmalidir ki , sonra ashagidaki emeliyyatlar icra edile bilsin
        CourseEntity course = courseRepository.findById(studentRequestDTO.getCourseId())
                .orElseThrow(() -> new RuntimeException(
                        "Course tapilmadi: " + studentRequestDTO.getCourseId()));

        //  Student entity yaradiriq burada ki Course obyektini set edenden sonra , biz id olaraq chagirsaq fetch ede bilek
        StudentEntity student = new StudentEntity();
        student.setName(studentRequestDTO.getName());
        student.setSurname(studentRequestDTO.getSurname());
        student.setCourseStatus(studentRequestDTO.getCourseStatus());
        student.setCourse(course);


        StudentEntity dbStudent = studentRepository.save(student);


        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
        studentResponseDTO.setId(dbStudent.getId());
        studentResponseDTO.setName(dbStudent.getName());
        studentResponseDTO.setSurname(dbStudent.getSurname());
        studentResponseDTO.setCourseStatus(dbStudent.getCourseStatus());
        studentResponseDTO.setCourseId(dbStudent.getCourse().getId());

        return studentResponseDTO;
    }


    @Override
    public List<StudentResponseDTO> getAll() {

        return studentRepository.findAll().stream()
                .map(student -> new StudentResponseDTO(
                        student.getId(),
                        student.getName(),
                        student.getSurname(),
                        student.getCourseStatus(),
                        student.getCourse() != null ? student.getCourse().getId() : null))
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
        studentResponseDTO.setCourseStatus(studentEntity.getCourseStatus());
        studentResponseDTO.setCourseId(studentEntity.getCourse().getId());

        return studentResponseDTO;
    }


    @Override
    public StudentResponseDTO update(Long id, StudentRequestDTO studentRequestDTO) {
        StudentEntity existStudent = studentRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("Istifadechi artiq var  ")
        );

        existStudent.setName(studentRequestDTO.getName());
        existStudent.setSurname(studentRequestDTO.getSurname());
        existStudent.setCourseStatus(studentRequestDTO.getCourseStatus());
        StudentEntity updatedStudent = studentRepository.save(existStudent);

        StudentResponseDTO studentResponseDTO = new StudentResponseDTO();
        studentResponseDTO.setId(updatedStudent.getId());
        studentResponseDTO.setName(updatedStudent.getName());
        studentResponseDTO.setSurname(updatedStudent.getSurname());
        studentResponseDTO.setCourseStatus(updatedStudent.getCourseStatus());

        return studentResponseDTO;

    }

    @Override
    public List<StudentResponseDTO> getStudentsByCourseId(Long courseId) {
        List<StudentEntity> students = studentRepository.findStudentsByCourseId(courseId);

        if(students.isEmpty()) {
            throw new CourseNotFoundException("Bu kursa aid telebeler tapilmadi: " + courseId);
        }

        return students.stream()
                .map(s -> new StudentResponseDTO(
                        s.getId(),
                        s.getName(),
                        s.getSurname(),
                        s.getCourseStatus(),
                        s.getCourse().getId()
                ))
                .toList();
    }


    @Override
    public void deleteById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new UserNotFoundException("Istifadechi yoxdur " + id);
        }
        studentRepository.deleteById(id);
    }

//    @Override
//    public List<StudentResponseDTO> getStudentsByCourseName(String courseName) {
//        List<StudentEntity> students = studentRepository.findStudentsByCourseName(courseName);
//
//        return students.stream().map(student -> new StudentResponseDTO(
//                student.getId(),
//                student.getName(),
//                student.getSurname(),
//                student.getCourseStatus(),
//                student.getCourse() != null ? student.getCourse().getId() : null
//        )).toList();
//    }
}
