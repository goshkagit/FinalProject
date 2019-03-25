package com.finalproject.upwork.repositories;

import com.finalproject.upwork.models.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel , Long> {

}
