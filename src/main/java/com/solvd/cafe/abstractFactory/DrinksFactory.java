package com.solvd.cafe.abstractFactory;

public class DrinksFactory extends AbstractMenuFactory{
    @Override
    Menu getDetails(MenuType type) {
        switch (type) {
            case WINE:
                return new Wine("Cabernet Sauvignon", "150 ml");
            case COCKTAIL:
                return new Cocktail("Gin and Tonic", "190 ml");
        }
        return null;
    }
}
