package com.solvd.cafe.abstractFactory;

import java.sql.SQLOutput;
import java.util.SortedMap;

public class Client {
    public static void main(String[] args) {
        Menu wine = FactoryGenerator.getFactory(FactoryType.DRINKSFACTORY).getDetails(MenuType.WINE);
        Menu cocktail = FactoryGenerator.getFactory(FactoryType.DRINKSFACTORY).getDetails(MenuType.WINE);
        System.out.println(wine.getDetails());
        System.out.println(cocktail.getDetails());
        Menu pasta = FactoryGenerator.getFactory(FactoryType.MEALSFACTORY).getDetails(MenuType.PASTA);
        Menu pizza = FactoryGenerator.getFactory(FactoryType.MEALSFACTORY).getDetails(MenuType.PASTA);
        System.out.println(pasta.getDetails());
        System.out.println(pizza.getDetails());
    }
}
