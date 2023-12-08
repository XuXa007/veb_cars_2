package org.example.views;

public class UserProfileView {
    private String username;

    private String email;

    private String fullName;

    private int age;

    public UserProfileView() {
    }

    public UserProfileView(String username, String email, String fullName, int age) {
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
