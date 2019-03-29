package com.finalproject.upwork.services;

import com.finalproject.upwork.models.DTO.TaskDTO;
import com.finalproject.upwork.models.TaskModel;

public interface TaskService {

    void addTask(TaskModel taskModel , TaskDTO taskDTO ,  long id);
    TaskModel getTask(long id);
}
