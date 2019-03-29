package com.finalproject.upwork.services.impl;

import com.finalproject.upwork.models.DTO.TaskDTO;
import com.finalproject.upwork.models.Hardness;
import com.finalproject.upwork.models.TaskModel;
import com.finalproject.upwork.models.Type;
import com.finalproject.upwork.models.UserProfileModel;
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
    public void addTask(TaskModel taskModel , TaskDTO taskDTO ,long id) {

        taskModel.setWho_posted(userService.getProfileById(id));

        taskModel.setType(Type.valueOf(taskDTO.getType().toUpperCase()));

        taskModel.setHardness(Hardness.valueOf(taskDTO.getHardness().toUpperCase()));

        taskRepository.save(taskModel);
    }

    @Override
    public TaskModel getTask(long id) {
        return taskRepository.findById(id).get();
    }
}
