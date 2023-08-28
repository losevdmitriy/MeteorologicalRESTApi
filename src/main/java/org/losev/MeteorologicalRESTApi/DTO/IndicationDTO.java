package org.losev.MeteorologicalRESTApi.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.losev.MeteorologicalRESTApi.models.Sensor;

import java.util.Date;

public class IndicationDTO {


    @Min(value = -100, message = "more then -100")
    @Max(value = 100, message = "less then 100")
    @NotNull(message = "Value should not be empty")
    private double value;

    @NotNull(message = "Raining should not be empty")
    private boolean raining;

    @NotNull
    private Sensor sensor;

    private Date time;

    public IndicationDTO(double value, boolean raining, Sensor sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public IndicationDTO() {
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
