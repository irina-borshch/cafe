package com.solvd.cafe.models;

import java.util.Objects;

public class MenuItem {
    private int id;
    private String positionName;
    private int servingPortion;
    private String measurementUnit;
    private double price;
    private int menuId;

    public MenuItem() {

    }
    public MenuItem(int id, String positionName, int servingPortion, String measurementUnit, double price, int menuId) {
        this.id = id;
        this.positionName = positionName;
        this.servingPortion = servingPortion;
        this.measurementUnit = measurementUnit;
        this.price = price;
        this.menuId = menuId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public int getServingPortion() {
        return servingPortion;
    }

    public void setServingPortion(int servingPortion) {
        this.servingPortion = servingPortion;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuItem menuItem = (MenuItem) o;
        return id == menuItem.id && servingPortion == menuItem.servingPortion && Double.compare(menuItem.price, price) == 0 && menuId == menuItem.menuId && positionName.equals(menuItem.positionName) && measurementUnit.equals(menuItem.measurementUnit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, positionName, servingPortion, measurementUnit, price, menuId);
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + id +
                ", positionName='" + positionName + '\'' +
                ", servingPortion=" + servingPortion +
                ", measurementUnit='" + measurementUnit + '\'' +
                ", price=" + price +
                ", menuId=" + menuId +
                '}';
    }
}
