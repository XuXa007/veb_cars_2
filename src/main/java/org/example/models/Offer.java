package org.example.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.example.Enums.Engine;
import org.example.Enums.Transmission;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "offer")
public class Offer extends Base {

    @ManyToOne
    @JoinColumn(name = "models_id")
    @JsonIgnore
    private Models models;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private Users users;

    @Column(name="description", length = 500, nullable = false)
    private String description;
    @Column(name="engine", length = 11, nullable = false)
    private Engine engine;
//    @Column(name="imageURL", length = 255, nullable = false)
//    private String imageUrl;
    @Column(name="mileage", length = 11, nullable = false)
    private int mileage;
    @Column(name="price", length = 20, nullable = false)
    private BigDecimal price;
    @Column(name="transmission", length = 11, nullable = false)
    private Transmission transmission;
    @Column(name="year", length = 11, nullable = false)
    private int year;
    @Column(name="created", length = 6, nullable = false)
    private LocalDateTime created;
    @Column(name="modified", length = 6, nullable = false)
    private LocalDateTime modified;

    public Offer() {
    }

    public Models getModel() {
        return models;
    }

    public void setModel(Models models) {
        this.models = models;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
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

//    public String getImageUrl() {
//        return imageUrl;
//    }
//
//    public void setImageUrl(String imageUrl) {
//        this.imageUrl = imageUrl;
//    }

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