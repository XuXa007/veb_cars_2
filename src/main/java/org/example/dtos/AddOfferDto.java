package org.example.dtos;

import jakarta.validation.constraints.*;
import org.example.models.Enums.Engine;
import org.example.models.Enums.Transmission;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AddOfferDto {
    private ModelDto model;
    private UsersDto users;
    private String description;
    private Engine engine;
    private int mileage;
    private BigDecimal price;
    private Transmission transmission;
    private int year;
    private String modelId;
    private String userId;

    private LocalDateTime created;
    private LocalDateTime modified;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public ModelDto getModel() {
        return model;
    }

    public void setModel(ModelDto model) {
        this.model = model;
    }

    public UsersDto getUsers() {
        return users;
    }

    public void setUsers(UsersDto users) {
        this.users = users;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    @NotEmpty(message = "Description must not be null or empty!")
    @Size(min = 2, message = "Description should be at least 2 characters long!")
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

    @Min(value = 1, message = "Mileage must not be null or empty!")
//    @Max(value = 2023, message = "Mileage  must be at most 2023")
    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    @NotNull(message = "Price must not be null or empty!")
    @DecimalMin(value = "0.0", inclusive = false)
//    @DecimalMax("30.00")
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

    @Min(value = 1885, message = "Year must be at least 1885")
//    @Max(value = 2023, message = "Year must be at most 2023")
    @NotNull(message = "Year must not be null or empty!")
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }
}
