package com.graduation.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @Column(name = "rest_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int rest_id;

    @Column(name = "name")
    private String name;

    @OneToMany(
            targetEntity = Dish.class,
            cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "rest_id")
    private List<Dish> dishList;

    public int getRest_id() {
        return rest_id;
    }

    public void setRest_id(int rest_id) {
        this.rest_id = rest_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Dish> getDishList() {
        return dishList;
    }

    public void setDishList(List<Dish> dishList) {
        this.dishList = dishList;
    }
}
