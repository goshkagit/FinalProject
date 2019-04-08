package com.finalproject.upwork.controllers;


import com.finalproject.upwork.exception.NotFoundException;
import com.finalproject.upwork.models.DTO.TaskDTO;
import com.finalproject.upwork.models.TaskModel;
import com.finalproject.upwork.services.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Secured(value = "ROLE_USER")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ModelMapper taskModelMapper;

    @Autowired
    private ModelMapper taskDTOModelMapper;


    @PostMapping("/addTask/{userId}")
    public ResponseEntity addTask(@Valid @RequestBody TaskDTO taskDTO, @PathVariable long userId) {

        TaskModel taskModel = taskModelMapper.map(taskDTO, TaskModel.class);

        taskService.addTask(taskModel, userId);

        return ResponseEntity.status(HttpStatus.CREATED).body("Task uploaded successfully");
    }


    @GetMapping("/getTaskByID/{whoPostedId}")
    public ResponseEntity getUser(@PathVariable long whoPostedId) {

        TaskModel task = taskService.getTask(whoPostedId);

        if (task == null) {
            throw new NotFoundException("There is no task with loginId :" + whoPostedId);
        }

        TaskDTO dto = taskDTOModelMapper.map(task, TaskDTO.class);

        return ResponseEntity.status(HttpStatus.OK).body(dto);

    }


    @PutMapping("/updateTask/{taskId}")
    public ResponseEntity updateTask(@Valid TaskDTO taskDTO, @PathVariable long taskId) {

        TaskModel taskModel = taskModelMapper.map(taskDTO, TaskModel.class);

        if (taskModel == null) {
            throw new NotFoundException("There is no task with loginId :" + taskId);
        }
        taskService.updateTask(taskModel, taskId);

        return ResponseEntity.status(HttpStatus.CREATED).body("Task updated successfully");
    }

@Secured("ROLE_ADMIN")
    @DeleteMapping("/deleteTask/{taskId}")
    public ResponseEntity deleteTask(@PathVariable long taskId) {

        taskService.deleteTask(taskId);

        return ResponseEntity.status(HttpStatus.OK).body("Task deleted successfully");
    }

}
