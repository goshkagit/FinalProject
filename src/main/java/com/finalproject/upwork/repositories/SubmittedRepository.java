package com.finalproject.upwork.repositories;

import com.finalproject.upwork.models.SubmittedModel;
import com.finalproject.upwork.models.TaskModel;
import com.finalproject.upwork.models.UserProfileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmittedRepository extends JpaRepository<SubmittedModel, Long> {

    List<SubmittedModel> findAllBySubmittedUsersId(UserProfileModel userProfileModel);

    List<SubmittedModel> findAllBySubmittedTaskId(TaskModel taskModel);

    SubmittedModel findAllBySubmittedTaskIdAndSubmittedUsersId(TaskModel taskModel, UserProfileModel userProfileModel);
}
