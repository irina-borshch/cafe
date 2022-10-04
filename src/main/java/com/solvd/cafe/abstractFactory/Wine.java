package com.solvd.cafe.abstractFactory;

public class Wine extends Menu {
    private String name;
    private String servingPortion;
    public Wine(String name, String servingPortion) {
        this.name = name;
        this.servingPortion = servingPortion;
    }

    @Override
    public String getDetails() {
        return "Wine name: " + this.name + " serving portion is: " + this.servingPortion;
    }

    @Override
    public String toString() {
        return "Wine{" +
                "name='" + name + '\'' +
                ", servingPortion='" + servingPortion + '\'' +
                '}';
    }
}
