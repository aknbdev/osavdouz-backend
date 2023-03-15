package dev.aknb.osavdouz.repositories;

import dev.aknb.osavdouz.entities.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    boolean existsAddressByStreetAddress(String streetAddress);


    boolean existsByStreetAddressAndIdNot(String streetAddress, Long id);

}
