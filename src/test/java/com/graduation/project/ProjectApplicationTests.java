package com.graduation.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ContextConfiguration
@AutoConfigureMockMvc
@WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
class ProjectApplicationTests {
	private final String URL =  "/api/admin/restaurants";
	private final String URLWithID =  "/api/admin/restaurants";

	@Autowired
	private MockMvc mockMvc;

	@Test
	void getAllRest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(URL))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	void getRestById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(URLWithID))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}
}
