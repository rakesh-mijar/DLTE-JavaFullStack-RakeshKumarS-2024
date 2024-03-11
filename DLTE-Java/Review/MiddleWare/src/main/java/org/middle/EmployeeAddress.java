package org.middle;

import java.io.Serializable;

public class EmployeeAddress implements Serializable {

    private String permanentStreet;
    private String permanentState;
    private String permanentCountry;
    private String permanentPincode;
    private String temporaryStreet;
    private String temporaryState;
    private String temporaryCountry;
    private String temporaryPincode;

    public String getPermanentStreet() {
        return permanentStreet;
    }

    public void setPermanentStreet(String permanentStreet) {
        this.permanentStreet = permanentStreet;
    }

    public String getPermanentState() {
        return permanentState;
    }

    public void setPermanentState(String permanentState) {
        this.permanentState = permanentState;
    }

    public String getPermanentCountry() {
        return permanentCountry;
    }

    public void setPermanentCountry(String permanentCountry) {
        this.permanentCountry = permanentCountry;
    }

    public String getPermanentPincode() {
        return permanentPincode;
    }

    public void setPermanentPincode(String permanentPincode) {
        this.permanentPincode = permanentPincode;
    }

    public String getTemporaryStreet() {
        return temporaryStreet;
    }

    public void setTemporaryStreet(String temporaryStreet) {
        this.temporaryStreet = temporaryStreet;
    }

    public String getTemporaryState() {
        return temporaryState;
    }

    public void setTemporaryState(String temporaryState) {
        this.temporaryState = temporaryState;
    }

    public String getTemporaryCountry() {
        return temporaryCountry;
    }

    public void setTemporaryCountry(String temporaryCountry) {
        this.temporaryCountry = temporaryCountry;
    }

    public String getTemporaryPincode() {
        return temporaryPincode;
    }

    public void setTemporaryPincode(String temporaryPincode) {
        this.temporaryPincode = temporaryPincode;
    }

}
