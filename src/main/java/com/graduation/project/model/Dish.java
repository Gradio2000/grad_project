package com.graduation.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "dish", indexes = {
        @Index(name = "idx_dish_dish_id", columnList = "dish_id")
})
public class Dish {


    @Id
    @Column(name = "dish_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int dishId;

    @Column(name = "date")
    @JsonIgnore
    private LocalDate localDate;

    @Column(name = "dish")
    private String dish;

    @Column(name = "price")
    @DecimalMin(value = "0.01", message = "Некорректное число")
    @Digits(integer = 6, fraction = 2, message = "Некорректное число")
    private BigDecimal price;

    @JsonIgnore
    @Column(name = "rest_id")
    private int restId;

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dih_id) {
        this.dishId = dih_id;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDateTime) {
        this.localDate = localDateTime;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getRestId() {
        return restId;
    }

    public void setRestId(int rest_id) {
        this.restId = rest_id;
    }
}
