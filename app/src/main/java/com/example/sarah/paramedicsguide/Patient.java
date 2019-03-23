package com.example.sarah.paramedicsguide;

public class Patient {


    String nationalId;
    String name;
    String bedType;
    String sex;
    String medicalState;
    String key ;
    VitalSigns vitalSigns;


    public Patient( ) {
    }

    public Patient(String nationalId, String name, String sex, String medicalState, String bedType, VitalSigns vitalSigns ) {
        this.nationalId = nationalId;
        this.name = name;
        this.bedType = bedType;
        this.sex = sex;
        this.medicalState = medicalState;
        this.vitalSigns=vitalSigns;
    }

    public Patient(String nationalId, String name, String bedType, String sex, String medicalState,String key) {
        this.nationalId = nationalId;
        this.name = name;
        this.bedType = bedType;
        this.sex = sex;
        this.medicalState = medicalState;
        this.key=key;
    }

    public String getKey() {
        return key;
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

    public VitalSigns getVitalSigns() {
        return vitalSigns;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVitalSigns(VitalSigns vitalSigns) {
        this.vitalSigns = vitalSigns;
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

    public void setKey(String key) {
        this.key = key;
    }
}
