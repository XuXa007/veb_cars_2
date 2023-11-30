package org.example.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.example.utils.validation.UniqueBrandName;
import org.example.utils.validation.UniqueModelName;

import java.time.LocalDateTime;
import java.time.Year;

public class AddModelDto {
    @UniqueModelName
    private String name;

    private String category;
    private int startYear;
    private int endYear;
    private String imageURL;
    private LocalDateTime created;
    private LocalDateTime modified;

    @NotEmpty(message = "Model name must not be null or empty!")
    @Size(min = 2, max = 10, message = "Brand name must be between 2 and 10 characters!")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Min(value = 1000, message = "Start year must be at least 1000")
    @Max(value = 9999, message = "Start year must be at most 9999")
    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    @Min(value = 1885, message = "Start year must be at least 1700")
    @Max(value = 2023, message = "Start year must be at most 9999")
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
}
