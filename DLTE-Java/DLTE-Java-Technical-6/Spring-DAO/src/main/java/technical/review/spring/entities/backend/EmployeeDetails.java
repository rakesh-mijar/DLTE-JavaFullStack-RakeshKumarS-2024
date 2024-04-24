package technical.review.spring.entities.backend;


public class EmployeeDetails {
    private String firstName;
    private String middleName;
    private String lastName;
    private Long empMobileNumber=0L;
    private int empId;
    private EmployeeAddress tempAddress;
    private EmployeeAddress permAddress;

    public EmployeeDetails(String firstName, String middleName, String lastName, Long empMobileNumber, int empId) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.empMobileNumber = empMobileNumber;
        this.empId = empId;
    }

    public EmployeeDetails(String firstName, String middleName, String lastName, Long empMobileNumber, int empId, EmployeeAddress tempAddress, EmployeeAddress permAddress) {
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

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
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
