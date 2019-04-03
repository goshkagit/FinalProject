package com.finalproject.upwork.models.DTO;

import com.finalproject.upwork.validation.annotations.SpecialChars;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class TaskDTO {

    private long taskID;

    @Size(min = 3, max = 55)
    @SpecialChars
    private String topic;

    @Size(min = 3, max = 350)
    @NotBlank
    private String description;

    @SpecialChars
    private String hardness;

    @SpecialChars
    private String type;

    private LocalDate deadline;

    private int payment;
}
