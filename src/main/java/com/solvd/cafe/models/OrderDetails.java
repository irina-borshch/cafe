package com.solvd.cafe.models;

import java.util.Objects;

public class OrderDetails {
    private int id;
    private int menuItemsQty;
    private int menuItemId;
    private int ordersId;
    public OrderDetails() {

    }
    public OrderDetails(int id, int menuItemsQty, int menuItemId, int ordersId) {
        this.id = id;
        this.menuItemsQty = menuItemsQty;
        this.menuItemId = menuItemId;
        this.ordersId = ordersId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMenuItemsQty() {
        return menuItemsQty;
    }

    public void setMenuItemsQty(int menuItemsQty) {
        this.menuItemsQty = menuItemsQty;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public int getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(int ordersId) {
        this.ordersId = ordersId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetails that = (OrderDetails) o;
        return id == that.id && menuItemsQty == that.menuItemsQty && menuItemId == that.menuItemId && ordersId == that.ordersId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, menuItemsQty, menuItemId, ordersId);
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "id=" + id +
                ", menuItemsQty=" + menuItemsQty +
                ", menuItemId=" + menuItemId +
                ", ordersId=" + ordersId +
                '}';
    }
}
