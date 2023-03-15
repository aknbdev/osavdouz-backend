package dev.aknb.osavdouz.controllers;

import dev.aknb.osavdouz.constants.ApiConstants;
import dev.aknb.osavdouz.dto.CityDto;
import dev.aknb.osavdouz.entities.address.City;
import dev.aknb.osavdouz.service.CityService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(ApiConstants.API_CiTY)
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public ResponseEntity<List<City>> getCityAll() {
        log.info("REST request to getAll city");
        List<City> cityList = cityService.getCityAll();
        return ResponseEntity.ok(cityList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<City> getCity(@PathVariable Long id) {
        log.info("Rest request to get city by id");
        City city = cityService.getCityById(id);
        return ResponseEntity.ok(city);
    }

    @PostMapping
    public ResponseEntity<CityDto> createCity(@Valid @RequestBody CityDto cityDto) {
        log.info("REST request to city");
        return ResponseEntity.ok(cityService.createCity(cityDto));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CityDto> updateCity(@PathVariable Long id, @Valid @RequestBody CityDto cityDto) {
        log.info("REST request to update city");
        return ResponseEntity.ok(cityService.updateCity(id, cityDto));
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> deleteCity(@PathVariable Long id) {
        log.info("delete city by id");
        cityService.deleteCity(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

}
