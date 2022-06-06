package com.fhfelipefh.webfluxandmongodb.service;

import com.fhfelipefh.webfluxandmongodb.document.City;
import com.fhfelipefh.webfluxandmongodb.respository.CityRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CityServiceTest {

    @InjectMocks
    private CityService cityService;

    @Mock
    private CityRepository cityRepository;

    private City sp = City.builder()
            .name("São Paulo")
            .country("Brazil")
            .state("São Paulo")
            .build();

    @Test
    void shouldFindAll() {
        when(cityRepository.findAll()).thenReturn(Flux.just(new City()));
        StepVerifier.create(cityService.findAll())
                .expectNextCount(1)
                .verifyComplete();
        Mockito.verify(cityRepository).findAll();
    }

    @Test
    void shouldFindByName() {
        when(cityRepository.findFirstByName("São Paulo")).thenReturn(Mono.just(sp));
        StepVerifier.create(cityService.findByName("São Paulo"))
                .expectNext(sp)
                .verifyComplete();
        Mockito.verify(cityRepository).findFirstByName("São Paulo");
    }

    @Test
    void shouldFindCityByCountry() {
        when(cityRepository.findFirstCityByCountry("Brazil")).thenReturn(Flux.just(sp));
        StepVerifier.create(cityService.findCityByCountry("Brazil"))
                .expectNext(sp)
                .verifyComplete();
        Mockito.verify(cityRepository).findFirstCityByCountry("Brazil");
    }

    @Test
    void shouldSave() {
        when(cityRepository.save(sp)).thenReturn(Mono.just(sp));
        StepVerifier.create(cityService.save(sp))
                .expectNext(sp)
                .verifyComplete();
        Mockito.verify(cityRepository).save(sp);
    }

    @Test
    void shouldNotSaveWhenCityIsEmpty() {
        City city = new City();
        StepVerifier.create(cityService.save(city))
                .expectError(IllegalArgumentException.class)
                .verify();
        Mockito.verify(cityRepository, Mockito.never()).save(city);
    }

    @Test
    void shouldUpdate() {
        when(cityRepository.findFirstByName("São Paulo")).thenReturn(Mono.just(sp));
        when(cityRepository.save(sp)).thenReturn(Mono.just(sp));
        StepVerifier.create(cityService.update(sp))
                .expectNext(sp)
                .verifyComplete();
        Mockito.verify(cityRepository).save(sp);
        Mockito.verify(cityRepository).findFirstByName("São Paulo");
    }

    @Test
    void shouldNotUpdateWhenCityIsEmpty() {
        City city = new City();
        StepVerifier.create(cityService.update(city))
                .expectComplete();
        Mockito.verify(cityRepository, Mockito.never()).save(city);
        Mockito.verify(cityRepository, Mockito.never()).findFirstByName("São Paulo");
    }

    @Test
    void shouldDelete() {
        when(cityRepository.findFirstByName("São Paulo")).thenReturn(Mono.just(sp));
        when(cityRepository.deleteAllByName("São Paulo")).thenReturn(Mono.empty());
        StepVerifier.create(cityService.deleteByName("São Paulo"))
                .verifyComplete();
        Mockito.verify(cityRepository).deleteAllByName("São Paulo");
        Mockito.verify(cityRepository).findFirstByName("São Paulo");
    }

}