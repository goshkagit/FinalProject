package com.finalproject.upwork.controllers;


import com.finalproject.upwork.models.TaskModel;
import com.finalproject.upwork.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping("/addTask")
    public ResponseEntity addTask(@Valid @RequestBody TaskModel taskModel){
        taskService.addTask(taskModel);
        return ResponseEntity.ok("Task uploaded successfully");
    }
}
