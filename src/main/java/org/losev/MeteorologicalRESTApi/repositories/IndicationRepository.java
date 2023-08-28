package org.losev.MeteorologicalRESTApi.repositories;

import org.losev.MeteorologicalRESTApi.models.Indication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IndicationRepository extends JpaRepository<Indication, Integer> {

    public Integer countAllByRainingIsTrue();
}
