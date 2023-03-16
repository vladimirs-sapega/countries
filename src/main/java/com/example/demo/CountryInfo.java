package com.example.demo;

import java.util.List;

public class CountryInfo {
    private String commonName;
    private String officialName;
    private String countryCode;
    private String region;
    private List <CountryInfo> borders ;

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<CountryInfo> getBorders() {
        return borders;
    }

    public void setBorders(List<CountryInfo> borders) {
        this.borders = borders;
    }
}
