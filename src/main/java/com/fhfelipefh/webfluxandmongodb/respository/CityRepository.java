package com.fhfelipefh.webfluxandmongodb.respository;

import com.fhfelipefh.webfluxandmongodb.document.City;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CityRepository extends ReactiveMongoRepository<City, String> {

    Mono<City> findById(String id);
    Mono<City> findByName(String name);
    Mono<City> save(City city);
    Mono<Void> deleteByName(String name);

}
