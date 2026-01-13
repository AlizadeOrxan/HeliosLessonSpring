package com.ltc.helioslessonspring.dto;

import com.ltc.helioslessonspring.model.enumaration.CourseStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDTO {

    private Long id;
    private String name;
    private String surname;
    private CourseStatus courseStatus;
    private Long courseId;


}
