package com.solvd.cafe.abstractFactory;

public class MealsFactory extends AbstractMenuFactory{
    @Override
    Menu getDetails(MenuType type) {
        switch (type) {
            case PASTA:
                return new Pasta("Casoncelli", "320 g", "cheese");
            case PIZZA:
                return new Pizza("Pepperoni", "670 g", "Jalapeño");
        }
        return null;
    }
}
