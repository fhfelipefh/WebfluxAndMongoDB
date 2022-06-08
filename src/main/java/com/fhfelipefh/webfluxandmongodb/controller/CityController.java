package com.fhfelipefh.webfluxandmongodb.controller;

import com.fhfelipefh.webfluxandmongodb.document.City;
import com.fhfelipefh.webfluxandmongodb.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping("/cities")
    public ResponseEntity<Flux<City>> getCities() {
        return ResponseEntity.ok(cityService.findAll());
    }

    @GetMapping("/cities/{name}")
    public ResponseEntity<Mono<City>> getCityByName(@PathVariable String name) {
        return new ResponseEntity<>(cityService.findByName(name), HttpStatus.OK);
    }

    @GetMapping("/cities/country/{country}")
    public ResponseEntity<Flux<City>> getCityByCountry(@PathVariable String country) {
        return new ResponseEntity<>(cityService.findCityByCountry(country), HttpStatus.OK);
    }

    @PostMapping("/cities")
    public ResponseEntity<Mono<City>> saveCity(@RequestBody City city) {
        return new ResponseEntity<>(cityService.save(city), HttpStatus.CREATED);
    }

    @PutMapping("/cities")
    public ResponseEntity<Mono<City>> updateCity(@RequestBody City city) {
        return new ResponseEntity<>(cityService.update(city), HttpStatus.OK);
    }

    @DeleteMapping("/cities/{name}")
    public ResponseEntity<Mono<Void>> deleteCity(@PathVariable String name) {
        return new ResponseEntity<>(cityService.deleteByName(name), HttpStatus.NO_CONTENT);
    }

}
