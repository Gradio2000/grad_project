package com.graduation.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "voit",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date"}, name = "user_date_unique")})
public class Voit {
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

    public Voit(int userId, int restId) {
        this.userId = userId;
        this.restId = restId;
    }

    public Voit() {
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

    public void setLocalDate(LocalDate localDateTime) {
        this.localDate = localDateTime;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }
}
