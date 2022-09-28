package com.solvd.cafe.models;

import java.util.Objects;

public class Tables {
    private int id;
    private int seatingSize;
    private int cafesId;

    public Tables(){


    }
    public Tables(int id, int seatingSize, int cafesId) {
        this.id = id;
        this.seatingSize = seatingSize;
        this.cafesId = cafesId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeatingSize() {
        return seatingSize;
    }

    public void setSeatingSize(int seatingSize) {
        this.seatingSize = seatingSize;
    }

    public int getCafesId() {
        return cafesId;
    }

    public void setCafesId(int cafesId) {
        this.cafesId = cafesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tables tables = (Tables) o;
        return id == tables.id && seatingSize == tables.seatingSize && cafesId == tables.cafesId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, seatingSize, cafesId);
    }

    @Override
    public String toString() {
        return "Tables{" +
                "id=" + id +
                ", seatingSize=" + seatingSize +
                ", cafesId=" + cafesId +
                '}';
    }
}
