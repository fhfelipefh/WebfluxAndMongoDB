package com.fhfelipefh.webfluxandmongodb.service;

import com.fhfelipefh.webfluxandmongodb.document.City;
import com.fhfelipefh.webfluxandmongodb.respository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;

    public Flux<City> findAll() {
        return cityRepository.findAll();
    }

    public Mono<City> findByName(String name) {
        return cityRepository.findFirstByName(name);
    }

    public Mono<City> update(City city) {
        return validateCityToSave(city).flatMap(c -> cityRepository
                .findFirstByName(c.getName())
                .flatMap(foundCity -> {
                    foundCity.setName(c.getName());
                    foundCity.setCountry(c.getCountry());
                    foundCity.setState(c.getState());
                    return cityRepository.save(foundCity);
                })).switchIfEmpty(Mono.error(new Exception("Update failed")));
    }

    public Mono<Void> deleteByName(String name) {
        return cityRepository.findFirstByName(name)
                .flatMap(c -> cityRepository.deleteAllByName(c.getName()))
                .then();
    }

    public Mono<City> save(City city) {
        return validateCityToSave(city)
                .switchIfEmpty(Mono.error(new IllegalArgumentException("City is invalid")))
                .flatMap(cityRepository::save);
    }

    public Flux<City> findCityByCountry(String country) {
        return cityRepository.findFirstCityByCountry(country);
    }

    private Mono<City> validateCityToSave(City city) {
        return Mono.just(city)
                .filter(c -> c.getName() != null && c.getState() != null && c.getCountry() != null)
                .switchIfEmpty(Mono.empty());
    }
}
