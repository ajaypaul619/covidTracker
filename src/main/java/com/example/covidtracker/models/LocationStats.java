package com.example.covidtracker.models;

public class LocationStats
{
    private String province_state;
    private int latest_total;
    private int differenceInCases;

    public int getDifferenceInCases() {
        return differenceInCases;
    }

    public void setDifferenceInCases(int differenceInCases) {
        this.differenceInCases = differenceInCases;
    }

    public String getProvince_state() {
        return province_state;
    }

    public void setProvince_state(String province_state) {
        this.province_state = province_state;
    }

    public int getLatest_total() {
        return latest_total;
    }

    public void setLatest_total(int latest_total) {
        this.latest_total = latest_total;
    }

    @Override
    public String toString() {
        return "LocationStats{" +
                "province_state='" + province_state + '\'' +
                ", latest_total=" + latest_total +
                '}';
    }
}

