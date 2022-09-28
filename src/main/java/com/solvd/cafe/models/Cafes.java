package com.solvd.cafe.models;

import java.util.Objects;

public class Cafes {
    private int id;
    private int cafeAddressesId;
    private int menuId;
    private String cafeName;

    public Cafes(){

    }
    public Cafes(int id, int cafeAddressesId, int menuId, String cafeName) {
        this.id = id;
        this.cafeAddressesId = cafeAddressesId;
        this.menuId = menuId;
        this.cafeName = cafeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCafeAddressesId() {
        return cafeAddressesId;
    }

    public void setCafeAddressesId(int cafeAddressesId) {
        this.cafeAddressesId = cafeAddressesId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getCafeName() {
        return cafeName;
    }

    public void setCafeName(String cafeName) {
        this.cafeName = cafeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cafes cafes = (Cafes) o;
        return id == cafes.id && cafeAddressesId == cafes.cafeAddressesId && menuId == cafes.menuId && cafeName.equals(cafes.cafeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cafeAddressesId, menuId, cafeName);
    }

    @Override
    public String toString() {
        return "Cafes{" +
                "id=" + id +
                ", cafeAddressesId=" + cafeAddressesId +
                ", menuId=" + menuId +
                ", cafeName='" + cafeName + '\'' +
                '}';
    }
}
