package com.graduation.project.model;

import javax.persistence.*;

@Entity
@Table(name = "restaurant")
public class Restaurant {
    @Id
    @Column(name = "rest_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
