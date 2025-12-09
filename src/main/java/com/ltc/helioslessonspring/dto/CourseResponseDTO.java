package com.ltc.helioslessonspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponseDTO {


    private Long id;

    private String courseName;

    private String studentName;

    private String studentSurname;
}
