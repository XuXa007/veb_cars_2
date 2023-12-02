package org.example.models;

import jakarta.persistence.*;
import org.example.Enums.Category;
import org.example.dtos.ShowModelInfoDto;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@Table(name = "model")
public class Models extends Base {
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "models", cascade = CascadeType.REMOVE)
    private List<Offer> offers;

    @Column(name="name", length = 255, nullable = false)
    private String name;
    @Enumerated(EnumType.ORDINAL)
    @Column(name="category", length = 11, nullable = false)
    private Category category;
    @Column(name="imageURL", length = 255)
    private String imageURL;
    @Column(name="startYear", length = 11, nullable = false)
    private int startYear;
    @Column(name="endYear", length = 11, nullable = false)
    private int endYear;
    @Column(name="created", length = 6, nullable = false)
    private LocalDateTime created;
    @Column(name="modified", length = 6, nullable = false)
    private LocalDateTime modified;

    public Models() {
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageUrl(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
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
