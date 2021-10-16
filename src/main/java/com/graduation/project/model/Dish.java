package com.graduation.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "dish")
public class Dish {

    @Id
    @Column(name = "dish_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int dish_id;

    @Column(name = "date")
    private Date date;

    @Column(name = "dish")
    private String dish;

    @Column(name = "price")
    private double price;

    @Column(name = "rest_id")
    @JsonIgnore
    private int rest_id;

    public int getDish_id() {
        return dish_id;
    }

    public void setDish_id(int dih_id) {
        this.dish_id = dih_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public int getRest_id() {
        return rest_id;
    }

    public void setRest_id(int rest_id) {
        this.rest_id = rest_id;
    }
}
