package com.graduation.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "restaurant", indexes = {
        @Index(name = "idx_restaurant_rest_id", columnList = "rest_id"),
        @Index(name = "idx_restaurant_name", columnList = "name")
})
public class Restaurant {
    @Id
    @Column(name = "rest_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private int restId;

    @Column(name = "name")
    private String name;

    @OneToMany(
            targetEntity = Dish.class,
            cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "rest_id")
    @JsonIgnore
    private List<Dish> dishList;

    public Restaurant(int restId, String name) {
        this.restId = restId;
        this.name = name;
    }

    public Restaurant(String name) {
        this.name = name;
    }

    public Restaurant() {
    }

    public int getRestId() {
        return restId;
    }

    public void setRestId(int rest_id) {
        this.restId = rest_id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
