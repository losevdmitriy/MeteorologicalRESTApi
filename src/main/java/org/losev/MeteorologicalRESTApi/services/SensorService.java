package org.losev.MeteorologicalRESTApi.services;

import org.losev.MeteorologicalRESTApi.models.Sensor;
import org.losev.MeteorologicalRESTApi.repositories.SensorDTORepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class SensorService {
    private final SensorDTORepository sensorRepository;

    @Autowired
    public SensorService(SensorDTORepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public void save(Sensor sensor){
        sensorRepository.save(sensor);
    }

    public Optional<Sensor> findByName(String name){
        return sensorRepository.findByName(name);
    }
}
