package com.solvd.cafe.models;

import java.util.Objects;

public class Guests {
    private int id;
    private String name;
    private String lastName;
    private int bookingsId;

    public Guests(){

    }
    public Guests(int id, String name, String  lastName, int bookingsId) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.bookingsId = bookingsId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getBookingsId() {
        return bookingsId;
    }

    public void setBookingsId(int bookingsId) {
        this.bookingsId = bookingsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guests guests = (Guests) o;
        return id == guests.id && bookingsId == guests.bookingsId && name.equals(guests.name) && lastName.equals(guests.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, bookingsId);
    }

    @Override
    public String toString() {
        return "Guests{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", bookingsId=" + bookingsId +
                '}';
    }
}
