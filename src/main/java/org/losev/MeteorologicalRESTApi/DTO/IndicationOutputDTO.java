package org.losev.MeteorologicalRESTApi.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.Date;

public class IndicationOutputDTO {
    @Min(value = -100, message = "more then -100")
    @Max(value = 100, message = "less then 100")
    private double value;

    private boolean raining;

    private Date time;

    public IndicationOutputDTO(double value, boolean raining, Date time) {
        this.value = value;
        this.raining = raining;
        this.time = time;
    }

    public IndicationOutputDTO() {
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
