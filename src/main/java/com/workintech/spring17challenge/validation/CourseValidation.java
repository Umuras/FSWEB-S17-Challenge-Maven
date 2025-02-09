package com.workintech.spring17challenge.validation;

import com.workintech.spring17challenge.exceptions.ApiException;
import com.workintech.spring17challenge.model.Course;
import org.springframework.http.HttpStatus;

import java.util.List;

public class CourseValidation {
    public static void isValidId(Integer id)
    {
        if(id == null || id <= 0)
        {
            throw new ApiException("id is greater than 0 or id is not valid: " + id, HttpStatus.BAD_REQUEST);
        }
    }

    public static void coursesSizeIsNotGreaterOrEqualId(Integer id, List<Course> courses)
    {
        if(id >= courses.size())
        {
            throw new ApiException("id must be lower than courses size",HttpStatus.BAD_REQUEST);
        }
    }

    public static void idIsNotEqualZero(Integer updatingCourseId)
    {
        if(updatingCourseId == 0)
        {
            throw new ApiException("There is no course in this id",HttpStatus.NOT_FOUND);
        }
    }

    public static void wrongCreditNumber(Integer credit)
    {
        if(credit > 4){
            throw new ApiException("Wrong credit value",HttpStatus.BAD_REQUEST);
        }
    }

    public static void courseNameNullControl(String courseName)
    {
        if (courseName == null || courseName.isEmpty())
        {
            throw new ApiException("Course name is not empty or null",HttpStatus.BAD_REQUEST);
        }
    }

    public  static void courseNullControl(Course course)
    {
        if(course == null)
        {
            throw new ApiException("Course is not null", HttpStatus.BAD_REQUEST);
        }
    }
}
