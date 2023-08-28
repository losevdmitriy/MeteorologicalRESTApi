package org.losev.MeteorologicalRESTApi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "sensors")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3,max = 30, message = "Name should be between 3 and 30 char")
    private String name;

    @OneToMany(mappedBy = "sensor")
    private List<Indication> indications;

    public Sensor(String name, List<Indication> indications) {
        this.name = name;
        this.indications = indications;
    }

    public Sensor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Indication> getIndications() {
        return indications;
    }

    public void setIndications(List<Indication> indications) {
        this.indications = indications;
    }
}
