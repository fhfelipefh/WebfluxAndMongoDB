package com.fhfelipefh.webfluxandmongodb.controller;

import com.fhfelipefh.webfluxandmongodb.config.Generated;
import com.fhfelipefh.webfluxandmongodb.document.City;
import com.fhfelipefh.webfluxandmongodb.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Controller
@Generated
public class CityController {

    @Autowired
    private CityService cityService;

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

    @PutMapping("/cities/{name}")
    public ResponseEntity<Mono<City>> updateCity(@PathVariable String name, @RequestBody City city) {
        return new ResponseEntity<>(cityService.update(city), HttpStatus.OK);
    }

    @DeleteMapping("/cities/{name}")
    public ResponseEntity<Mono<Void>> deleteCity(@PathVariable String name) {
        return new ResponseEntity<>(cityService.deleteByName(name), HttpStatus.NO_CONTENT);
    }

}
