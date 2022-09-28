package com.solvd.cafe.models;

import java.util.Objects;

public class Orders {
    private int id;
    private int guestsId;

    public Orders() {

    }
    public Orders(int id, int guestsId) {
        this.id = id;
        this.guestsId = guestsId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGuestsId() {
        return guestsId;
    }

    public void setGuestsId(int guestsId) {
        this.guestsId = guestsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return id == orders.id && guestsId == orders.guestsId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, guestsId);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", guestsId=" + guestsId +
                '}';
    }
}
