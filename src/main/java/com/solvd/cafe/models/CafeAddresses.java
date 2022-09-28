package com.solvd.cafe.models;

import java.util.Objects;

public class CafeAddresses {
    private int id;
    private String streetName;
    private int buildingNum;
    private String city;
    private int franchisesId;
    private String country;

    public CafeAddresses() {

    }
    public CafeAddresses(int id, String streetName, int buildingNum, String city, int franchisesId, String country){
        this.id = id;
        this.streetName = streetName;
        this.buildingNum = buildingNum;
        this.city = city;
        this.franchisesId = franchisesId;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getBuildingNum() {
        return buildingNum;
    }

    public void setBuildingNum(int buildingNum) {
        this.buildingNum = buildingNum;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getFranchisesId() {
        return franchisesId;
    }

    public void setFranchisesId(int franchisesId) {
        this.franchisesId = franchisesId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CafeAddresses that = (CafeAddresses) o;
        return id == that.id && buildingNum == that.buildingNum && franchisesId == that.franchisesId
                && streetName.equals(that.streetName) && city.equals(that.city) && country.equals(that.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, streetName, buildingNum, city, franchisesId, country);
    }

    @Override
    public String toString() {
        return "CafeAddresses{" +
                "id=" + id +
                ", streetName='" + streetName + '\'' +
                ", buildingNum=" + buildingNum +
                ", city='" + city + '\'' +
                ", franchisesId=" + franchisesId +
                ", country='" + country + '\'' +
                '}';
    }
}
