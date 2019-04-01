package com.finalproject.upwork.controllers;


import com.finalproject.upwork.models.DTO.TaskDTO;
import com.finalproject.upwork.models.TaskModel;
import com.finalproject.upwork.models.enums.Hardness;
import com.finalproject.upwork.models.enums.Type;
import com.finalproject.upwork.services.TaskService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ModelMapper taskModelMapper;


    @PostMapping("/addTask/{userId}")

    public ResponseEntity addTask(@Valid @RequestBody TaskDTO taskDTO, @PathVariable long userId) {

        TaskModel taskModel = taskModelMapper.map(taskDTO, TaskModel.class);

        taskService.addTask(taskModel, userId);

        return ResponseEntity.ok("Task uploaded successfully");
    }

    @GetMapping("/getTaskByID/{whoPostedId}")
    public ResponseEntity getUser(@PathVariable long whoPostedId) {

        return ResponseEntity.ok(taskService.getTask(whoPostedId));

    }



    @PutMapping("/updateTask/{taskId}")
    public  ResponseEntity updateTask(@Valid TaskDTO taskDTO  , @PathVariable long taskId){


        TaskModel taskModel = taskModelMapper.map(taskDTO, TaskModel.class);

        taskService.updateTask(taskModel , taskId);

        return ResponseEntity.ok("Task updated successfully");
    }

}
