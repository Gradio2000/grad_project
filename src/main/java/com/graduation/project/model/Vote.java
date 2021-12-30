package com.graduation.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "voit",
        indexes = {@Index(name = "idx_vote_user_id", columnList = "user_id")},
        uniqueConstraints = { @UniqueConstraint(columnNames = { "date", "user_id" })}
        )
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

    @Column(name = "date")
    @JsonIgnore
    private LocalDate localDate;

    @Column(name = "time")
    @JsonIgnore
    private LocalTime localTime;


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

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }
}
