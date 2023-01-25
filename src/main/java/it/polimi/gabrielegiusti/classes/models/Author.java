package it.polimi.gabrielegiusti.classes.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.Document;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Author implements GenericModel{

    @JsonProperty
    private Affiliation affiliation;

    @JsonProperty
    private String email;

    @JsonProperty
    private String bio;

    @JsonProperty
    private String name;

    @JsonProperty
    private String surname;

    @JsonProperty
    private Date dateOfBirth;

    public Author(){}

    public Author(Affiliation affiliation, String email, String bio,
                  String name, String surname, Date dateOfBirth) {
        this.affiliation = affiliation;
        this.email = email;
        this.bio = bio;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }

    public Affiliation getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(Affiliation affiliation) {
        this.affiliation = affiliation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public Map<String, Object> toHashMap() {
        Map<String, Object> dataMap = new HashMap<>();

        dataMap.put("name", this.name);
        dataMap.put("surname", this.surname);
        dataMap.put("email", this.email);
        dataMap.put("bio", this.bio);
        dataMap.put("date_of_birth", this.dateOfBirth);
        dataMap.put("affiliation", this.affiliation.toDocument());

        return dataMap;
    }

    @Override
    public Document toDocument() {
        return new Document(toHashMap());
    }
}
