package com.finalproject.upwork.controllers;


import com.finalproject.upwork.models.DTO.TaskDTO;
import com.finalproject.upwork.models.TaskModel;
import com.finalproject.upwork.services.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@RestController
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    private ModelMapper modelMapper;


    @PostMapping("/addTask")
    public ResponseEntity addTask(@Valid @RequestBody TaskDTO taskDTO){
        TaskModel taskModel = modelMapper.map(taskDTO , TaskModel.class);
        taskService.addTask(taskModel);
        return ResponseEntity.ok("Task uploaded successfully");
    }

    @GetMapping("/getTaskByID/{id}")
    public ResponseEntity getUser(@PathVariable long id) throws IOException {
        return ResponseEntity.ok(taskService.getTask(id).toString());
    }
}
