package com.fhfelipefh.webfluxandmongodb.controller;

import com.fhfelipefh.webfluxandmongodb.document.City;
import com.fhfelipefh.webfluxandmongodb.service.CityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Objects;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class CityControllerTest {

    @InjectMocks
    private CityController cityController;

    @Mock
    private CityService cityService;

    private City city = buildCity("São Paulo", "SP", "Brasil");

    @BeforeEach
    void setUp() {
        when(cityService.findAll()).thenReturn(Flux.just(city));
        when(cityService.findByName("São Paulo")).thenReturn(Mono.just(city));
        when(cityService.findCityByCountry("Brasil")).thenReturn(Flux.just(city));
        when(cityService.save(city)).thenReturn(Mono.just(city));
        when(cityService.update(city)).thenReturn(Mono.just(city));
        when(cityService.deleteByName("São Paulo")).thenReturn(Mono.empty());
    }

    @Test
    @DisplayName("Should return all cities")
    void getCities() {
        ResponseEntity<Flux<City>> response = cityController.getCities();
        StepVerifier.create(Objects.requireNonNull(response.getBody()))
                .expectNext(city)
                .verifyComplete();
        verify(cityService).findAll();
    }

    @Test
    @DisplayName("Should return city by name")
    void getCityByName() {
        ResponseEntity<Mono<City>> response = cityController.getCityByName("São Paulo");
        StepVerifier.create(Objects.requireNonNull(response.getBody()))
                .expectNext(city)
                .verifyComplete();
        verify(cityService).findByName("São Paulo");
    }

    @Test
    @DisplayName("Should return city by country")
    void getCityByCountry() {
        ResponseEntity<Flux<City>> response = cityController.getCityByCountry("Brasil");
        StepVerifier.create(Objects.requireNonNull(response.getBody()))
                .expectNext(city)
                .verifyComplete();
        verify(cityService).findCityByCountry("Brasil");
    }

    @Test
    @DisplayName("Should save city")
    void saveCity() {
        ResponseEntity<Mono<City>> response = cityController.saveCity(city);
        StepVerifier.create(Objects.requireNonNull(response.getBody()))
                .expectNext(city)
                .verifyComplete();
        verify(cityService).save(city);
    }

    @Test
    @DisplayName("Should update city")
    void updateCity() {
        ResponseEntity<Mono<City>> response = cityController.updateCity("São Paulo", city);
        StepVerifier.create(Objects.requireNonNull(response.getBody()))
                .expectNext(city)
                .verifyComplete();
        verify(cityService).update(city);
    }

    @Test
    @DisplayName("Should delete city by name")
    void deleteCityByName() {
        ResponseEntity<Mono<Void>> response = cityController.deleteCity("São Paulo");
        StepVerifier.create(Objects.requireNonNull(response.getBody()))
                .verifyComplete();
        verify(cityService).deleteByName("São Paulo");
    }


    private City buildCity(String name, String state, String country) {
        City city = new City();
        city.setName(name);
        city.setState(state);
        city.setCountry(country);
        return city;
    }

}