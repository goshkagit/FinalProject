package com.finalproject.upwork.services;

import com.finalproject.upwork.models.TaskModel;

public interface TaskService {

    void addTask(TaskModel taskModel, long id);

    TaskModel getTask(long id);

    void updateTask(TaskModel taskModel , long id);
}
