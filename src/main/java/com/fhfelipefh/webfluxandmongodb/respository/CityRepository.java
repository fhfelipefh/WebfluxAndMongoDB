package com.fhfelipefh.webfluxandmongodb.respository;

import com.fhfelipefh.webfluxandmongodb.config.Generated;
import com.fhfelipefh.webfluxandmongodb.document.City;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Generated
public interface CityRepository extends ReactiveMongoRepository<City, String> {

    Mono<City> findById(String id);
    Mono<City> findFirstByName(String name);
    Mono<City> save(City city);
    Mono<Void> deleteAllByName(String name);
    Flux<City> findFirstCityByCountry(String country);
}
