package com.graduation.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "voit")
public class Voit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voit_id")
    private int voit_id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "rest_id")
    private int restId;

    @Column(name = "date")
    @JsonIgnore
    private LocalDateTime localDateTime;

    public Voit(int userId, int restId) {
        this.userId = userId;
        this.restId = restId;
    }

    public Voit() {
    }

    public int getVoit_id() {
        return voit_id;
    }

    public void setVoit_id(int voit_id) {
        this.voit_id = voit_id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRestId() {
        return restId;
    }

    public void setRestId(int restId) {
        this.restId = restId;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
