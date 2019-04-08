package com.finalproject.upwork.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.finalproject.upwork.models.DTO.UserLoginDTO;
import com.finalproject.upwork.models.DTO.UserProfileModelDTO;

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

import org.springframework.http.MediaType;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@EnableWebMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserControllerTest {

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

    @Test
    public void addUserLogin() throws Exception {

        UserLoginDTO userLoginDTO = new UserLoginDTO();

        userLoginDTO.setId(1);
        userLoginDTO.setNickname("Johny22");
        userLoginDTO.setPassword("qwerty12345");

        String json = objectMapper.writeValueAsString(userLoginDTO);

        RequestBuilder requestBuilder = post("/addUser")
                .contentType(APPLICATION_JSON_UTF8_VALUE)
                .content(json);

        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();

        assertEquals(mvcResult.getResponse().getContentAsString(), "Sign up successfully");
        assertEquals(mvcResult.getResponse().getStatus(), 201);

    }

    @Test
    public void addUserProfile() throws Exception {
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setId(1);
        userLoginDTO.setNickname("Johny22");
        userLoginDTO.setPassword("qwerty12345");

        UserProfileModelDTO userProfileModelDTO = new UserProfileModelDTO();
        userProfileModelDTO.setId(1);
        userProfileModelDTO.setName("John");
        userProfileModelDTO.setSurname("Conker");
        userProfileModelDTO.setEmail("john214@gmail.com");
        userProfileModelDTO.setSkill("java");
        userProfileModelDTO.setPortfolio("https://www.google.com");

        String json = objectMapper.writeValueAsString(userProfileModelDTO);

        RequestBuilder requestBuilder = post("/addUserDetails/{loginId}", userLoginDTO.getId())
                .contentType(APPLICATION_JSON_UTF8_VALUE)
                .content(json);

        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();

        assertEquals(mvcResult.getResponse().getContentAsString(), "Details added successfully");
        assertEquals(mvcResult.getResponse().getStatus(), 201);
    }


    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @WithMockUser("Johny22")
    @Test
    @Transactional
    public void getProfile() throws Exception {
        UserLoginModel userLogin = new UserLoginModel();
        userLogin.setNickname("Johny22");
        userLogin.setPassword("qwerty12345");

        UserProfileModel userProfileModel = new UserProfileModel();
        userProfileModel.setName("John");
        userProfileModel.setSurname("Conker");
        userProfileModel.setEmail("john214@gmail.com");
        userProfileModel.setSkill(Type.JAVA);
        userProfileModel.setPortfolio("https://www.google.com");
        userProfileModel.setUserId(userLogin);

        String json = objectMapper.writeValueAsString(userProfileModel);

        userProfileModel = userProfileRepository.save(userProfileModel);

        RequestBuilder requestBuilder = get("/getById/{profileId}", userProfileModel.getProfileId())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(json);

        mvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(userProfileModel.getName()))
                .andExpect(jsonPath("$.surname").value(userProfileModel.getSurname()))
                .andExpect(jsonPath("$.email").value(userProfileModel.getEmail()))
                .andExpect(jsonPath("$.skill").value(userProfileModel.getSkill().toString().toLowerCase()))
                .andExpect(jsonPath("$.portfolio").value(userProfileModel.getPortfolio()));
    }

    @Test
    @WithMockUser("Johny22")
    public void updateProfile() throws Exception {

        UserProfileModel userProfileModel = new UserProfileModel();
        userProfileModel.setName("Anna");
        userProfileModel.setSurname("Conker");
        userProfileModel.setEmail("anna214@gmail.com");
        userProfileModel.setSkill(Type.DESIGN);
        userProfileModel.setPortfolio("https://www.google2.com");

        userProfileModel = userProfileRepository.save(userProfileModel);

        UserProfileModelDTO userProfileModelDTO = new UserProfileModelDTO();
        userProfileModelDTO.setName("Maria");
        userProfileModelDTO.setSurname("Conker");
        userProfileModelDTO.setEmail("maria214@gmail.com");
        userProfileModelDTO.setSkill("design");
        userProfileModelDTO.setPortfolio("https://www.google2.com");

        String json = objectMapper.writeValueAsString(userProfileModelDTO);

        RequestBuilder requestBuilder = put("/updateProfile/{profileId}", userProfileModel.getProfileId())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(json);

        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();

        assertEquals(mvcResult.getResponse().getStatus(), 201);

        assertEquals(mvcResult.getResponse().getContentAsString(), "Updated successfully");


    }

    @Test
    @WithMockUser("Johny22")
    @Transactional
    public void deleteUser() throws Exception {

        UserLoginModel userLogin = new UserLoginModel();

        UserProfileModel userProfileModel = new UserProfileModel();


        userLogin.setNickname("Jack33");
        userLogin.setPassword("qwerty12345");


        userLogin = userLoginRepository.save(userLogin);


        userProfileModel.setName("Jack");
        userProfileModel.setSurname("Conker");
        userProfileModel.setEmail("jack214@gmail.com");
        userProfileModel.setSkill(Type.PHOTO_RETOUCH);
        userProfileModel.setPortfolio("https://www.google.3com");


        userProfileModel = userProfileRepository.save(userProfileModel);


        RequestBuilder requestBuilder = delete("/deleteUser/" + userLogin.getLoginId() + "/" + userProfileModel.getProfileId())
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

        MvcResult mvcResult = mvc.perform(requestBuilder).andReturn();

        assertEquals(mvcResult.getResponse().getStatus(), 200);
        assertEquals(mvcResult.getResponse().getContentAsString(), "User deleted successfully");


    }


}