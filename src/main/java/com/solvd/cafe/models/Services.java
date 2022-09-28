package com.solvd.cafe.models;

import java.util.Objects;

public class Services {
    private int id;
    private String type;

    public Services() {

    }

    public Services(int id, String type) {
        this.id = id;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Services services = (Services) o;
        return id == services.id && type.equals(services.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }

    @Override
    public String toString() {
        return "Services{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }
}
