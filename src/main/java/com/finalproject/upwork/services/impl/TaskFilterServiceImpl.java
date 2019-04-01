package com.finalproject.upwork.services.impl;

import com.finalproject.upwork.models.TaskModel;
import com.finalproject.upwork.models.enums.Type;
import com.finalproject.upwork.repositories.TaskRepository;
import com.finalproject.upwork.services.TaskFilterService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TaskFilterServiceImpl implements TaskFilterService {

    @Autowired
    private TaskRepository taskRepository;


    @Override
    public List<TaskModel> findAllByType(String type) {
        return taskRepository.findAllByType(Type.valueOf(type.toUpperCase()));
    }
}
