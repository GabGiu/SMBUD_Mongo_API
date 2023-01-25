package it.polimi.gabrielegiusti.classes.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

public class Location implements GenericModel{

    @JsonProperty
    private int zipcode;

    @JsonProperty
    private String city;

    @JsonProperty
    private String country;

    public Location(){};

    public Location(int zipcode, String city, String country) {
        this.zipcode = zipcode;
        this.city = city;
        this.country = country;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public Map<String, Object> toHashMap() {
        Map<String, Object> dataMap = new HashMap<>();

        dataMap.put("zipcode", this.zipcode);
        dataMap.put("city", this.city);
        dataMap.put("country", this.country);

        return dataMap;
    }

    @Override
    public Document toDocument() {
        return new Document(toHashMap());
    }
}
