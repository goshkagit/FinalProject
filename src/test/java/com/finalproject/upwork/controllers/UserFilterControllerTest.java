package com.finalproject.upwork.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalproject.upwork.models.UserLoginModel;
import com.finalproject.upwork.models.UserProfileModel;
import com.finalproject.upwork.models.enums.Type;
import com.finalproject.upwork.repositories.UserLoginRepository;
import com.finalproject.upwork.repositories.UserProfileRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@EnableWebMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserFilterControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserLoginRepository userLoginRepository;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithMockUser("Johny22")
    @Transactional
    public void allSkill() throws Exception {


        UserLoginModel userLogin = new UserLoginModel();
        userLogin.setNickname("Johny222");
        userLogin.setPassword("qwerty12345");

        UserProfileModel userProfileModel = new UserProfileModel();
        userProfileModel.setName("Jack");
        userProfileModel.setSurname("Conker");
        userProfileModel.setEmail("jack214@gmail.com");
        userProfileModel.setSkill(Type.JAVA);
        userProfileModel.setPortfolio("https://www.google4.com");
        userProfileModel.setUserId(userLogin);

        UserLoginModel userLogin2 = new UserLoginModel();
        userLogin.setNickname("Anthony22");
        userLogin.setPassword("qwerty12345");

        UserProfileModel userProfileModel2 = new UserProfileModel();
        userProfileModel.setName("Anthony");
        userProfileModel.setSurname("Conker");
        userProfileModel.setEmail("anthony214@gmail.com");
        userProfileModel.setSkill(Type.PYTHON);
        userProfileModel.setPortfolio("https://www.google5.com");
        userProfileModel.setUserId(userLogin2);


        userProfileModel = userProfileRepository.save(userProfileModel);

        String json = objectMapper.writeValueAsString(userProfileModel);

        userProfileModel2 = userProfileRepository.save(userProfileModel2);


        RequestBuilder requestBuilder = get("/skillIs/{skill}", "java")
                .contentType(APPLICATION_JSON_UTF8_VALUE);


        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();

        assertEquals(mvcResult.getResponse().getContentAsString(), "[" + json + "]");

        assertEquals(mvcResult.getResponse().getStatus(), 200);


    }
}