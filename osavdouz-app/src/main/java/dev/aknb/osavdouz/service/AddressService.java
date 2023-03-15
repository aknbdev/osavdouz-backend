package dev.aknb.osavdouz.service;

import dev.aknb.osavdouz.dto.AddressDto;
import dev.aknb.osavdouz.entities.address.Address;
import dev.aknb.osavdouz.repositories.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final CityService cityService;

    public AddressService(AddressRepository addressRepository, CityService cityService) {
        this.addressRepository = addressRepository;
        this.cityService = cityService;
    }

    public List<Address> getAddressAll() {
        return addressRepository.findAll();
    }

    public Address getAddressById(Long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        return optionalAddress.orElse(null);
    }

    public AddressDto createAddress(AddressDto addressDto) {
        Boolean existsById = cityService.existsById(addressDto.getCityId());
        if (!existsById) {
            return null;
        }
        boolean existsAddressByName = addressRepository.existsAddressByStreetAddress(addressDto.getStreetAddress());
        if (existsAddressByName) {
            return null;
        }
        Address address = new Address();
        updateDtoToAddress(addressDto, address);
        address = addressRepository.save(address);
        updateAddressToDto(address, addressDto);
        return addressDto;
    }

    public AddressDto updateAddress(Long id, AddressDto addressDto) {
        boolean existsByNameAndIdNot = addressRepository.existsByStreetAddressAndIdNot(addressDto.getStreetAddress(), id);
        if (existsByNameAndIdNot) {
            return null;
        }
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()) {
            return null;
        }
        Address address = optionalAddress.get();
        updateDtoToAddress(addressDto, address);
        address = addressRepository.save(address);
        updateAddressToDto(address, addressDto);
        return addressDto;
    }

    public void deleteAddress(Long id) {
        if (!addressRepository.existsById(id)) {
//        throw new;
        }
        addressRepository.deleteById(id);
    }


    public void updateDtoToAddress(AddressDto addressDto, Address address) {
        address.setStreetAddress(addressDto.getStreetAddress());
        address.setUserId(addressDto.getUserId());
        address.setCityId(addressDto.getCityId());
    }

    public void updateAddressToDto(Address address, AddressDto addressDto) {
        addressDto.setStreetAddress(address.getStreetAddress());
        addressDto.setUserId(address.getUserId());
        addressDto.setCityId(address.getCityId());
    }

}
