package dev.aknb.osavdouz.service;

import dev.aknb.osavdouz.dto.RegionDto;
import dev.aknb.osavdouz.entities.address.Region;
import dev.aknb.osavdouz.repositories.RegionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegionService {

    private final RegionRepository regionRepository;
    private final CountryService countryService;

    public RegionService(RegionRepository regionRepository, CountryService countryService) {
        this.regionRepository = regionRepository;
        this.countryService = countryService;
    }

    public List<Region> getRegionAll() {
        return regionRepository.findAll();
    }

    public Region getRegionById(Long id) {
        Optional<Region> optionalRegion = regionRepository.findById(id);
        return optionalRegion.orElse(null);
    }

    public RegionDto createRegion(RegionDto regionDto) {
        Boolean existsById = countryService.existsById(regionDto.getCountryId());
        if (!existsById) {
            return null;
        }
        boolean existsCountryByName = regionRepository.existsRegionByName(regionDto.getName());
        if (existsCountryByName) {
            return null;
        }
        Region region = new Region();
        updateDtoToRegion(regionDto, region);
        region = regionRepository.save(region);
        updateRegionToDto(region, regionDto);
        return regionDto;

    }

    public RegionDto updateRegion(Long id, RegionDto regionDto) {
        boolean existsByNameAndIdNot = regionRepository.existsByNameAndIdNot(regionDto.getName(), id);
        if (existsByNameAndIdNot) {
            return null;
        }
        Optional<Region> optionalRegion = regionRepository.findById(id);
        if (optionalRegion.isEmpty()) {
            return null;
        }
        Region region = optionalRegion.get();
        updateDtoToRegion(regionDto, region);
        region = regionRepository.save(region);
        updateRegionToDto(region, regionDto);
        return regionDto;
    }

    public void deleteRegion(Long id) {
        if (regionRepository.existsById(id)) {
//            throw new null;
        }
        regionRepository.deleteById(id);
    }


    public void updateDtoToRegion(RegionDto regionDto, Region region) {
        region.setName(regionDto.getName());
        region.setCountryId(regionDto.getCountryId());
    }

    public void updateRegionToDto(Region region, RegionDto regionDto) {
        regionDto.setName(region.getName());
        regionDto.setCountryId(region.getCountryId());
        regionDto.setCities(region.getCities());
        countryService.updateCountryToDto(region.getCountry(), regionDto.getCountryDto());
    }


}
