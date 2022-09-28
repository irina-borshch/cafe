package com.solvd.cafe.models;

import java.util.Objects;

public class OrdersHasServices {
    private int id;
    private int ordersId;
    private int servicesId;

    public OrdersHasServices() {

    }
    public OrdersHasServices(int id, int ordersId, int servicesId) {
        this.id = id;
        this.ordersId = ordersId;
        this.servicesId = servicesId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(int ordersId) {
        this.ordersId = ordersId;
    }

    public int getServicesId() {
        return servicesId;
    }

    public void setServicesId(int servicesId) {
        this.servicesId = servicesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersHasServices that = (OrdersHasServices) o;
        return id == that.id && ordersId == that.ordersId && servicesId == that.servicesId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ordersId, servicesId);
    }

    @Override
    public String toString() {
        return "OrdersHasServices{" +
                "id=" + id +
                ", ordersId=" + ordersId +
                ", servicesId=" + servicesId +
                '}';
    }
}
