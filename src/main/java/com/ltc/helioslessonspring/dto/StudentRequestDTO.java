package com.ltc.helioslessonspring.dto;

import com.ltc.helioslessonspring.model.enumaration.CourseStatus;
import lombok.Data;

@Data
public class StudentRequestDTO {


    private String name;
    private String surname;
    private CourseStatus courseStatus;
    private Long courseId;
}
