package com.finalproject.upwork.models.DTO;

import lombok.Data;

@Data
public class SubmitedDTO {

    private Long id;

    private UserLoginDTO submited_user;

    private TaskDTO task_id;

}
