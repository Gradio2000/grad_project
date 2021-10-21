package com.graduation.project.controller;

import com.graduation.project.repository.DishRepository;
import com.graduation.project.repository.UserRepository;
import com.graduation.project.repository.VoitRepository;
import com.graduation.project.service.UserService;
import com.graduation.project.service.VoitService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
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
    UserController userController;

    @MockBean
    DishController dishController;

    @MockBean
    UserService userService;

    @MockBean
    PasswordEncoder passwordEncoder;

    @MockBean
    VoitRepository voitRepository;

    @MockBean
    VoitService voitService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user", password = "user")
    public void testRegisterValidUser() throws Exception {
        String user = "{\n" +
                "\"name\": \"aaa\",\n" +
                "\"email\": \"as@as.as\",\n" +
                "\"password\": \"q\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                        .content(user)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                        .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }

    @Test
    @WithMockUser(username = "user", password = "user")
    public void testRegisterInvalidUser() throws Exception {
        String user = "{\n" +
                "\"name\": \"aaa\",\n" +
                "\"email\": \"asas.as\",\n" +
                "\"password\": \"q\"\n" +
                "}";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                        .content(user)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(MockMvcResultHandlers.print())
                .andReturn().getResponse().getContentAsString();
    }

}