package dev.aknb.osavdouz.service;

import dev.aknb.osavdouz.dto.CountryDto;
import dev.aknb.osavdouz.entities.address.Country;
import dev.aknb.osavdouz.repositories.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> getCountryAll() {
        return countryRepository.findAll();
    }

    public Country getCountryById(Long id) {
        Optional<Country> optionalCountry = countryRepository.findById(id);
        return optionalCountry.orElse(null);
    }

    public CountryDto createCountry(CountryDto countryDto) {

        boolean existsCountryByName = countryRepository.existsCountryByName(countryDto.getName());
        if (existsCountryByName) {
            return null;
        }
        Country country = new Country();
        updateDtoToCountry(countryDto, country);
        country = countryRepository.save(country);
        updateCountryToDto(country, countryDto);
        return countryDto;
    }

    public CountryDto updateCountry(Long id, CountryDto countryDto) {
        boolean existsByNameAndIdNot = countryRepository.existsByNameAndIdNot(countryDto.getName(), id);
        if (existsByNameAndIdNot) {
            return null;
        }
        Optional<Country> optionalCountry = countryRepository.findById(id);
        if (optionalCountry.isEmpty()) {
            return null;
        }
        Country country = optionalCountry.get();
        updateDtoToCountry(countryDto, country);
        country = countryRepository.save(country);
        updateCountryToDto(country, countryDto);
        return countryDto;

    }

    public void deleteCountry(Long id) {
        if (!countryRepository.existsById(id)) {
//            throw new null;
        }
        countryRepository.deleteById(id);

    }

    public void updateDtoToCountry(CountryDto countryDto, Country country) {

        country.setName(countryDto.getName());
    }

    public void updateCountryToDto(Country country, CountryDto countryDto) {
        countryDto.setName(country.getName());
        countryDto.setRegions(country.getRegions());
    }


    public Boolean existsById(Long id) {
        boolean existsById = countryRepository.existsById(id);
        return existsById;
    }
}