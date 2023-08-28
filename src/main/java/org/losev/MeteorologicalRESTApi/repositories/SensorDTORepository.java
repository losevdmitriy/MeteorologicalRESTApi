package org.losev.MeteorologicalRESTApi.repositories;

import org.losev.MeteorologicalRESTApi.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorDTORepository extends JpaRepository<Sensor,Integer> {
    Optional<Sensor> findByName(String name);
}
