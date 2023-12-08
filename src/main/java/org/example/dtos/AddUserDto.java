package org.example.dtos;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;


public class AddUserDto {
    private String userName;
    private String role;
    private String fullname;
    private int password;
    private LocalDateTime created;
    private LocalDateTime modified;

    @NotEmpty(message = "Name must not be null or empty!")
    @Size(min = 2, message = "Name must be more 2 !")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @NotNull(message = "password must not be null or empty!")
//    @Size(min = 10, message = "password must be at least 1 characters!")
    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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