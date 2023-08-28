package org.losev.MeteorologicalRESTApi.services;

import org.losev.MeteorologicalRESTApi.models.Indication;
import org.losev.MeteorologicalRESTApi.models.Sensor;
import org.losev.MeteorologicalRESTApi.repositories.IndicationRepository;
import org.losev.MeteorologicalRESTApi.repositories.SensorDTORepository;
import org.losev.MeteorologicalRESTApi.utill.SensorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class IndicationService {

    private final IndicationRepository indicationRepository;
    private final SensorDTORepository sensorRepository;

    @Autowired
    public IndicationService(IndicationRepository indicationRepository, SensorDTORepository sensorRepository) {
        this.indicationRepository = indicationRepository;
        this.sensorRepository = sensorRepository;
    }

    public void save(Indication indication) {
        Optional<Sensor> sensor = sensorRepository.findByName(indication.getSensor().getName());
        if (sensor.isPresent()) {
            indication.setTime(new Date());
            indication.setSensor(sensor.get());
        } else {
            throw new SensorNotFoundException();
        }

        indicationRepository.save(indication);
    }

    public List<Indication> getAll() {
        return indicationRepository.findAll();
    }

    public Integer countRainyDays() {
        return indicationRepository.countAllByRainingIsTrue();
    }
}
