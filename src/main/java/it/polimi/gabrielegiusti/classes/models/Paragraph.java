package it.polimi.gabrielegiusti.classes.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

public class Paragraph implements GenericModel{

    @JsonProperty
    private String paragraphTitle;

    @JsonProperty
    private String paragraphText;

    public Paragraph(){}

    public Paragraph(String paragraphTitle, String paragraphText) {
        this.paragraphTitle = paragraphTitle;
        this.paragraphText = paragraphText;
    }

    public String getParagraphTitle() {
        return paragraphTitle;
    }

    public void setParagraphTitle(String paragraphTitle) {
        this.paragraphTitle = paragraphTitle;
    }

    public String getParagraphText() {
        return paragraphText;
    }

    public void setParagraphText(String paragraphText) {
        this.paragraphText = paragraphText;
    }

    @Override
    public Map<String, Object> toHashMap() {
        Map<String, Object> dataMap = new HashMap<>();

        dataMap.put("paragraph_title", this.paragraphTitle);
        dataMap.put("paragraph_text", this.paragraphText);

        return dataMap;
    }

    @Override
    public Document toDocument() {
        return new Document(toHashMap());
    }
}
