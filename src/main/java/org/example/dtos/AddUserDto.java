package org.example.dtos;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;


public class AddUserDto {
    private String userName;
    private String role;
    private String firstName;
    private int password;
    private String lastName;
    private String imageURL;
    private LocalDateTime created;
    private LocalDateTime modified;

    @NotEmpty(message = "Name must not be null or empty!")
    @Size(min = 2, message = "Name must be more 2 !")
    public String getUserName() {
        return userName;
    }

    @NotNull(message = "password must not be null or empty!")
//    @Size(min = 10, message = "password must be at least 1 characters!")
    public int getPassword() {
        return password;
    }


//    @NotEmpty(message = "Company name must not be null or empty!")
//    @Min(value = 2, message = "firstName must be more than 2!")
    public String getFirstName() {
        return firstName;
    }

//    @NotEmpty(message = "Company name must not be null or empty!")
//    @Min(value = 2,message = "lastName must be more than 2!")
    public String getLastName() {
        return lastName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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