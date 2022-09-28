package com.solvd.cafe.models;

import java.util.Objects;

public class Employees {
    private int id;
    private String name;
    private String lastName;
    private String phoneNum;
    private String position;
    private int cafesId;

    public Employees(){

    }
    public Employees(int id, String name, String lastName, String phoneNum, String position, int cafesId) {
        this.id = id;
        this.name = name;
        this.lastName =  lastName;
        this.phoneNum = phoneNum;
        this.position = position;
        this.cafesId = cafesId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getCafesId() {
        return cafesId;
    }

    public void setCafesId(int cafesId) {
        this.cafesId = cafesId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employees employees = (Employees) o;
        return id == employees.id && cafesId == employees.cafesId && name.equals(employees.name) && lastName.equals(employees.lastName) && phoneNum.equals(employees.phoneNum) && position.equals(employees.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, phoneNum, position, cafesId);
    }

    @Override
    public String toString() {
        return "Employees{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", position='" + position + '\'' +
                ", cafesId=" + cafesId +
                '}';
    }
}
