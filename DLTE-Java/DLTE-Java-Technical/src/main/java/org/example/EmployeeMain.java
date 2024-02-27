package org.example;

public class EmployeeMain
{
    private String firstName;
    private String middleName;
    private String lastName;
    private Long empMobileNumber=0L;
    private Long empId=0L;

    public EmployeeMain(String firstName, String middleName, String lastName, Long empMobileNumber, Long empId) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.empMobileNumber = empMobileNumber;
        this.empId = empId;
    }
    @Override
    public String toString() {
        return "EmployeeMain{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", empMobileNumber=" + empMobileNumber +
                ", empId=" + empId +
                '}';
    }
}
class EmployeeAddress{
    private String permStreet;
    private String permState;
    private String permCountry;
    private String permPincode;

    private String tempStreet;
    private String tempState;
    private String tempCountry;
    private String tempPincode;

    public String getPermStreet() {
        return permStreet;
    }

    public void setPermStreet(String permStreet) {
        this.permStreet = permStreet;
    }

    public String getPermState() {
        return permState;
    }

    public void setPermState(String permState) {
        this.permState = permState;
    }

    public String getPermCountry() {
        return permCountry;
    }

    public void setPermCountry(String permCountry) {
        this.permCountry = permCountry;
    }

    public String getPermPincode() {
        return permPincode;
    }

    public void setPermPincode(String permPincode) {
        this.permPincode = permPincode;
    }

    public String getTempStreet() {
        return tempStreet;
    }

    public void setTempStreet(String tempStreet) {
        this.tempStreet = tempStreet;
    }

    public String getTempState() {
        return tempState;
    }

    public void setTempState(String tempState) {
        this.tempState = tempState;
    }

    public String getTempCountry() {
        return tempCountry;
    }

    public void setTempCountry(String tempCountry) {
        this.tempCountry = tempCountry;
    }

    public String getTempPincode() {
        return tempPincode;
    }

    public void setTempPincode(String tempPincode) {
        this.tempPincode = tempPincode;
    }
}
