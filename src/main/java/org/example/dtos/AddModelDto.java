package org.example.dtos;

import jakarta.validation.constraints.*;
import org.example.Enums.Category;
import org.example.models.Brand;
import org.example.utils.validation.UniqueBrandName;
import org.example.utils.validation.UniqueModelName;

import java.time.LocalDateTime;
import java.time.Year;

public class AddModelDto {
    @UniqueModelName
    private String name;
    private String brand;
    private String brandId;
    private Category category;
    private int startYear;
    private int endYear;
    private String imageURL;
    private LocalDateTime created;
    private LocalDateTime modified;

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    @NotEmpty(message = "Model name must not be null or empty!")
    @Size(min = 2, message = "Model name should be at least 2 characters long!")
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

    @NotNull(message = "Start year must not be null or empty!")
    @Min(value = 1885, message = "Start year must be at least 1885")
//    @Max(value = 2025, message = "Start year must be at most 2023")
    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    @Min(value = 1885, message = "Start year must be at least 1885")
//    @Max(value = 2023, message = "Start year must be at most 2023")
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
