package com.workintech.spring17challenge.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDTO {
    private Course course;
    private  int totalGpa;

    public CourseDTO(Course course, int totalGpa)
    {
        this.course = course;
        this.totalGpa = totalGpa;
    }
}
