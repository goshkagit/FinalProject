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


    @PostMapping("/submit/{task_id}/{user_id}")
    public ResponseEntity submit(@PathVariable long task_id , @PathVariable  long user_id){

        submitService.submit(task_id, user_id);

        return ResponseEntity.ok("Submitted");
    }

}
