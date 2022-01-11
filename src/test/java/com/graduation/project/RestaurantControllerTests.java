package com.graduation.project;

import com.graduation.project.model.Restaurant;
import com.graduation.project.repository.RestaurantRepository;
import com.graduation.project.util.JsonUtil;
import org.junit.jupiter.api.Assertions;
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
@WithUserDetails("admin")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class RestaurantControllerTests {
	private final String URLAdmin = "/api/admin/restaurants";
	private final String URLUser = "/api/user/restaurants";

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Test
	void getAllRest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(URLUser))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	void getRestById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(URLUser + "/1"))
				.andExpect(status().isOk())
				.andDo(MockMvcResultHandlers.print());
	}

	@Test
	void addRest() throws Exception {
		Restaurant restaurant = new Restaurant("newRestaurant");
		String restMapper = JsonUtil.writeValue(restaurant);
		mockMvc.perform(MockMvcRequestBuilders.post(URLAdmin)
						.contentType(MediaType.APPLICATION_JSON)
						.content(restMapper))
				.andExpect(status().isCreated());

		Restaurant restaurant1 = restaurantRepository.findByName(restaurant.getName());
		Assertions.assertEquals(restaurant1, restaurant);
	}

	@Test
	void deleteRest() throws Exception {
		Restaurant restaurant = new Restaurant("newRestaurant");
		restaurantRepository.save(restaurant);

		Restaurant restaurant1 = restaurantRepository.findByName(restaurant.getName());
		int id = restaurant1.getRestId();

		mockMvc.perform(MockMvcRequestBuilders.delete(URLAdmin + "/" + id))
				.andExpect(status().isOk());

		restaurant1 = restaurantRepository.getById(restaurant1.getRestId());
		Assertions.assertNull(restaurant1);
	}

	@Test
	void patchRestById() throws Exception {
		int id = 1;
		Restaurant restaurant = restaurantRepository.getById(id);
		restaurant.setName("newRestaurant");
		String restMapper = JsonUtil.writeValue(restaurant);

		mockMvc.perform(MockMvcRequestBuilders.patch(URLAdmin + "/" + id)
						.contentType(MediaType.APPLICATION_JSON)
						.content(restMapper))
				.andExpect(status().isOk());

		Restaurant restaurant1 = restaurantRepository.findByName("newRestaurant");
		Assertions.assertEquals(restaurant1, restaurant);
	}
}
