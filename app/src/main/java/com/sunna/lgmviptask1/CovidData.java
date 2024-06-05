package com.sunna.lgmviptask1;

public class CovidData {
    private String districtName,
            confirmed, deceased, recovered, active;


    public CovidData(String name, String active,
                     String confirmed, String deceased, String recovered) {
        this.districtName = name;
        this.confirmed = confirmed;
        this.active = active;
        this.deceased = deceased;
        this.recovered = recovered;
    }


    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;

    }

    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;

    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;

    }

    public String getDeceased() {
        return deceased;

    }

    public void setDeceased(String deceased) {
        this.deceased = deceased;

    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }
}
