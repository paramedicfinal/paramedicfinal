package com.example.sarah.paramedicsguide;

import java.util.Date;

public class VitalSigns {
    int pluseRate;
    int bloodPressure;
    int respRate;
    int GCS;
    int bloodGlucose;
    int BloodOxygen;
    int tempreture;
    Date timeOfMeasurement;



    public VitalSigns(String text){ }

    public VitalSigns(int pluseRate, int bloodPressure, int respRate, int GCS, int bloodGlucose, int BloodOxygen, int tempreture
                    ){
        this.pluseRate=pluseRate;
        this.bloodPressure=bloodPressure;
        this.respRate=respRate;
        this.GCS=GCS;
        this.bloodGlucose=bloodGlucose;
        this.BloodOxygen=BloodOxygen;
        this.tempreture=tempreture;



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
