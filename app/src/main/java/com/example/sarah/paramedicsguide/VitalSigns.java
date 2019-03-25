package com.example.sarah.paramedicsguide;

import java.util.Date;

public class VitalSigns {
    String patient_key;
    String vitalByVoice;
    int pluseRate;
    int bloodPressure;
    int respRate;
    int GCS;
    int bloodGlucose;
    int BloodOxygen;
    int tempreture;
    Date timeOfMeasurement;



    public VitalSigns(String vitalByVoice, String patient_key)
    {
       this.vitalByVoice=vitalByVoice;
       this.patient_key=patient_key;


    }

    public VitalSigns() {
    }

    public VitalSigns(int pluseRate, int bloodPressure, int respRate, int GCS, int bloodGlucose, int BloodOxygen, int tempreture, String patient_key){
        this.pluseRate=pluseRate;
        this.bloodPressure=bloodPressure;
        this.respRate=respRate;
        this.GCS=GCS;
        this.bloodGlucose=bloodGlucose;
        this.BloodOxygen=BloodOxygen;
        this.tempreture=tempreture;
        this.patient_key=patient_key;



    }
    public int pluseRate() {
        return pluseRate;
    }
    public int bloodPressure() {
        return bloodPressure;
    }
    public int respRate() {
        return respRate;
    }
    public int GCS() {
        return GCS;
    }
    public int bloodGlucose() {
        return bloodGlucose;
    }
    public int BloodOxygen() {
        return BloodOxygen;
    }
    public int tempreture() {
        return tempreture;
    }
    public  Date timeOfMeasurement() {
        return timeOfMeasurement;
    }

}
