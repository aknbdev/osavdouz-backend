package dev.aknb.osavdouz.repositories;

import dev.aknb.osavdouz.entities.address.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    boolean existsCountryByName(String name);

    boolean existsById(Long id);

    boolean existsByNameAndIdNot(String name, Long id);

}
