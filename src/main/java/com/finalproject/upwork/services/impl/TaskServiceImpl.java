package com.finalproject.upwork.services.impl;

import com.finalproject.upwork.models.DTO.TaskDTO;
import com.finalproject.upwork.models.enums.Hardness;
import com.finalproject.upwork.models.TaskModel;
import com.finalproject.upwork.models.enums.Type;
import com.finalproject.upwork.repositories.TaskRepository;
import com.finalproject.upwork.services.TaskService;
import com.finalproject.upwork.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    UserService userService;

    @Override
    public void addTask(TaskModel taskModel  ,long userId) {

        taskModel.setWhoPosted(userService.getProfileById(userId));

        taskRepository.save(taskModel);
    }

    @Override
    public TaskModel getTask(long id) {
        return taskRepository.findById(id).get();
    }
}
