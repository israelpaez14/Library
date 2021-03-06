package library.demo.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users", schema = "library")
@JsonIgnoreProperties({"comments","publications","likes"})
public class User {

    @Id
    @Column(name = "username", unique = true, nullable = false, length = 50)
    private String userName;
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @JsonIgnore
    @Column(name = "password", nullable = false, length = 100)
    private String password;
    @Column(name = "enable", nullable = false)
    private boolean enable;

    @Email
    @Column
    private String email;

    @Column(name = "profile_picture")
    private  String profilePicture;

    @JsonBackReference(value = "comments")
    //@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OneToMany(mappedBy = "username", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserRole> userRole = new HashSet<UserRole>(0);

    @JsonBackReference(value = "publications")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Publication> publications = new ArrayList<>(0);

    @JsonBackReference(value = "likes")
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Like> likes = new ArrayList<>(0);

    public User() {

    }

    public User(String userName, String password, Set<UserRole> userRoles) {
        this.userName = userName;
        this.password = password;
        this.userRole = userRoles;
        this.enable = true;
    }

    public User(String username, String password, boolean enabled) {
        this.userName = username;
        this.password = password;
        this.enable = enabled;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty(value = "password")
    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public Set<UserRole> getUserRole() {
        return userRole;
    }

    public void setUserRole(Set<UserRole> userRole) {
        this.userRole = userRole;
    }

    public List<Publication> getPublications() {
        return publications;
    }

    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }


    @Override
    public String toString() {
        return this.userName;
    }
}
