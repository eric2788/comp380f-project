package ouhk.comps380f.dao;

import org.hibernate.annotations.Type;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class User {

    @Id
    @Column(columnDefinition = "varchar(15)", nullable = false)
    private String username;

    @Column(columnDefinition = "longtext")
    private String password;
    private Integer phone;
    @Column(columnDefinition = "tinytext")
    private String fullname;
    @Column(columnDefinition = "longtext")
    private String address;
    private Boolean admin;


    @OneToMany
    private final Set<Comment> comments = new HashSet<>();

    @OneToMany
    private final Set<Order> orders = new HashSet<>();

    public Set<Comment> getComments() {
        return comments;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
