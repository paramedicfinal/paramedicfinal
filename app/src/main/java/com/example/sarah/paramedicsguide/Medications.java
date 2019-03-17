package com.example.sarah.paramedicsguide;

public class Medications {
    String itropen;
    String esprin;
    String gelatinSuger;
    String dicstrose;
    String ntroglesen;
    String edranelin;
    String salbotamol;

    public Medications(String itropen, String esprin, String gelatinSuger, String dicstrose, String ntroglesen, String edranelin, String salbotamol) {
        this.itropen = itropen;
        this.esprin = esprin;
        this.gelatinSuger = gelatinSuger;
        this.dicstrose = dicstrose;
        this.ntroglesen = ntroglesen;
        this.edranelin = edranelin;
        this.salbotamol = salbotamol;
    }

    public String getItropen() {
        return itropen;
    }

    public void setItropen(String itropen) {
        this.itropen = itropen;
    }

    public String getEsprin() {
        return esprin;
    }

    public void setEsprin(String esprin) {
        this.esprin = esprin;
    }

    public String getGelatinSuger() {
        return gelatinSuger;
    }

    public void setGelatinSuger(String gelatinSuger) {
        this.gelatinSuger = gelatinSuger;
    }

    public String getDicstrose() {
        return dicstrose;
    }

    public void setDicstrose(String dicstrose) {
        this.dicstrose = dicstrose;
    }

    public String getNtroglesen() {
        return ntroglesen;
    }

    public void setNtroglesen(String ntroglesen) {
        this.ntroglesen = ntroglesen;
    }

    public String getEdranelin() {
        return edranelin;
    }

    public void setEdranelin(String edranelin) {
        this.edranelin = edranelin;
    }

    public String getSalbotamol() {
        return salbotamol;
    }

    public void setSalbotamol(String salbotamol) {
        this.salbotamol = salbotamol;
    }
}
