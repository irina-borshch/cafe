package com.solvd.cafe.models;

import java.util.Objects;

public class Discounts {
    private int id;
    private String discountType;
    private double discountSize;
    public Discounts() {

    }
    public Discounts(int id, String discountType, double discountSize){
        this.id = id;
        this.discountType = discountType;
        this.discountSize = discountSize;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public double getDiscountSize() {
        return discountSize;
    }

    public void setDiscountSize(double discountSize) {
        this.discountSize = discountSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discounts discounts = (Discounts) o;
        return id == discounts.id && Double.compare(discounts.discountSize, discountSize) == 0 && discountType.equals(discounts.discountType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, discountType, discountSize);
    }

    @Override
    public String toString() {
        return "Discounts{" +
                "id=" + id +
                ", discountType='" + discountType + '\'' +
                ", discountSize=" + discountSize +
                '}';
    }
}
