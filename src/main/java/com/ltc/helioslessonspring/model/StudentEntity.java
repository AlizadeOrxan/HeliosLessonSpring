package com.ltc.helioslessonspring.model;

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

    private String name;
    private String surname;





}
