package com.finalproject.upwork.models.DTO;

import com.finalproject.upwork.validation.annotations.SpecialChars;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class TaskDTO {

    private long taskID;

    @SpecialChars
    @com.finalproject.upwork.validation.annotations.Size
    private String topic;


    @com.finalproject.upwork.validation.annotations.Size
    private String description;

    @SpecialChars
    private String hardness;

    @SpecialChars
    private String type;

    private LocalDate deadline;


    private int payment;
}
