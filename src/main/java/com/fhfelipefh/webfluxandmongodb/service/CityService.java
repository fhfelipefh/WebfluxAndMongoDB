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
        return cityRepository.findByName(name);
    }

    public Mono<City> update(City city) {
        return cityRepository.delete(city).then(cityRepository.save(city));
    }

    public Mono<Void> deleteByName(String name) {
        return cityRepository.deleteByName(name);
    }

    public Mono<City> save(City city) {
        return cityRepository.save(city);
    }

}
