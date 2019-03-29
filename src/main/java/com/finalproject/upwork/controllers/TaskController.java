package com.finalproject.upwork.controllers;


import com.finalproject.upwork.models.DTO.TaskDTO;
import com.finalproject.upwork.models.Hardness;
import com.finalproject.upwork.models.TaskModel;
import com.finalproject.upwork.models.Type;
import com.finalproject.upwork.models.UserProfileModel;
import com.finalproject.upwork.services.TaskService;
import com.finalproject.upwork.services.UserService;
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


    @PostMapping("/addTask/{user_id}")
    public ResponseEntity addTask(@Valid @RequestBody TaskDTO taskDTO, @PathVariable long user_id) {
        TaskModel taskModel = modelMapper.map(taskDTO, TaskModel.class);

        taskService.addTask(taskModel , taskDTO ,  user_id);

        return ResponseEntity.ok("Task uploaded successfully");
    }

    @GetMapping("/getTaskByID/{id}")
    public ResponseEntity getUser(@PathVariable long id) throws IOException {
        return ResponseEntity.ok(taskService.getTask(id).toString());
    }
}
