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

    public EmployeeMain(int count) {
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

}
