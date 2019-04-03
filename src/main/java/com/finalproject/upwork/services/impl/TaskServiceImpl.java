package com.finalproject.upwork.services.impl;

import com.finalproject.upwork.exception.NotFoundException;
import com.finalproject.upwork.models.SubmittedModel;
import com.finalproject.upwork.models.TaskModel;
import com.finalproject.upwork.repositories.SubmittedRepository;
import com.finalproject.upwork.repositories.TaskRepository;
import com.finalproject.upwork.services.TaskService;
import com.finalproject.upwork.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    UserService userService;

    @Autowired
    SubmittedRepository submittedRepository;

    @Override
    public void addTask(TaskModel taskModel, long userId) {

        taskModel.setWhoPosted(userService.getProfileById(userId));

        taskRepository.save(taskModel);
    }

    @Override
    public TaskModel getTask(long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public void updateTask(TaskModel taskModel, long taskId) {

        TaskModel model = taskRepository.findById(taskId).orElse(null);

        if(model == null){
            throw new NotFoundException("There is no task with id :"+ taskId);
        }

        model.setTopic(model.getTopic());
        model.setDeadline(model.getDeadline());
        model.setDescription(model.getDescription());
        model.setPayment(model.getPayment());
        model.setType(model.getType());
        model.setHardness(model.getHardness());

        taskRepository.save(model);

    }

    @Override
    public void deleteTask(long id) {

        TaskModel taskModel = taskRepository.findById(id).orElse(null);

        if(taskModel == null){
            throw new NotFoundException("There is no task with id :"+ id);
        }
        List<SubmittedModel> submitted = submittedRepository.findAllBySubmittedTaskId(taskModel);

        submitted.forEach(SubmittedModel -> submittedRepository.delete(SubmittedModel));

        taskRepository.delete(taskModel);
    }

}
