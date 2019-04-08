package com.finalproject.upwork.services.impl;

import com.finalproject.upwork.exception.CantSubmitException;
import com.finalproject.upwork.exception.NotFoundException;
import com.finalproject.upwork.models.SubmittedModel;
import com.finalproject.upwork.models.TaskModel;
import com.finalproject.upwork.models.UserProfileModel;
import com.finalproject.upwork.repositories.SubmittedRepository;
import com.finalproject.upwork.repositories.TaskRepository;
import com.finalproject.upwork.repositories.UserProfileRepository;
import com.finalproject.upwork.services.SubmitService;
import com.finalproject.upwork.services.TaskService;
import com.finalproject.upwork.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SubmitServiceImpl implements SubmitService {

    @Autowired
    SubmittedRepository submittedRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TaskService taskService;

    @Autowired
    UserService userService;


    @Override
    public void submit(long taskId, long userId) {

        TaskModel task = taskService.getTask(taskId);

        UserProfileModel profile = userService.getProfileById(userId);


        if (task == null) {
            throw new NotFoundException("There is no task with loginId :" + taskId);
        } else if (profile == null) {
            throw new NotFoundException("There is no task with user :" + taskId);
        }

        if (profile.getProfileId() == task.getWhoPosted().getProfileId()) {
            throw new CantSubmitException();
        }

        SubmittedModel submittedModel = new SubmittedModel();

        submittedModel.setSubmittedTaskId(task);

        submittedModel.setSubmittedUsersId(profile);

        submittedRepository.save(submittedModel);
    }

    @Override
    public List<SubmittedModel> findAllBySubmittedUsersId(long userId) {
        return submittedRepository.findAllBySubmittedUsersId(userProfileRepository.findById(userId).orElse(null));
    }

    @Override
    public List<SubmittedModel> findAllBySubmittedTaskId(long taskId) {
        return submittedRepository.findAllBySubmittedTaskId(taskRepository.findById(taskId).orElse(null));
    }

}

