package org.example.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.example.utils.validation.UniqueBrandName;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class AddBrandDto {
    @UniqueBrandName
    @NotEmpty(message = "Brand name must not be null or empty!")
    @Size(min = 2, max = 10, message = "Brand name must be between 2 and 10 characters!")
    public String name;
    private LocalDateTime created;
    private LocalDateTime modified;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @DateTimeFormat(pattern = "MM-yyyy")
    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

//    @DateTimeFormat(pattern = "MM-yyyy")
    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }
}
