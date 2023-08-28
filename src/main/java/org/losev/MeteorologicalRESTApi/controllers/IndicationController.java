package org.losev.MeteorologicalRESTApi.controllers;

import jakarta.validation.Valid;
import org.losev.MeteorologicalRESTApi.DTO.IndicationDTO;
import org.losev.MeteorologicalRESTApi.DTO.IndicationOutputDTO;
import org.losev.MeteorologicalRESTApi.models.Indication;
import org.losev.MeteorologicalRESTApi.services.IndicationService;
import org.losev.MeteorologicalRESTApi.utill.IndicationErrorResponse;
import org.losev.MeteorologicalRESTApi.utill.IndicationFieldException;
import org.losev.MeteorologicalRESTApi.utill.SensorErrorResponse;
import org.losev.MeteorologicalRESTApi.utill.SensorNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class IndicationController {

    private final IndicationService indicationService;
    private final ModelMapper modelMapper;

    @Autowired
    public IndicationController(IndicationService indicationService, ModelMapper modelMapper) {
        this.indicationService = indicationService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<IndicationOutputDTO> getAll() {
        return indicationService.getAll().stream().map(this::convertToIndicationOutputDTO).collect(Collectors.toList());
    }


    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid IndicationDTO indicationDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMgs = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMgs.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append("; ");
            }
            throw new IndicationFieldException(errorMgs.toString());
        }
        indicationService.save(convertToIndication(indicationDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/rainyDaysCount")
    public ResponseEntity rainyDaysCount() {
        Map<String, Integer> response = new HashMap<>();
        response.put("days", indicationService.countRainyDays());
        return ResponseEntity.ok().body(response);
    }


    private Indication convertToIndication(IndicationDTO indicationDTO) {
        return modelMapper.map(indicationDTO, Indication.class);
    }

    private IndicationOutputDTO convertToIndicationOutputDTO(Indication indication) {
        return modelMapper.map(indication, IndicationOutputDTO.class);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotFoundException exception) {
        SensorErrorResponse response = new SensorErrorResponse("Sensor not found", System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<IndicationErrorResponse> handleException(IndicationFieldException exception) {
        IndicationErrorResponse response = new IndicationErrorResponse(exception.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
