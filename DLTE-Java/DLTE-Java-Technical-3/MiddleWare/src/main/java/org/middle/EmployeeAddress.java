package org.middle;

import java.io.Serializable;

public class EmployeeAddress implements Serializable {

    private String Street;
    private String State;
    private String Country;
    private String Pincode;

    public EmployeeAddress(String street, String state, String country, String pincode) {
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

    public String getPincode() {
        return Pincode;
    }

    public void setPincode(String pincode) {
        Pincode = pincode;
    }

    @Override
    public String toString() {
        return "EmployeeAddress{" +
                "Street='" + Street + '\'' +
                ", State='" + State + '\'' +
                ", Country='" + Country + '\'' +
                ", Pincode='" + Pincode + '\'' +
                '}';
    }
}
