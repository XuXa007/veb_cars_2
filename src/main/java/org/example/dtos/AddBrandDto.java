package org.example.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.example.utils.validation.UniqueBrandName;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDateTime;

public class AddBrandDto {
    @UniqueBrandName
    @NotEmpty(message = "Brand name must not be null or empty!")
    @Size(min = 2, message = "Brand name should be at least 2 characters long!")
    public String name;
    private LocalDateTime created;
    private LocalDateTime modified;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
