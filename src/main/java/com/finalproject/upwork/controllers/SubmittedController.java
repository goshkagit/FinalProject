package com.finalproject.upwork.controllers;


import com.finalproject.upwork.exception.NotFoundException;
import com.finalproject.upwork.models.DTO.GetDTO.GetSubmissionDTO;
import com.finalproject.upwork.models.SubmittedModel;
import com.finalproject.upwork.services.SubmitService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Secured(value = "ROLE_USER")
public class SubmittedController {

    @Autowired
    SubmitService submitService;

    @Autowired
    ModelMapper modelMapper;


    @PostMapping("/submit/{taskId}/{userId}")
    public ResponseEntity submit(@PathVariable long taskId, @PathVariable long userId) {

        submitService.submit(taskId, userId);

        return ResponseEntity.status(HttpStatus.CREATED).body("Submitted");
    }


    @GetMapping("/submissions/{userId}")
    public ResponseEntity getAllSubmissions(@PathVariable long userId) {

        List<SubmittedModel> all = submitService.findAllBySubmittedUsersId(userId);
        if (all.isEmpty()) {
            throw new NotFoundException("Your not submit for any work yet");
        }
        List<GetSubmissionDTO> allDTO = all
                .stream()
                .map(SubmittedModel -> modelMapper.map(SubmittedModel, GetSubmissionDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(allDTO);
    }


    @GetMapping("/userSubmissions/{taskId}")
    public ResponseEntity getAllUserSubmissions(@PathVariable long taskId) {

        List<SubmittedModel> all = submitService.findAllBySubmittedTaskId(taskId);

        if (all.isEmpty()) {
            throw new NotFoundException("This task have not any submissions yet");
        }
        List<GetSubmissionDTO> allDTO = all
                .stream()
                .map(SubmittedModel -> modelMapper.map(SubmittedModel, GetSubmissionDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(allDTO);
    }

}
