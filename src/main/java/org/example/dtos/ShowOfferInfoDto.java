package org.example.dtos;

import org.example.Enums.Engine;
import org.example.Enums.Transmission;

import java.math.BigDecimal;

public class ShowOfferInfoDto {
    private String id;
    private ShowModelInfoDto model;
    private ShowUserInfoDto users;
    private String description;
    private Engine engine;
    private int mileage;
    private BigDecimal price;
    private Transmission transmission;
    private int year;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ShowModelInfoDto getModel() {
        return model;
    }

    public void setModel(ShowModelInfoDto model) {
        this.model = model;
    }

    public ShowUserInfoDto getUsers() {
        return users;
    }

    public void setUsers(ShowUserInfoDto users) {
        this.users = users;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
