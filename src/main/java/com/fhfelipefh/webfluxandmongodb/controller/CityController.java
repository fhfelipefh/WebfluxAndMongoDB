package com.fhfelipefh.webfluxandmongodb.controller;

import com.fhfelipefh.webfluxandmongodb.config.Generated;
import com.fhfelipefh.webfluxandmongodb.document.City;
import com.fhfelipefh.webfluxandmongodb.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public Flux<City> getCities() {
        return cityService.findAll()
                .switchIfEmpty(Mono.error(new RuntimeException("Nenhuma cidade encontrada")));
    }

    @GetMapping("/cities/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<City> getCityByName(@PathVariable String name) {
        return cityService.findByName(name)
                .switchIfEmpty(Mono.error(new RuntimeException("Cidade não encontrada")));
    }

    @PostMapping("/cities")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<City> saveCity(@RequestBody City city) {
        return cityService.save(city).switchIfEmpty(Mono.error(new RuntimeException("Cidade não pode ser salva")));
    }

    @PutMapping("/cities/{name}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<City> updateCity(@PathVariable String name, @RequestBody City city) {
        return cityService.update(city).switchIfEmpty(Mono.error(new RuntimeException("Cidade não pode ser atualizada")));
    }

    @DeleteMapping("/cities/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteCity(@PathVariable String name) {
        return cityService.deleteByName(name);
    }

}
