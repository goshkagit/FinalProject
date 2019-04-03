package com.finalproject.upwork.controllers;


import com.finalproject.upwork.exception.NotFoundException;
import com.finalproject.upwork.models.DTO.TaskDTO;
import com.finalproject.upwork.models.TaskModel;
import com.finalproject.upwork.services.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ModelMapper taskModelMapper;

    @Secured("ROLE_USER")
    @PostMapping("/addTask/{userId}")
    public ResponseEntity addTask(@Valid @RequestBody TaskDTO taskDTO, @PathVariable long userId) {

        TaskModel taskModel = taskModelMapper.map(taskDTO, TaskModel.class);

        taskService.addTask(taskModel, userId);

        return ResponseEntity.ok("Task uploaded successfully");
    }

    @GetMapping("/getTaskByID/{whoPostedId}")
    public ResponseEntity getUser(@PathVariable long whoPostedId) {

        TaskModel task = taskService.getTask(whoPostedId);

        if (task == null) {
            throw new NotFoundException("There is no task with id :" + whoPostedId);
        }
        return ResponseEntity.ok(task);

    }


    @PutMapping("/updateTask/{taskId}")
    public ResponseEntity updateTask(@Valid TaskDTO taskDTO, @PathVariable long taskId) {

        TaskModel taskModel = taskModelMapper.map(taskDTO, TaskModel.class);

        if (taskModel == null) {
            throw new NotFoundException("There is no task with id :" + taskId);
        }
        taskService.updateTask(taskModel, taskId);

        return ResponseEntity.ok("Task updated successfully");
    }


    @DeleteMapping("/deleteTask/{taskId}")
    public ResponseEntity deleteTask(@PathVariable long taskId) {

        taskService.deleteTask(taskId);

        return ResponseEntity.ok("Task deleted successfully");
    }

}
