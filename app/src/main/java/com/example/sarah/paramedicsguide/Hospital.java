package com.example.sarah.paramedicsguide;

public class Hospital {String hospitalName;
    String hospitalID;
    String hospitalEmail;
    String hospitalPassword;

    public Hospital() {
    }

    public Hospital(String hospitalName, String hospitalID, String hospitalEmail, String hospitalPassword) {
        this.hospitalName = hospitalName;
        this.hospitalID = hospitalID;
        this.hospitalEmail = hospitalEmail;
        this.hospitalPassword = hospitalPassword;
    }


    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getHospitalID() {
        return hospitalID;
    }

    public void setHospitalID(String hospitalID) {
        this.hospitalID = hospitalID;
    }

    public String getHospitalEmail() {
        return hospitalEmail;
    }

    public void setHospitalEmail(String hospitalEmail) {
        this.hospitalEmail = hospitalEmail;
    }

    public String getHospitalPassword() {
        return hospitalPassword;
    }

    public void setHospitalPassword(String hospitalPassword) {
        this.hospitalPassword = hospitalPassword;
    }
}
