package org.project2.Entities;

public class EmployeeDetails {
    private String firstName;
    private String middleName;
    private String lastName;
    private Long empMobileNumber=0L;
    private Long empId=0L;
    private EmployeeAddress permAddress;
    private EmployeeAddress tempAddress;

    public EmployeeDetails(String firstName, String middleName, String lastName, Long empMobileNumber, Long empId, EmployeeAddress permAddress, EmployeeAddress tempAddress) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.empMobileNumber = empMobileNumber;
        this.empId = empId;
        this.permAddress = permAddress;
        this.tempAddress = tempAddress;
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

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public EmployeeAddress getPermAddress() {
        return permAddress;
    }

    public void setPermAddress(EmployeeAddress permAddress) {
        this.permAddress = permAddress;
    }

    public EmployeeAddress getTempAddress() {
        return tempAddress;
    }

    public void setTempAddress(EmployeeAddress tempAddress) {
        this.tempAddress = tempAddress;
    }
}
