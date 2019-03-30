package com.finalproject.upwork.controllers;


import com.finalproject.upwork.services.SubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubmittedController {

    @Autowired
    SubmitService submitService;


    @PostMapping("/submit/{taskId}/{userId}")
    public ResponseEntity submit(@PathVariable long taskId , @PathVariable  long userId){

        submitService.submit(taskId, userId);

        return ResponseEntity.ok("Submitted");
    }

}
