package com.ltc.helioslessonspring.model;

import com.ltc.helioslessonspring.model.enumaration.CourseStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Students")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CourseStatus courseStatus;

    @Column(length = 100, nullable = false , name = "project_name")
    private String name;

    @Column(length = 50, nullable = false)
    private String surname;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private CourseEntity course;



}
