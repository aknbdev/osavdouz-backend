package dev.aknb.osavdouz.service;

import dev.aknb.osavdouz.dto.CityDto;
import dev.aknb.osavdouz.entities.address.City;
import dev.aknb.osavdouz.repositories.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CityService {

    private final CityRepository cityRepository;

    private final RegionService regionService;

    public CityService(CityRepository cityRepository, RegionService regionService) {
        this.cityRepository = cityRepository;
        this.regionService = regionService;
    }

    public List<City> getCityAll() {
        return cityRepository.findAll();
    }

    public City getCityById(Long id) {
        Optional<City> optionalCity = cityRepository.findById(id);
        return optionalCity.orElse(null);
    }

    public CityDto createCity(CityDto cityDto) {
        Boolean existsById = regionService.existsById(cityDto.getRegionId());
        if (!existsById) {
            return null;
        }
        boolean existsCityByName = cityRepository.existsCityByName(cityDto.getName());
        if (existsCityByName) {
            return null;
        }
        City city = new City();
        updateDtoToCity(cityDto, city);
        city = cityRepository.save(city);
        updateCityToDto(city, cityDto);
        return cityDto;
    }


    public CityDto updateCity(Long id, CityDto cityDto) {
        boolean existsByNameAndIdNot = cityRepository.existsByNameAndIdNot(cityDto.getName(), id);
        if (existsByNameAndIdNot) {
            return null;
        }
        Optional<City> optionalCity = cityRepository.findById(id);
        if (optionalCity.isEmpty()) {
            return null;
        }
        City city = optionalCity.get();
        updateDtoToCity(cityDto, city);
        city = cityRepository.save(city);
        updateCityToDto(city, cityDto);
        return cityDto;

    }

    public void deleteCity(Long id) {
        if (!cityRepository.existsById(id)) {
//            throw new ;
        }
        cityRepository.deleteById(id);
    }

    public void updateDtoToCity(CityDto cityDto, City city) {
        city.setName(cityDto.getName());
    }

    public void updateCityToDto(City city, CityDto cityDto) {
        cityDto.setName(city.getName());
        cityDto.setRegionId(city.getRegionId());

    }

    public Boolean existsById(Long id) {
        return cityRepository.existsById(id);
    }

}
