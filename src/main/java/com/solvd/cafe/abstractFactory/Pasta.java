package com.solvd.cafe.abstractFactory;

public class Pasta extends Menu {
    private String name;
    private String servingPortion;
    private String additionalIngredient;
    public Pasta(String name, String servingPortion, String additionalIngredient) {
        this.name = name;
        this.servingPortion = servingPortion;
        this.additionalIngredient = additionalIngredient;
    }

    @Override
    public String getDetails() {
        return "Pasta name: " + this.name + " serving portion is: " + this.servingPortion+ " chosen additional ingredient: " + this.additionalIngredient;
    }

    @Override
    public String toString() {
        return "Pasta{" +
                "name='" + name + '\'' +
                ", servingPortion='" + servingPortion + '\'' +
                ", additionalIngredient='" + additionalIngredient + '\'' +
                '}';
    }
}
