package com.solvd.cafe.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Bookings {
    private int id;
    private LocalDateTime time;
    private int tablesId;

    public Bookings() {

    }
    public Bookings(int id, LocalDateTime time, int tablesId) {
        this.id = id;
        this.time = time;
        this.tablesId = tablesId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getTablesId() {
        return tablesId;
    }

    public void setTablesId(int tablesId) {
        this.tablesId = tablesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bookings bookings = (Bookings) o;
        return id == bookings.id && tablesId == bookings.tablesId && time.equals(bookings.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time, tablesId);
    }

    @Override
    public String toString() {
        return "Bookings{" +
                "id=" + id +
                ", time=" + time +
                ", tablesId=" + tablesId +
                '}';
    }
}
