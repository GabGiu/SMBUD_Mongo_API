package it.polimi.gabrielegiusti.classes.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.Document;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Section implements GenericModel{

    @JsonProperty
    private int sectionNumber;

    @JsonProperty
    private String sectionTitle;

    @JsonProperty
    private String sectionText;

    @JsonProperty
    private List<Subsection> subsections;

    public Section(){}

    public Section(String sectionTitle, List<Subsection> subsection, Map<String, Image> figures,
                   int sectionNumber, String sectionText) {
        this.sectionTitle = sectionTitle;
        this.subsections = subsection;
        this.sectionNumber = sectionNumber;
        this.sectionText = sectionText;
    }

    public String getSectionTitle() {
        return sectionTitle;
    }

    public void setSectionTitle(String sectionTitle) {
        this.sectionTitle = sectionTitle;
    }

    public List<Subsection> getSubsection() {
        return subsections;
    }

    public void setSubsection(List<Subsection> subsection) {
        this.subsections = subsection;
    }

    public int getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(int sectionNumber) {
        this.sectionNumber = sectionNumber;
    }

    @Override
    public Map<String, Object> toHashMap() {
        Map<String, Object> dataMap = new HashMap<>();
        List<Document> tmpSubsections = new ArrayList<>();

        for (Subsection subsection: this.subsections){
            tmpSubsections.add(subsection.toDocument());
        }

        dataMap.put("section_number", this.sectionNumber);
        dataMap.put("section_title", this.sectionTitle);
        dataMap.put("section_text", this.sectionText);
        dataMap.put("subsections", tmpSubsections);
        return dataMap;
    }

    @Override
    public Document toDocument() {
        return new Document(toHashMap());
    }


    public String getSectionText() {
        return sectionText;
    }

    public void setSectionText(String sectionText) {
        this.sectionText = sectionText;
    }
}
