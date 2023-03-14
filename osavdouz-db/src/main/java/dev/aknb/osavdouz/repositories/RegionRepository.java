package dev.aknb.osavdouz.repositories;

import dev.aknb.osavdouz.entities.address.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    boolean existsRegionByName(String name);

    boolean existsByNameAndIdNot(String name, Long id);

}
