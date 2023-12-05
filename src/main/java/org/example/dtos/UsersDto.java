//package org.example.dtos;
//
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
//import org.hibernate.validator.constraints.Length;
//
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//public class UsersDto {
//    private String id;
//    private RoleDto role;
//    @NotNull
//    @Length(min = 4)
//    private String userName;
//    @NotNull
//    @Length(min = 8)
//    private String password;
////    @NotNull
////    @Length(min = 2)
//    private String firstName;
////    @NotNull
////    @Length(min = 2)
//    private String lastName;
//    private boolean isActive;
//    private String imageURL;
//    private LocalDateTime created;
//    private LocalDateTime modified;
//
//    protected UsersDto() {};
//
//    public UsersDto(String id, RoleDto role, String userName, String password, String firstName, String lastName,
//                    boolean isActive, String imageURL, LocalDateTime created, LocalDateTime modified) {
//        this.id = id;
//        this.role = role;
//        this.userName = userName;
//        this.password = password;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.isActive = isActive;
//        this.imageURL = imageURL;
//        this.created = created;
//        this.modified = modified;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public RoleDto getRole() {
//        return role;
//    }
//
//    public void setRole(RoleDto role) {
//        this.role = role;
//    }
//
//    @NotNull
//    @Size(min = 1, max = 10)
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    @NotNull
//    @Size(min = 8, max = 24)
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//    @NotNull
//    @Size(min = 1, max = 100)
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//    @NotNull
//    @Size(min = 1, max = 100)
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public boolean isActive() {
//        return isActive;
//    }
//
//    public void setActive(boolean active) {
//        isActive = active;
//    }
//
//    public String getImageURL() {
//        return imageURL;
//    }
//
//    public void setImageURL(String imageURL) {
//        this.imageURL = imageURL;
//    }
//
//    public LocalDateTime getCreated() {
//        return created;
//    }
//
//    public void setCreated(LocalDateTime created) {
//        this.created = created;
//    }
//
//    public LocalDateTime getModified() {
//        return modified;
//    }
//
//    public void setModified(LocalDateTime modified) {
//        this.modified = modified;
//    }
//
//    @Override
//    public String toString() {
//        return "UsersDto{" +
//                "id=" + id +
//                ", role=" + role +
//                ", userName='" + userName + '\'' +
//                ", password='" + password + '\'' +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", isActive=" + isActive +
//                ", imageURL='" + imageURL + '\'' +
//                ", created=" + created +
//                ", modified=" + modified +
//                '}';
//    }
//}
