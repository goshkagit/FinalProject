package com.finalproject.upwork.services.impl;

import com.finalproject.upwork.models.SubmittedModel;
import com.finalproject.upwork.models.TaskModel;
import com.finalproject.upwork.models.UserProfileModel;
import com.finalproject.upwork.repositories.SubmittedRepository;
import com.finalproject.upwork.services.SubmitService;
import com.finalproject.upwork.services.TaskService;
import com.finalproject.upwork.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SubmitServiceImpl implements SubmitService {

    @Autowired
    SubmittedRepository submittedRepository;

    @Autowired
    TaskService taskService;

    @Autowired
    UserService userService;


    @Override
    public void submit(long taskId, long userId) {

        TaskModel task = taskService.getTask(taskId);

        UserProfileModel profile = userService.getProfileById(userId);

        SubmittedModel submittedModel = new SubmittedModel();

        submittedModel.setSubmittedTaskId(task);

        submittedModel.setSubmittedUsersId(profile);

        submittedRepository.save(submittedModel);
    }

}

