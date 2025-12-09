package com.ltc.helioslessonspring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequestDTO {



    @Size(min = 3 , max = 10)
    private String courseName;
    @Size(min = 3 , max = 10 , message = "Xahish edirem 3 herfden boyuk ad yazin")
    private String courseCode;

    @NotNull(message = "Mutleq daxilinde bir sheyler yazilmalidir")
    private String studentName;
    @NotBlank(message = "Bosh olmali deyil")
    private String studentSurname;

}
