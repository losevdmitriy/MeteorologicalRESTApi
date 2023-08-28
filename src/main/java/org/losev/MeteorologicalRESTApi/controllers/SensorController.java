package org.losev.MeteorologicalRESTApi.controllers;

import jakarta.validation.Valid;
import org.losev.MeteorologicalRESTApi.DTO.SensorDTO;
import org.losev.MeteorologicalRESTApi.models.Sensor;
import org.losev.MeteorologicalRESTApi.services.SensorService;
import org.losev.MeteorologicalRESTApi.utill.SensorErrorResponse;
import org.losev.MeteorologicalRESTApi.utill.SensorNameException;
import org.losev.MeteorologicalRESTApi.utill.SensorDTOValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorService sensorService;
    private final ModelMapper modelMapper;

    private final SensorDTOValidator sensorDTOValidator;

    @Autowired
    public SensorController(SensorService sensorService, ModelMapper modelMapper, SensorDTOValidator sensorDTOValidator) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.sensorDTOValidator = sensorDTOValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid SensorDTO sensorDTO, BindingResult bindingResult) {

        sensorDTOValidator.validate(sensorDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            StringBuilder errorMgs = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMgs.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append("; ");
            }
            throw new SensorNameException(errorMgs.toString());
        }

        sensorService.save(convertToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNameException exception) {
        SensorErrorResponse response = new SensorErrorResponse(exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
