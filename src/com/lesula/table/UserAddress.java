package com.lesula.table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by enrico on 10/1/14.
 */

@Entity
public class UserAddress implements Serializable {


    private int userId;
    LoginUser user;
    private String city;
    private String street;


    @Id
    @GeneratedValue(generator = "gen")
    @GenericGenerator(name="gen", strategy="foreign",
            parameters=@Parameter(name="property", value="user"))
    @Column(name = "UserId", unique = true, nullable = true)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    public LoginUser getUser() {
        return user;
    }

    public void setUser(LoginUser user) {
        this.user = user;
    }

    @Column(name="City")
    public String getCity() {
        return city;
    }


    public void setCity(String city) {
        this.city = city;
    }

    @Column(name="Street")
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}