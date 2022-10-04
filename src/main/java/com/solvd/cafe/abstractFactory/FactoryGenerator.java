package com.solvd.cafe.abstractFactory;

public class FactoryGenerator {
    public static AbstractMenuFactory getFactory(FactoryType factoryType) {
        switch (factoryType) {
            case DRINKSFACTORY:
                return new DrinksFactory();
            case MEALSFACTORY:
                return new MealsFactory();
        }
        return null;
    }
}
