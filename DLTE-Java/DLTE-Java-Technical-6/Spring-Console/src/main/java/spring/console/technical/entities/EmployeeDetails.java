package spring.console.technical.entities;

import technical.review.spring.entities.backend.EmployeeAddress;

public class EmployeeDetails {
    private String firstName;
    private String middleName;
    private String lastName;
    private Long empMobileNumber=0L;
    private int empId;
   private spring.console.technical.entities.EmployeeAddress tempAddress;
   private spring.console.technical.entities.EmployeeAddress permAddress;
    public EmployeeDetails(String firstName, String middleName, String lastName, Long empMobileNumber, int empId) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.empMobileNumber = empMobileNumber;
        this.empId = empId;
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

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public spring.console.technical.entities.EmployeeAddress getTempAddress() {
        return tempAddress;
    }

    public void setTempAddress(spring.console.technical.entities.EmployeeAddress tempAddress) {
        this.tempAddress = tempAddress;
    }

    public spring.console.technical.entities.EmployeeAddress getPermAddress() {
        return permAddress;
    }

    public void setPermAddress(spring.console.technical.entities.EmployeeAddress permAddress) {
        this.permAddress = permAddress;
    }

    public EmployeeDetails() {
    }

    @Override
    public String toString() {
        return "EmployeeDetails{" +
                "firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", empMobileNumber=" + empMobileNumber +
                ", empId=" + empId +
                ", tempAddress=" + tempAddress +
                ", permAddress=" + permAddress +
                '}';
    }
}
