package org.example.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "users")
public class Users extends Base {
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> role;

    @OneToMany(mappedBy = "users", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Offer> offers;
    @Column(name="userName", length = 255, nullable = false)
    private String userName;
    @Column(name="password", length = 255, nullable = false)
    private String password;

    @Column(name = "full_name")
    private String fullName;
    private boolean isActive;
    private String email;
    private int age;

//    @Column(name="created", length = 6, nullable = false)
//    private LocalDateTime created;
//    @Column(name="modified", length = 6, nullable = false)
//    private LocalDateTime modified;

    public Users() {
    }

    public Users(String userName, String password, String fullName, String email, int age) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.age = age;
    }

    public Users(Object username, String password, Object collect) {
        super();
    }


    @ManyToMany(fetch = FetchType.EAGER)
    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

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

    @Column(unique = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
