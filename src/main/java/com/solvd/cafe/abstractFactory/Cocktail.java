package com.solvd.cafe.abstractFactory;

public class Cocktail extends Menu {
    private String name;
    private String servingPortion;
    public Cocktail(String name, String servingPortion) {
        this.name = name;
        this.servingPortion = servingPortion;
    }

    @Override
    public String getDetails() {
        return "Cocktail name: " + this.name + " serving portion is" + this.servingPortion;
    }

    @Override
    public String toString() {
        return "Cocktail{" +
                "name='" + name + '\'' +
                ", servingPortion='" + servingPortion + '\'' +
                '}';
    }
}
