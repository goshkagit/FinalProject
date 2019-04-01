package com.finalproject.upwork.services;

import com.finalproject.upwork.models.TaskModel;

import java.util.List;

public interface TaskFilterService {

    List<TaskModel> findAllByType(String type);

}
