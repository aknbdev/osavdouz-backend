package dev.aknb.osavdouz.controllers;

import dev.aknb.osavdouz.constants.ApiConstants;
import dev.aknb.osavdouz.dto.AddressDto;
import dev.aknb.osavdouz.entities.address.Address;
import dev.aknb.osavdouz.service.AddressService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(ApiConstants.API_ADDRESS)
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<List<Address>> getAddressAll() {
        log.info("REST request to getAll address");
        List<Address> addressList = addressService.getAddressAll();
        return ResponseEntity.ok(addressList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Address> getAddress(@PathVariable Long id) {
        log.info("REST request to get address by id");
        Address address = addressService.getAddressById(id);
        return ResponseEntity.ok(address);

    }

    @PostMapping
    public ResponseEntity<AddressDto> createAddress(@Valid @RequestBody AddressDto addressDto){
        log.info("REST request to address");
        return ResponseEntity.ok(addressService.createAddress(addressDto));
    }
   @PutMapping(path = "/{id}")
   public ResponseEntity<AddressDto> updateAddress(@PathVariable Long id,@Valid @RequestBody AddressDto addressDto){
        log.info("REST request to update address");
        return ResponseEntity.ok(addressService.updateAddress(id, addressDto));
   }
   @DeleteMapping(path = "/{id}")
   public ResponseEntity<Boolean> deleteAddress(@PathVariable Long id){
        log.info("delete address by id");
        addressService.deleteAddress(id);
        return ResponseEntity.ok(Boolean.TRUE);
   }
}
