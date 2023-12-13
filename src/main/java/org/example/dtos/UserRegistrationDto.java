package org.example.dtos;

import jakarta.validation.constraints.*;
import org.example.utils.validation.UniqueEmail;
import org.example.utils.validation.UniqueUserName;

public class UserRegistrationDto {

    @UniqueUserName
    private String userName;

    private String fullName;

    @UniqueEmail
    private String email;

    private int age;

    private String password;

    private String confirmPassword;

    public UserRegistrationDto() {
    }

    public UserRegistrationDto(String userName, String fullName, String email, int age, String password, String confirmPassword) {
        this.userName = userName;
        this.fullName = fullName;
        this.email = email;
        this.age = age;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    @NotEmpty(message = "User name cannot be null or empty!")
    @Size(min = 5, max = 20)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @NotEmpty(message = "Full name cannot be null or empty!")
    @Size(min = 5, max = 20)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    @NotEmpty(message = "Email cannot be null or empty!")
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @Min(0)
    @Max(90)
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    @NotEmpty(message = "Password cannot be null or empty!")
    @Size(min = 5, max = 20)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @NotEmpty(message = "Confirm Password cannot be null or empty!")
    @Size(min = 5, max = 20)
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "UserRegistrationDTO{" +
                "username='" + userName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }


}
