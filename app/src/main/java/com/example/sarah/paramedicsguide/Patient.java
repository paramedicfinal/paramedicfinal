package com.example.sarah.paramedicsguide;

public class Patient {


    String nationalId;
    String name;
    String bedType;
    String sex;
    String medicalState;


    public Patient(String nationalId, String name, String bedType, String sex, String medicalState) {
        this.nationalId = nationalId;
        this.name = name;
        this.bedType = bedType;
        this.sex = sex;
        this.medicalState = medicalState;
    }

    public String getNationalId() {
        return nationalId;
    }

    public String getName() {
        return name;
    }


    public String getSex() {
        return sex;
    }

    public String getMedicalState() {
        return medicalState;
    }



    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setMedicalState(String medicalState) {
        this.medicalState = medicalState;
    }

    public String getBedType() {
        return bedType;
    }

    public void setBedType(String bedType) {
        this.bedType = bedType;
    }
}
