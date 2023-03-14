package dev.aknb.osavdouz.controllers;

import dev.aknb.osavdouz.constants.ApiConstants;
import dev.aknb.osavdouz.dto.RegionDto;
import dev.aknb.osavdouz.entities.address.Region;
import dev.aknb.osavdouz.service.RegionService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstants.API_REGION)
public class RegionController {
    private final RegionService regionService;

    private final Logger log = LoggerFactory.getLogger(CountryController.class);

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping
    public ResponseEntity<List<Region>> getRegionAll() {
        log.info("REST request to getAll regions");
        List<Region> regionList = regionService.getRegionAll();
        return ResponseEntity.ok(regionList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Region> getRegion(@PathVariable Long id) {
        log.info("REST request to get region by id");
        Region region = regionService.getRegionById(id);
        return ResponseEntity.ok(region);
    }


    @PostMapping
    public ResponseEntity<RegionDto> createRegion(@Valid @RequestBody RegionDto regionDto) {
        log.info("REST request to region");
        return ResponseEntity.ok(regionService.createRegion(regionDto));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<RegionDto> updateRegion(@PathVariable Long id, @Valid @RequestBody RegionDto regionDto) {
        log.info("REST request to update region");
        return ResponseEntity.ok(regionService.updateRegion(id, regionDto));
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> deleteRegion(@PathVariable Long id) {
        log.info("delete region by id ");
        regionService.deleteRegion(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }


}
