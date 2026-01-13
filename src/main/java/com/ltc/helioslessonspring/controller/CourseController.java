package com.ltc.helioslessonspring.controller;

import com.ltc.helioslessonspring.dto.CourseRequestDTO;
import com.ltc.helioslessonspring.dto.CourseResponseDTO;
import com.ltc.helioslessonspring.service.impl.CourseServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
@Tag(name = "Course Management System ", description = "Course istifadechilerini idare eden sistemdir")
public class CourseController {
    private final CourseServiceImpl courseService;

    public CourseController(CourseServiceImpl courseService) {
        this.courseService = courseService;
    }


/// bu Method-da qeyd olunan annotasiyalari digerlerine de uygunlashdirin
    @PostMapping("/create")
    @ApiResponse(description = "Istifadechi yaradildi", responseCode = "201")
    @Operation(summary = "Istifadechinin create olunmasi")
    public ResponseEntity<CourseResponseDTO> create(@Valid @RequestBody CourseRequestDTO courseRequestDTO) {
        CourseResponseDTO course =  courseService.create(courseRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }




    /// Bu qayda ile yazilarsa , bize qayidan cavab ashagidaki kimi deyil
    ///     "courseName": "Java",
    ///     "studentName": " Huseyn ",
    ///     "studentSurname": " Alizade"
    ///
    /// Bu qayda da qayidacaq
    ///  Course id-si -> 1
    ///  Course name Java
    ///  Student Name Huseyn
//    public ResponseEntity<String> create(@Valid @RequestBody CourseRequestDTO courseRequestDTO) {
//        CourseResponseDTO course =  courseService.create(courseRequestDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body(
//                "Course id-si -> " +  course.getId()
//                        + "\nCourse name " + course.getCourseName() +
//                        "\nStudent Name " + course.getStudentName());
//    }






    @Operation(summary = "Istifadechilerin pagination halinda gosterilmesi")
    @GetMapping("/getAll")
    public ResponseEntity<Page<CourseResponseDTO>> getAll(@ParameterObject
                                                              @PageableDefault(page = 0,size = 10,sort = "id",direction = Sort.Direction.DESC)
                                                              Pageable pageable) {
        Page<CourseResponseDTO> courseResponseDTOS = courseService.getAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(courseResponseDTOS);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Id uzre search", description = "Bu method vasitesi ile biz id-e esasesn istifadechini tapiriq")
    public ResponseEntity<CourseResponseDTO> findById(@PathVariable Long id) {
//        CourseResponseDTO courseResponseDTO = courseService.findById(id);
//        return ResponseEntity.status(HttpStatus.OK).body(courseResponseDTO);

        CourseResponseDTO courseResponseDTOS = courseService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(courseResponseDTOS);



    }


    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> update(@PathVariable Long id , @Valid @RequestBody CourseRequestDTO courseRequestDTO) {
        CourseResponseDTO courseResponseDTO =  courseService.update(id,courseRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(courseResponseDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> delete(@PathVariable Long id) {
        courseService.deleteById(id);
        return ResponseEntity.notFound().build();
    }



}
