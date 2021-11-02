package com.graduation.project.controller;

import com.graduation.project.repository.DishRepository;
import com.graduation.project.repository.UserRepository;
import com.graduation.project.repository.VoitRepository;
import com.graduation.project.service.VoitService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest
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
    VoitRepository voitRepository;

    @MockBean
    VoitService voitService;

    @MockBean
    RestaurantController restaurantController;

    @MockBean
    AnyAccessController anyAccessController;

    @Autowired
    private MockMvc mockMvc;


    private final String URL = "/api/admin/restaurants";

    @Test
    void getAllRest() throws Exception {
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
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }



    @Test
    void getRestById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URL + "/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

}