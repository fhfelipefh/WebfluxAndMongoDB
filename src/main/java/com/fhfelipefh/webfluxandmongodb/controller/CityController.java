package com.fhfelipefh.webfluxandmongodb.controller;

import com.fhfelipefh.webfluxandmongodb.document.City;
import com.fhfelipefh.webfluxandmongodb.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Controller
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/cities")
    public Flux<City> getCities() {
        return cityService.findAll();
    }

    @GetMapping("/cities/{name}")
    public Mono<City> getCityByName(@PathVariable String name) {
        return cityService.findByName(name);
    }

    @PostMapping("/cities")
    public Mono<City> saveCity(@RequestBody City city) {
        return cityService.save(city);
    }

    @PutMapping("/cities/{name}")
    public Mono<City> updateCity(@PathVariable String name, @RequestBody City city) {
        return cityService.update(city);
    }

    @DeleteMapping("/cities/{name}")
    public Mono<Void> deleteCity(@PathVariable String name) {
        return cityService.deleteByName(name);
    }

}
