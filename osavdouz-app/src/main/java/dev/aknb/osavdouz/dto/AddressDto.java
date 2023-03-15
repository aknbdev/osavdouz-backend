package dev.aknb.osavdouz.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
    @NotBlank
    @Size(max = 100)
    private String streetAddress;

    @NotNull(message = "USER_REQUIRED")
    private Long userId;
    
    @NotNull(message = "CITY_REQUIRED")
    private Long cityId;

}
