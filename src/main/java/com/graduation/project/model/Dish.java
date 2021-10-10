package com.graduation.project.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "dish")
public class Dish {

    @Id
    @Column(name = "dish_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int dih_id;

    @Column(name = "date")
    private Date date;

    @Column(name = "dish")
    private String dish;

    @Column(name = "price")
    private double price;

//    @ManyToOne(targetEntity = Restaurant.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "rest_id", insertable = false, updatable = false)
    @Column(name = "rest_id")
    private int rest_id;

    public int getDih_id() {
        return dih_id;
    }

    public void setDih_id(int dih_id) {
        this.dih_id = dih_id;
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
