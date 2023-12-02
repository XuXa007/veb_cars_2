package org.example.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "brand")
public class Brand extends Base {

    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private List<Models> models;

    private String name;

    @Column(name="created", length = 6, nullable = false)
    private LocalDateTime created;
    @Column(name="modified", length = 6, nullable = false)
    private LocalDateTime modified;

    public Brand() {
    }

    public List<Models> getModels() {
        return models;
    }

    public void setModels(List<Models> models) {
        this.models = models;
    }

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