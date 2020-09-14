package com.example.weather.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="city")
public class City {

    public City() {}

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String city;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "{\"id\":" + id + ", \"city\":\"" + city +"\", \"user\":" + user.getId() + "}";
    }
}
