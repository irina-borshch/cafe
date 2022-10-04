package com.solvd.cafe.builder;

public class MainBuilder {
    public static void main(String[] args) {
        Cafe cafe1 = new Cafe.CafeBuilder("JONSONUK", "UKRAINE", "Borys Jonsonuk")
                .seatingQty(15)
                .employeesQty(8)
                .build();

        System.out.println(cafe1);

        Cafe cafe2 = new Cafe.CafeBuilder("ZSU", "LONDON", "Valeriy Zaluzhnuy")
                .seatingQty(31)
                //no info about employees quantity
                .build();

        System.out.println(cafe2);

        Cafe cafe3 = new Cafe.CafeBuilder("SLOZY RUSNI", "POLAND", "Andjei Duda")
                //no info about seating qty
                .employeesQty(13)
                .build();
        System.out.println(cafe3);

    }
}
