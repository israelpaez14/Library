package library.demo.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_roles")
@IdClass(UserRoleId.class)
public class UserRole {


    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username",nullable = false)
    private User user;


    @Id
    @Column(name = "role")
    private String role;

    public UserRole() {

    }


    public UserRole(User user, String role) {
        this.user = user;
        this.role = role;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



}
class UserRoleId implements Serializable {
    private User user;
    private String role;
}
