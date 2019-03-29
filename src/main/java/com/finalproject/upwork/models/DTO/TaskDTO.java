package com.finalproject.upwork.models.DTO;

import com.finalproject.upwork.validation.annotations.SpecialChars;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class TaskDTO {

    private long task_ID;


    @Size(min = 3 , max = 55)
    @NotBlank
    private String topic;

    @Size(min = 3 , max = 350)
    @NotBlank
    private String description;

    @SpecialChars
    @NotBlank
    private String hardness;

    @SpecialChars
    @NotBlank
    private String type;


    private LocalDate deadline;

    private int payment;
}
