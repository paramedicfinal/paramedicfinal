package com.example.sarah.paramedicsguide;

public class NewCase {

/////////////////////////////////////////////////////////
   private Patient patient;
   private String name_paramedic, id_paramedic, name_hospital;
   boolean send=false, recive=false, done=false;
//////////////////////////////////////////////////////
    public NewCase() {
    }
//////////////////////////////////////////////////////
    public Patient getPatient() {
        return patient;
    }

    public String getName_paramedic() {
        return name_paramedic;
    }

    public String getId_paramedic() {
        return id_paramedic;
    }

    public String getName_hospital() {
        return name_hospital;
    }
 //////////////////////////////////////////////////////
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setName_paramedic(String name_paramedic) {
        this.name_paramedic = name_paramedic;
    }

    public void setId_paramedic(String id_paramedic) {
        this.id_paramedic = id_paramedic;
    }

    public void setName_hospital(String name_hospital) {
        this.name_hospital = name_hospital;
    }
   ///////////////////////////////////////////////////
}
