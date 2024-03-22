package Entitties.Console.data;


import Entities.Backend.EmployeeAddress;

public class EmployeeDetails {
    private String firstName;
    private String middleName;
    private String lastName;
    private Long empMobileNumber=0L;
    private Integer empId;
    private Entities.Backend.EmployeeAddress tempAddress;
    private EmployeeAddress permAddress;


    public EmployeeDetails(String firstName, String middleName, String lastName, Long empMobileNumber, Integer empId, EmployeeAddress tempAddress, EmployeeAddress permAddress) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.empMobileNumber = empMobileNumber;
        this.empId = empId;
        this.tempAddress = tempAddress;
        this.permAddress = permAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getEmpMobileNumber() {
        return empMobileNumber;
    }

    public void setEmpMobileNumber(Long empMobileNumber) {
        this.empMobileNumber = empMobileNumber;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public EmployeeAddress getTempAddress() {
        return tempAddress;
    }

    public void setTempAddress(EmployeeAddress tempAddress) {
        this.tempAddress = tempAddress;
    }

    public EmployeeAddress getPermAddress() {
        return permAddress;
    }

    public void setPermAddress(EmployeeAddress permAddress) {
        this.permAddress = permAddress;
    }

    public EmployeeDetails() {
    }
}

