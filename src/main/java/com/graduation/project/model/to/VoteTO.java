package com.graduation.project.model.to;

import java.time.LocalDate;
import java.time.LocalTime;

public class VoteTO {
    private int restId;
    private LocalDate localDate;
    private LocalTime localTime;

    public VoteTO(int restId, LocalDate localDate, LocalTime localTime) {
        this.restId = restId;
        this.localDate = localDate;
        this.localTime = localTime;
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
