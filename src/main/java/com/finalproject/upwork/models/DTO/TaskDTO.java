package com.finalproject.upwork.models.DTO;

import com.finalproject.upwork.validation.annotations.SpecialChars;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class TaskDTO {

    private long task_ID;

    private UserLoginDTO whoPosted;

    @Size(min = 3 , max = 25 )
    private String topic;

    @Size(min = 3 , max = 350)
    private String description;

    @SpecialChars
    private String hardnessLevel;

    @SpecialChars
    private String type;

    private Date deadline;

    @SpecialChars
    private int payment;
}
