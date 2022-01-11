package com.graduation.project.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "vote",
        indexes = {@Index(name = "idx_vote_user_id", columnList = "user_id")},
        uniqueConstraints = { @UniqueConstraint(columnNames = { "date", "user_id" })}
        )
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vote_id")
    @JsonIgnore
    private int voteId;

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

    public int getVoteId() {
        return voteId;
    }

    public void setVoteId(int vote_id) {
        this.voteId = vote_id;
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
