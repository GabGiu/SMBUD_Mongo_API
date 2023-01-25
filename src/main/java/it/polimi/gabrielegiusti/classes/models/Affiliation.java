package it.polimi.gabrielegiusti.classes.models;

import com.ctc.wstx.shaded.msv_core.datatype.xsd.datetime.IDateTimeValueType;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

public class Affiliation implements GenericModel{

    @JsonProperty
    private String affiliationName;

    @JsonProperty
    private String affiliationDepartment;

    @JsonProperty
    private Location location;

    public Affiliation(){}

    public Affiliation(String affiliationName, String affiliationDepartment, Location location) {
        this.affiliationName = affiliationName;
        this.affiliationDepartment = affiliationDepartment;
        this.location = location;
    }

    public String getAffiliationName() {
        return affiliationName;
    }

    public void setAffiliationName(String affiliationName) {
        this.affiliationName = affiliationName;
    }

    public String getAffiliationDepartment() {
        return affiliationDepartment;
    }

    public void setAffiliationDepartment(String affiliationDepartment) {
        this.affiliationDepartment = affiliationDepartment;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    @Override
    public Map<String, Object> toHashMap() {
        Map<String, Object> dataMap = new HashMap<>();

        dataMap.put("affiliation_name", this.affiliationName);
        dataMap.put("department", this.affiliationDepartment);
        dataMap.put("location", this.location.toDocument());

        return dataMap;
    }

    @Override
    public Document toDocument() {
        return new Document(toHashMap());
    }
}
