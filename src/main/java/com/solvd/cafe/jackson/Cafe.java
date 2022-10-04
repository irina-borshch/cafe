package com.solvd.cafe.jackson;

public class Cafe {
    private String name;
    private String city;
    private String owner;
    private int seatingQty = 0;
    private int employeesQty = 0;
    public Cafe(){
    }
    public Cafe(String name, String city, String owner, int seatingQty, int employeesQty){
        this.name = name;
        this.city = city;
        this.owner = owner;
        this.seatingQty = seatingQty;
        this.employeesQty = employeesQty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public int getSeatingQty() {
        return seatingQty;
    }

    public void setSeatingQty(int seatingQty) {
        this.seatingQty = seatingQty;
    }

    public int getEmployeesQty() {
        return employeesQty;
    }

    public void setEmployeesQty(int employeesQty) {
        this.employeesQty = employeesQty;
    }

    @Override
    public String toString() {
        return "Cafe{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", owner='" + owner + '\'' +
                ", seatingQty=" + seatingQty +
                ", employeesQty=" + employeesQty +
                '}';
    }
}
