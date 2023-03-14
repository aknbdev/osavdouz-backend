package dev.aknb.osavdouz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.aknb.osavdouz.entities.address.City;
import dev.aknb.osavdouz.entities.address.Region;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class RegionDto {

    @NotBlank
    @Size(max = 100)
    private String name;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private CountryDto countryDto = new CountryDto();
    @NotNull(message = "COUNTRY_REQUIRED")
    private Long countryId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<City> cities = new HashSet<>();


}
