package com.finalproject.upwork.services;


import com.finalproject.upwork.models.SubmittedModel;

import java.util.List;

public interface SubmitService {

    void submit(long taskId, long userId);

    List<SubmittedModel> findAllBySubmittedUsersId(long userId);

    List<SubmittedModel> findAllBySubmittedTaskId(long taskId);

}
