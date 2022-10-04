package com.solvd.cafe.models;

import java.sql.Timestamp;
import java.util.Objects;

public class Bookings {
    private int id;
    private Timestamp timestamp;
    private int tablesId;

    public Bookings() {

    }
    public Bookings(int id, Timestamp time, int tablesId) {
        this.id = id;
        this.timestamp = time;
        this.tablesId = tablesId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
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
        return id == bookings.id && tablesId == bookings.tablesId && timestamp.equals(bookings.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timestamp, tablesId);
    }

    @Override
    public String toString() {
        return "Bookings{" +
                "id=" + id +
                ", time=" + timestamp +
                ", tablesId=" + tablesId +
                '}';
    }


}
