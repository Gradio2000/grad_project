package com.graduation.project;

import com.graduation.project.model.to.UserTO;
import com.graduation.project.util.JsonUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithUserDetails("admin")
    void getAllUsers() throws Exception {
        String URL = "/api/admin/users";
        mockMvc.perform(MockMvcRequestBuilders.get(URL))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @WithUserDetails("admin")
    void getUserById() throws Exception {
        String URLWithID = "/api/admin/users/1";
        mockMvc.perform(MockMvcRequestBuilders.get(URLWithID))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void registerUser() throws Exception {
        String URL = "/api/users/add";
        UserTO userTO = new UserTO("newName", "newEmail@ss.ss", "pass");
        String userMapper = JsonUtil.writeValue(userTO);

        mockMvc.perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userMapper))
                    .andExpect(status().isCreated());
    }

    @Test
    @WithUserDetails("admin")
    void getAllVoits() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/voits"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());

        System.out.println("попытка 2");

        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/voits"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
