package dev.aknb.osavdouz.repositories;

import dev.aknb.osavdouz.entities.address.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

    boolean existsCityByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);

}
