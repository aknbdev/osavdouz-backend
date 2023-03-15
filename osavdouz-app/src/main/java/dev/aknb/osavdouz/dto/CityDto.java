package dev.aknb.osavdouz.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityDto {
    @NotBlank
    @Size(max = 100)
    private String name;


    @NotNull(message = "REGION_REQUIRED")
    private Long regionId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private RegionDto regionDto = new RegionDto();

}
