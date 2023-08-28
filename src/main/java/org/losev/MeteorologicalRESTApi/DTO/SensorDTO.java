package org.losev.MeteorologicalRESTApi.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.losev.MeteorologicalRESTApi.models.Indication;

import java.util.List;


public class SensorDTO {


    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3, max = 30, message = "Name should be between 3 and 30 char")
    private String name;

    public SensorDTO(String name, List<Indication> indications) {
        this.name = name;
    }

    public SensorDTO() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
