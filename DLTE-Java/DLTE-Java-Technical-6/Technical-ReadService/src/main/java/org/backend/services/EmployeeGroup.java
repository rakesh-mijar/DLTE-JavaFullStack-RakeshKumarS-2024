package org.backend.services;

import Entities.Backend.EmployeeDetails;

import java.util.List;

public class EmployeeGroup {
    List<EmployeeDetails> employeeDetailsList;

    public EmployeeGroup() {
    }

    public EmployeeGroup(List<EmployeeDetails> employeeDetailsList) {
        this.employeeDetailsList = employeeDetailsList;
    }

    public List<EmployeeDetails> getEmployeeDetailsList() {
        return employeeDetailsList;
    }

    public void setEmployeeDetailsList(List<EmployeeDetails> employeeDetailsList) {
        this.employeeDetailsList = employeeDetailsList;
    }

    @Override
    public String toString() {
        return "EmployeeGroup{" +
                "employeeDetailsList=" + employeeDetailsList +
                '}';
    }
}
