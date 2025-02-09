package com.workintech.spring17challenge.controller;

import com.workintech.spring17challenge.exceptions.ApiException;
import com.workintech.spring17challenge.model.Course;
import com.workintech.spring17challenge.model.CourseGpa;
import com.workintech.spring17challenge.model.CourseDTO;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private List<Course> courses;

    private CourseGpa lowCourseGpa;
    private CourseGpa mediumCourseGpa;
    private CourseGpa highCourseGpa;

    @Autowired
    public CourseController(@Qualifier("lowCourseGpa") CourseGpa lowCourseGpa,
                            @Qualifier("mediumCourseGpa") CourseGpa mediumCourseGpa,
                            @Qualifier("highCourseGpa") CourseGpa highCourseGpa)
    {
        this.lowCourseGpa = lowCourseGpa;
        this.mediumCourseGpa = mediumCourseGpa;
        this.highCourseGpa = highCourseGpa;
    }
    @PostConstruct
    public void init()
    {
        courses = new LinkedList<>();
    }


    @GetMapping()
    public List<Course> findAllCourses()
    {
        return courses;
    }

    @GetMapping("/{name}")
    public Course findCourseByName(@PathVariable String name)
    {
        for (int i = 0; i < courses.size(); i++) {
            if(courses.get(i).getName().equals(name))
            {
                return courses.get(i);
            }
        }
        throw new ApiException("This course is not exist", HttpStatus.NOT_FOUND);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CourseDTO save(@RequestBody Course course)
    {
            if(course.getCredit() <= 2)
            {
                CourseDTO courseResponse = new CourseDTO(course,
                        course.getGrade().getCoefficient() * course.getCredit() * lowCourseGpa.getGpa());
                courses.add(course);
                return courseResponse;
            } else if (course.getCredit() == 3) {
                CourseDTO courseResponse = new CourseDTO(course,
                        course.getGrade().getCoefficient() * course.getCredit() * mediumCourseGpa.getGpa());
                courses.add(course);
                return courseResponse;
            } else if (course.getCredit() == 4) {
                CourseDTO courseResponse = new CourseDTO(course,
                        course.getGrade().getCoefficient() * course.getCredit() * highCourseGpa.getGpa());
                courses.add(course);
                return courseResponse;
            }else{
                throw new ApiException("Wrong credit value",HttpStatus.BAD_REQUEST);
            }
    }

    @PutMapping("/{id}")
    public CourseDTO update(@PathVariable Integer id, @RequestBody Course course)
    {
        if(id <= 0)
        {
            throw new ApiException("id is greater than 0",HttpStatus.BAD_REQUEST);
        }
        if(id >= courses.size())
        {
            throw new ApiException("id must be lower than courses size",HttpStatus.BAD_REQUEST);
        }

        int updatingCourseId = 0;
        for (int i = 0; i < courses.size(); i++) {
            if(courses.get(i).getId() == course.getId())
            {
                updatingCourseId = course.getId();
            }
        }
        if(updatingCourseId == 0)
        {
            throw new ApiException("There is no course in this id",HttpStatus.NOT_FOUND);
        }
        if(course.getCredit() <= 2)
        {
            CourseDTO courseResponse = new CourseDTO(course,
                    course.getGrade().getCoefficient() * course.getCredit() * lowCourseGpa.getGpa());
            courses.set(updatingCourseId,course);
            return courseResponse;
        } else if (course.getCredit() == 3) {
            CourseDTO courseResponse = new CourseDTO(course,
                    course.getGrade().getCoefficient() * course.getCredit() * mediumCourseGpa.getGpa());
            courses.set(updatingCourseId,course);
            return courseResponse;
        } else if (course.getCredit() == 4) {
            CourseDTO courseResponse = new CourseDTO(course,
                    course.getGrade().getCoefficient() * course.getCredit() * highCourseGpa.getGpa());
            courses.set(updatingCourseId,course);
            return courseResponse;
        }
        throw new ApiException("Wrong credit value",HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public Course delete(@PathVariable Integer id)
    {
        if(id <= 0)
        {
            throw new ApiException("id is greater than 0",HttpStatus.BAD_REQUEST);
        }
        Course course = courses.get(id);
        courses.remove(id);
        return course;
    }
}
