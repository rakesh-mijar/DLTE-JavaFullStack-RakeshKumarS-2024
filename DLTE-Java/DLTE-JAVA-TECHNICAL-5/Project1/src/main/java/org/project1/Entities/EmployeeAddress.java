package org.project1.Entities;

public class EmployeeAddress {
    private String Street;
    private String State;
    private String Country;
    private int Pincode;

    public EmployeeAddress(String street, String state, String country, int pincode) {
        Street = street;
        State = state;
        Country = country;
        Pincode = pincode;
    }

    public EmployeeAddress() {
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public int getPincode() {
        return Pincode;
    }

    public void setPincode(int pincode) {
        Pincode = pincode;
    }
}
