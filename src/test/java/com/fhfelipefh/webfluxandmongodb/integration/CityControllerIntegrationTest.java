package com.fhfelipefh.webfluxandmongodb.integration;

import com.fhfelipefh.webfluxandmongodb.document.City;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureDataMongo
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CityControllerIntegrationTest {

    private final City city = buildCity("São Paulo", "SP", "Brasil");
    private final String expectedCity = "{\"id\":\"\",\"name\":\"São Paulo\",\"state\":\"SP\",\"country\":\"Brasil\"}";

    @Autowired
    private TestRestTemplate restTemplate = new TestRestTemplate();

    @BeforeAll
    public static void setUp() {
        CityControllerIntegrationTest.class.getClassLoader().setDefaultAssertionStatus(true);
    }

    @Test
    @DisplayName("Should save city")
    @Order(1)
    @Tags({@Tag("ALL")})
    public void saveCity() {
        String response = restTemplate.postForObject("/cities", city, String.class);
        response = response.replaceAll("\"id\":\"[^\"]+\"", "\"id\":\"\"");
        assertEquals(expectedCity, response);
    }

    @Test
    @DisplayName("Should get city")
    @Order(2)
    @Tags({@Tag("ALL")})
    public void getCity() {
        String response = restTemplate.getForObject("/cities/São Paulo", String.class);
        response = response.replaceAll("\"id\":\"[^\"]+\"", "\"id\":\"\"");
        assertEquals(expectedCity, response);
    }

    @Test
    @DisplayName("Should update city")
    @Order(3)
    @Tags({@Tag("ALL")})
    public void updateCity() {
        String expected = "{\"id\":\"\",\"name\":\"São Paulo\",\"state\":\"RS\",\"country\":\"Brasil\"}";
        City updatedCity = buildCity("São Paulo", "RS", "Brasil");
        restTemplate.put("/cities", updatedCity);
        String response = restTemplate.getForObject("/cities/São Paulo", String.class);
        response = response.replaceAll("\"id\":\"[^\"]+\"", "\"id\":\"\"");
        assertEquals(expected, response);
    }

    @Test
    @DisplayName("Should delete city")
    @Order(4)
    @Tags({@Tag("ALL")})
    public void deleteCity() {
        restTemplate.delete("/cities/São Paulo");
        restTemplate.getForObject("/cities/São Paulo", String.class);
    }

    private City buildCity(String name, String state, String country) {
        City city = new City();
        city.setName(name);
        city.setState(state);
        city.setCountry(country);
        return city;
    }


}
