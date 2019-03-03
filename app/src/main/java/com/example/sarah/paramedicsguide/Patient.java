package com.example.sarah.paramedicsguide;

public class Patient {


    String nationalId;
    String Name;
    String bedType;
    String sex;
    String medicalState;

    public Patient() {

    }

    public Patient(String nationalId, String name, String sex, String medicalState, String bedType) {
        this.nationalId = nationalId;
        Name = name;
        this.sex = sex;
        this.medicalState = medicalState;
        this.bedType=bedType;
    }







    public String getNationalId() {
        return nationalId;
    }

    public String getName() {
        return Name;
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

    public void setName(String Name) {
        this.Name = Name;
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
