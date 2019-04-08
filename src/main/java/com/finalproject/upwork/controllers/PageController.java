package com.finalproject.upwork.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Secured("ROLE_USER")
public class PageController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/addTask")
    public String addTask() {
        return "postTask";
    }

}
