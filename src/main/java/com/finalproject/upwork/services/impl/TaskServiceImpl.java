package com.finalproject.upwork.services.impl;

import com.finalproject.upwork.models.TaskModel;
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
    public void addTask(TaskModel taskModel, long userId) {

        taskModel.setWhoPosted(userService.getProfileById(userId));

        taskRepository.save(taskModel);
    }

    @Override
    public TaskModel getTask(long id) {
        return taskRepository.findById(id).get();
    }

    @Override
    public void updateTask(TaskModel taskModel, long taskId) {

        TaskModel model = taskRepository.findById(taskId).get();

        model.setTopic(taskModel.getTopic());
        model.setDeadline(taskModel.getDeadline());
        model.setDescription(taskModel.getDescription());
        model.setPayment(taskModel.getPayment());
        model.setType(taskModel.getType());
        model.setHardness(taskModel.getHardness());

        taskRepository.save(model);

    }

}
