package com.graduation.project.controller;

import com.graduation.project.repository.DishRepository;
import com.graduation.project.repository.RestaurantRepository;
import com.graduation.project.repository.UserRepository;
import com.graduation.project.repository.VoitRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @MockBean
    UserRepository userRepository;

    @MockBean
    DishRepository dishRepository;

    @MockBean
    UserAccessController userController;

    @MockBean
    VoitRepository voitRepository;

    @MockBean
    AnyAccessController anyAccessController;

    @MockBean
    RestaurantRepository repository;

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void testRegisterValidUser() throws Exception    {
        String user = "{\n" +
                "\"name\": \"aaa\",\n" +
                "\"email\": \"as@as.as\",\n" +
                "\"password\": \"q\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/add")
                        .content(user)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void testRegisterInvalidUser() throws Exception {
        String user = "{\n" +
                "\"name\": \"aaa\",\n" +
                "\"email\": \"asas.as\",\n" +
                "\"password\": \"q\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users/add")
                        .content(user)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();
    }

}