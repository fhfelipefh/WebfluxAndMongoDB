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

    private City sp = City.builder().name("São Paulo").build();

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
        when(cityRepository.findByName("São Paulo")).thenReturn(Mono.just(sp));
        StepVerifier.create(cityService.findByName("São Paulo"))
                .expectNext(sp)
                .verifyComplete();
        Mockito.verify(cityRepository).findByName("São Paulo");
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
    void shouldUpdate() {
        when(cityRepository.delete(sp)).thenReturn(Mono.empty());
        when(cityRepository.save(sp)).thenReturn(Mono.just(sp));
        StepVerifier.create(cityService.update(sp))
                .expectNext(sp)
                .verifyComplete();
        Mockito.verify(cityRepository).delete(sp);
        Mockito.verify(cityRepository).save(sp);
    }

    @Test
    void shouldDelete() {
        when(cityRepository.deleteByName("São Paulo")).thenReturn(Mono.empty());
        StepVerifier.create(cityService.deleteByName("São Paulo"))
                .verifyComplete();
    }

}