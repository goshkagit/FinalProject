package com.finalproject.upwork.configuration;


import com.finalproject.upwork.models.SubmittedModel;
import com.finalproject.upwork.models.TaskModel;
import com.finalproject.upwork.models.UserLoginModel;
import com.finalproject.upwork.models.UserProfileModel;
import com.finalproject.upwork.models.enums.Hardness;
import com.finalproject.upwork.models.enums.Roles;
import com.finalproject.upwork.models.enums.Type;
import com.finalproject.upwork.repositories.SubmittedRepository;
import com.finalproject.upwork.repositories.TaskRepository;
import com.finalproject.upwork.repositories.UserLoginRepository;
import com.finalproject.upwork.repositories.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataBaseConfig implements ApplicationRunner {

    @Autowired
    UserLoginRepository userLoginRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    SubmittedRepository submittedRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        UserLoginModel save = userLoginRepository.save(new UserLoginModel((long) 1, "admin", Roles.ROLE_ADMIN, "admin"));
        UserLoginModel save2 = userLoginRepository.save(new UserLoginModel((long) 2, "Olya123", Roles.ROLE_USER, "qwerty123"));
        UserLoginModel save3 = userLoginRepository.save(new UserLoginModel((long) 2, "Olya123", Roles.ROLE_USER, "qwerty123"));
        UserLoginModel save4 = userLoginRepository.save(new UserLoginModel((long) 3, "zebra12", Roles.ROLE_USER, "zxcvb456"));


        userProfileRepository.save(new UserProfileModel((long) 4, save, "admiin", Type.JAVA, "http://google.com", "admin", "admin156@gmail.com"));
        UserProfileModel profile = userProfileRepository.save(new UserProfileModel((long) 5, save2, "Freddy", Type.MANAGEMENT, "http://googlePlayMusic.com/Queen", "Mercury", "freddy.100@gmail.com"));
        UserProfileModel profile2 = userProfileRepository.save(new UserProfileModel((long) 6, save3, "Olya", Type.DESIGN, "http://linkedIn.com", "Stark", "olya1@gmail.com"));
        UserProfileModel profile3 = userProfileRepository.save(new UserProfileModel((long) 7, save4, "Samon", Type.PHOTO_RETOUCH, "http://behance.com", "Fury", "john156@gmail.com"));


        TaskModel task = taskRepository.save(new TaskModel(8, "Stock photos retouching", " I need a freelancer who might retouch 50 photos due next week ", Hardness.MEDIUM, Type.PHOTO_RETOUCH, LocalDate.ofEpochDay(2019 - 04 - 16), 15, profile));
        TaskModel task2 = taskRepository.save(new TaskModel(9, "Spring rest api", " I need a simple spring boot rest api ", Hardness.EASY, Type.JAVA, LocalDate.ofEpochDay(2019 - 04 - 12), 5, profile2));
        TaskModel task3 = taskRepository.save(new TaskModel(10, "Logotype for online store", " I need a designed logotype for online store", Hardness.HARD, Type.DESIGN, LocalDate.ofEpochDay(2019 - 05 - 10), 20, profile3));
        TaskModel task4 = taskRepository.save(new TaskModel(11, "Html css site layout", " I need a simple htm css site layout for online store", Hardness.EASY, Type.SITE_LAYOUT, LocalDate.ofEpochDay(2019 - 04 - 20), 3, profile3));

        submittedRepository.save(new SubmittedModel((long) 12 ,profile3 , task));
        submittedRepository.save(new SubmittedModel((long) 13 ,profile2 , task3));

    }
}
