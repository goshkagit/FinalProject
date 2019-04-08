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
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Secured(value = "ROLE_USER")
public class SubmittedController {

    @Autowired
    SubmitService submitService;

    @Autowired
    ModelMapper modelMapper;


    @PostMapping("/submit/{taskId}/{profileId}")
    public ResponseEntity submit(@PathVariable long taskId, @PathVariable long profileId) {

        submitService.submit(taskId, profileId);

        return ResponseEntity.status(HttpStatus.CREATED).body("Submitted");
    }


    @GetMapping("/submissions/{profileId}")
    public ResponseEntity getAllSubmissions(@PathVariable long profileId) {

        List<SubmittedModel> all = submitService.findAllBySubmittedUsersId(profileId);
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

    @DeleteMapping("/unSubmit/{taskId}/{profileId}")
    public ResponseEntity unSubmit(@PathVariable long taskId , @PathVariable long profileId) {

      submitService.unSubmit(taskId , profileId);

        return ResponseEntity.status(HttpStatus.OK).body("Unsubmitted successfully");
    }

}
