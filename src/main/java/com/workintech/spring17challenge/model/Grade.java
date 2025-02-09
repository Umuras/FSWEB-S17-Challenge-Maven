package com.workintech.spring17challenge.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@Data
public class Grade {
    private int coefficient;
    private String note;

    public Grade(int coefficient, String note)
    {
        this.coefficient = coefficient;
        this.note = note;
    }
}
