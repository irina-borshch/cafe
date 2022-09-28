package com.solvd.cafe.models;

import java.util.Objects;

public class Menu {
    private int id;
    private String menuType;

    public Menu() {

    }

    public Menu(int id, String menuType) {
        this.id = id;
        this.menuType = menuType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return id == menu.id && menuType.equals(menu.menuType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, menuType);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", menuType='" + menuType + '\'' +
                '}';
    }
}
