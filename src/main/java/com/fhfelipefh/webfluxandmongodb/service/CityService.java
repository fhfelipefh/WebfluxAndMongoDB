package com.fhfelipefh.webfluxandmongodb.service;

import com.fhfelipefh.webfluxandmongodb.document.City;
import com.fhfelipefh.webfluxandmongodb.respository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    public Flux<City> findAll() {
        return cityRepository.findAll().switchIfEmpty(Flux.empty());
    }

    public Mono<City> findByName(String name) {
        return cityRepository.findByName(name).switchIfEmpty(Mono.empty());
    }

    public Mono<City> update(City city) {
        return validateCityToSave(city)
                .flatMap(c -> cityRepository.findByName(c.getName())
                .flatMap(foundCity -> {
                    foundCity.setName(c.getName());
                    foundCity.setCountry(c.getCountry());
                    foundCity.setState(c.getState());
                    return cityRepository.save(foundCity);
                })
        ).switchIfEmpty(Mono.empty());
    }

    public Mono<Void> deleteByName(String name) {
        return cityRepository.findByName(name)
                .flatMap(c -> cityRepository.deleteByName(c.getName()))
                .switchIfEmpty(Mono.empty());
    }

    public Mono<City> save(City city) {
        return validateCityToSave(city)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("City is invalid")))
                .flatMap(c -> cityRepository.save(c));
    }

    private Mono<City> validateCityToSave(City city) {
        return Mono.just(city)
                .filter(c -> c.getName() != null && c.getState() != null && c.getCountry() != null)
                .switchIfEmpty(Mono.empty());
    }

}
