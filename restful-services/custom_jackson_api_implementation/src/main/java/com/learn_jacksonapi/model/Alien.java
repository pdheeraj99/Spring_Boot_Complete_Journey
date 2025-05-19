package com.learn_jacksonapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// FYI: JsonIgnoreProperties => It is by default implemented in Jackson API
@JsonIgnoreProperties(ignoreUnknown = true)
public class Alien {

    private Integer id;

    private String name;

    private String planet;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public Alien() {
        super();
    }

    public Alien(Integer id, String name, String planet) {
        super();
        this.id = id;
        this.name = name;
        this.planet = planet;
    }

    @Override
    public String toString() {
        return "Alien [id=" + id + ", name=" + name + ", planet=" + planet + "]";
    }

}
