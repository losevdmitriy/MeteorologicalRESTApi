package org.losev.MeteorologicalRESTApi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "indications")
public class Indication {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "value")
    @Min(value = -100, message = "more then -100")
    @Max(value = 100, message = "less then 100")
    @NotNull(message = "Value should not be empty")
    private double value;

    @Column(name = "raining")
    @NotNull(message = "Raining should not be empty")
    private boolean raining;

    @Column(name = "time")
    private Date time;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sensor_id",referencedColumnName = "id")
    private Sensor sensor;

    public Indication(double value, boolean raining, Sensor sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public Indication() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
