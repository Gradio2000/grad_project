package com.graduation.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "dish")
public class Dish {

    @Id
    @Column(name = "dish_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int dishId;

    @Column(name = "date")
    private LocalDateTime localDateTime;

    @Column(name = "dish")
    private String dish;

    @Column(name = "price")
    private double price;

    @Column(name = "rest_id")
    private int restId;

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dih_id) {
        this.dishId = dih_id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getRestId() {
        return restId;
    }

    public void setRestId(int rest_id) {
        this.restId = rest_id;
    }
}
