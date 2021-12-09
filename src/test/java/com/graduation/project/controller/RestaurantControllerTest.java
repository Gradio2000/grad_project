package com.graduation.project.controller;

import com.graduation.project.ProjectApplication;
import com.graduation.project.model.Restaurant;
import com.graduation.project.model.User;
import com.graduation.project.repository.DishRepository;
import com.graduation.project.repository.RestaurantRepository;
import com.graduation.project.repository.UserRepository;
import com.graduation.project.repository.VoteRepository;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectApplication.class)
@AutoConfigureMockMvc
@WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
class RestaurantControllerTest {

    @MockBean
    UserRepository userRepository;

    @MockBean
    DishRepository dishRepository;

    @MockBean
    UserAccessController userController;

    @MockBean
    VoteRepository voteRepository;

    @MockBean
    RestaurantRepository repository;

    @MockBean
    AnyAccessController anyAccessController;

    @Autowired
    private MockMvc mockMvc;


    private final String URL = "/api/admin/restaurants";

    @BeforeClass
    void add(){
        userRepository.save(new User(100, "aaawww", "asasa@qq.qq", "asasa"));
    }

    @Test
//    @WithUserDetails(value = "aaawww")
//    @WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetail")
    void getAllRest() throws Exception {

        userRepository.save(new User(100, "aaawww", "asasa@qq.qq", "asasa"));
        repository.save(new Restaurant(100, "aaa"));


        mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void addRest() throws Exception {
        String restaurant = "{\"name\": \"string\"}";
        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                        .content(restaurant)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }



    @Test
    void getRestById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}