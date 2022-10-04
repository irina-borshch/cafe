package com.solvd.cafe.builder;

public class Cafe {
    private String name;
    private String city;
    private String owner;
    private int seatingQty = 0;
    private int employeesQty = 0;

    public Cafe(CafeBuilder builder) {
        this.name = builder.name;
        this.city = builder.city;
        this.owner = builder.owner;
        this.seatingQty = builder.seatingQty;
        this.employeesQty = builder.employeesQty;
    }

    public Cafe() {
    }

    public Cafe(String name, String city, String owner, int seatingQty, int employeesQty) {
        this.name = name;
        this.city = city;
        this.owner = owner;
        this.seatingQty = seatingQty;
        this.employeesQty = employeesQty;
    }

    public String getName() {
        return name;
    }
    public String getCity() {
        return city;
    }
    public String getOwner() {
        return owner;
    }
    public int getSeatingQty() {
        return seatingQty;
    }
    public int getEmployeesQty() {
        return employeesQty;
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

    public static class CafeBuilder {
        private String name; // required
        private String city;// required
        private String owner;// required
        private int seatingQty; // optional
        private int employeesQty;// optional

        public CafeBuilder(String name, String city, String owner) {
            this.name = name;
            this.city = city;
            this.owner = owner;
        }

        public CafeBuilder seatingQty(int seatingQty) {
            this.seatingQty = seatingQty;
            return this;
        }

        public CafeBuilder employeesQty(int employeesQty) {
            this.employeesQty = employeesQty;
            return this;
        }

        public Cafe build() {
            Cafe cafe = new Cafe(this);
            return cafe;

        }
    }
}
