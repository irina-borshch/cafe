package com.solvd.cafe.models;

import java.util.Objects;

public class Payments {
    private int id;
    private double totalPrice;
    private int ordersId;
    private int discountsId;

    public Payments() {

    }

    public Payments(int id, double totalPrice, int ordersId, int discountsId) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.ordersId = ordersId;
        this.discountsId = discountsId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(int ordersId) {
        this.ordersId = ordersId;
    }

    public int getDiscountsId() {
        return discountsId;
    }

    public void setDiscountsId(int discountsId) {
        this.discountsId = discountsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payments payments = (Payments) o;
        return id == payments.id && Double.compare(payments.totalPrice, totalPrice) == 0 && ordersId == payments.ordersId && discountsId == payments.discountsId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalPrice, ordersId, discountsId);
    }

    @Override
    public String toString() {
        return "Payments{" +
                "id=" + id +
                ", totalPrice=" + totalPrice +
                ", ordersId=" + ordersId +
                ", discountsId=" + discountsId +
                '}';
    }
}
