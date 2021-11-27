package com.graduation.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "voit")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voit_id")
    @JsonIgnore
    private int voitId;

    @JsonIgnore
    @Column(name = "user_id")
    private int userId;

    @Column(name = "rest_id")
    private int restId;

    @Column(name = "date_time")
    @JsonIgnore
    private LocalDateTime localDateTime;


    public Vote(int userId, int restId) {
        this.userId = userId;
        this.restId = restId;
    }

    public Vote() {
    }

    public int getVoitId() {
        return voitId;
    }

    public void setVoitId(int voit_id) {
        this.voitId = voit_id;
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
