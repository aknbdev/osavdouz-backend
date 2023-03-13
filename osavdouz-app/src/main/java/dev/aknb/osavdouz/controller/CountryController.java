package dev.aknb.osavdouz.controller;


import dev.aknb.osavdouz.constants.ApiConstants;
import dev.aknb.osavdouz.dto.CountryDto;
import dev.aknb.osavdouz.entities.address.Country;
import dev.aknb.osavdouz.service.CountryService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(ApiConstants.API_COUNTRY)
public class CountryController {
    private final CountryService countryService;
    private final Logger log = LoggerFactory.getLogger(CountryController.class);

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public ResponseEntity<List<Country>> getCountryAll() {
        log.info("REST request to getAll countries");
        List<Country> countryList = countryService.getCountryAll();
        return ResponseEntity.ok(countryList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Country> getCountry(@PathVariable Long id) {
        log.info("REST request to get country by id");
        Country country = countryService.getCountryById(id);
        return ResponseEntity.ok(country);
    }

    @PostMapping
    public ResponseEntity<CountryDto> createCountry(@Valid @RequestBody CountryDto countryDto) {
        log.info("REST request to country");
        return ResponseEntity.ok(countryService.createCountry(countryDto));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CountryDto> updateCountry(@PathVariable Long id, @Valid @RequestBody CountryDto countryDto) {
        log.info("REST request to update country");
        return ResponseEntity.ok(countryService.updateCountry(id, countryDto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> deleteCountry(@PathVariable Long id) {
        log.info("delete country by id");
        countryService.deleteCountry(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }
}
