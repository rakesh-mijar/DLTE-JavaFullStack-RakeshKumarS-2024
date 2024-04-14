package spring.console.technical.entities;

public class EmployeeAddress {
    private String streetName;
    private String stateName;
    private String countryName;
    private int pincode;
    private int empId;
    private String flag;

    public EmployeeAddress() {
    }

    public EmployeeAddress(String streetName, String stateName, String countryName, int pincode) {
        this.streetName = streetName;
        this.stateName = stateName;
        this.countryName = countryName;
        this.pincode = pincode;
    }

    public EmployeeAddress(String streetName, String stateName, String countryName, int pincode, int empId, String flag) {
        this.streetName = streetName;
        this.stateName = stateName;
        this.countryName = countryName;
        this.pincode = pincode;
        this.empId = empId;
        this.flag = flag;
    }

    public EmployeeAddress(String street, String state, String country, int tempPincode, int empId) {
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getPincode() {
        return pincode;
    }

    public void setPincode(int pincode) {
        this.pincode = pincode;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "EmployeeAddress{" +
                "streetName='" + streetName + '\'' +
                ", stateName='" + stateName + '\'' +
                ", countryName='" + countryName + '\'' +
                ", pincode=" + pincode +
                ", empId=" + empId +
                ", flag='" + flag + '\'' +
                '}';
    }
}

