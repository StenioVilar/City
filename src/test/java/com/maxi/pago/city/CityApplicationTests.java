package com.maxi.pago.city;

import com.maxi.pago.city.dao.CityDAO;
import com.maxi.pago.city.model.City;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class CityApplicationTests {

    @Test
    public void contextLoads() {
    }

    /*@Autowired
    private MockMvc mockMvc;

    @MockBean
    private CityDAO cityDAO;

    @Test
    public void find_allcitiesNotFound_404() throws Exception {
        mockMvc.perform(get("/cities")).andExpect(status().isNotFound());
    }

    @Test
    public void find_allcities_OK() throws Exception {

        List<City> cities = Arrays.asList(
                new City(1, "Brasilia", 1,1,1));

        when(cityDAO.findAll()).thenReturn(cities);

        mockMvc.perform(get("/cities"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Brasilia")))
                .andExpect(jsonPath("$[0].latitude", is(1)))
                .andExpect(jsonPath("$[0].longitude", is(1)))
                .andExpect(jsonPath("$[0].timezone", is(1)));

        verify(cityDAO, times(1)).findAll();
    }
    */

}
