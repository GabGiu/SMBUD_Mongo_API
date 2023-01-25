package it.polimi.gabrielegiusti.classes.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Subsection implements GenericModel{

    @JsonProperty
    private int subsecNumber;

    @JsonProperty
    private String subsecTitle;

    @JsonProperty
    private String subsecText;

    @JsonProperty
    private List<Paragraph> paragraphs;

    public Subsection(){}

    public Subsection(int subsecNumber, String subsecTitle, String subsecText, List<Paragraph> paragraphs) {
        this.subsecNumber = subsecNumber;
        this.subsecTitle = subsecTitle;
        this.subsecText = subsecText;
        this.paragraphs = paragraphs;
    }

    public int getSubsecNumber() {
        return subsecNumber;
    }

    public void setSubsecNumber(int subsecNumber) {
        this.subsecNumber = subsecNumber;
    }

    public String getSubsecTitle() {
        return subsecTitle;
    }

    public void setSubsecTitle(String subsecTitle) {
        this.subsecTitle = subsecTitle;
    }

    public String getSubsecText() {
        return subsecText;
    }

    public void setSubsecText(String subsecText) {
        this.subsecText = subsecText;
    }

    public List<Paragraph> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(List<Paragraph> paragraphs) {
        this.paragraphs = paragraphs;
    }

    @Override
    public Map<String, Object> toHashMap() {
        Map<String, Object> dataMap = new HashMap<>();
        List<Document> tmpParagraphs = new ArrayList<>();

        for (Paragraph paragraph : this.paragraphs){
            tmpParagraphs.add(paragraph.toDocument());
        }

        dataMap.put("subsection_number", this.subsecNumber);
        dataMap.put("subsection_title", this.subsecTitle);
        dataMap.put("subsection_text", this.subsecText);
        dataMap.put("paragraphs", tmpParagraphs);

        return dataMap;
    }

    @Override
    public Document toDocument() {
        return new Document(toHashMap());
    }
}
