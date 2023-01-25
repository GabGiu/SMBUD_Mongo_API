package it.polimi.gabrielegiusti.classes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

public class Figure implements GenericModel{

    @JsonProperty("figure_number")
    private int figureNumber;

    @JsonProperty("image_URL")
    private String imageURL;

    @JsonProperty
    private String caption;

    @JsonProperty
    private Map<String, String> type;

    @JsonProperty
    private String ref_text;

    public Figure(){}

    public Figure(int figureNumber, String imageURL, String caption) {
        this.figureNumber = figureNumber;
        this.imageURL = imageURL;
        this.caption = caption;
    }

    public int getFigureNumber() {
        return figureNumber;
    }

    public void setFigureNumber(int figureNumber) {
        this.figureNumber = figureNumber;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Map<String, String> getType() {
        return type;
    }

    public void setType(Map<String, String> type) {
        this.type = type;
    }

    public String getRef_text() {
        return ref_text;
    }

    public void setRef_text(String ref_text) {
        this.ref_text = ref_text;
    }

    @Override
    public Map<String, Object> toHashMap() {
        Map<String, Object> dataMap = new HashMap<>();

        dataMap.put("figure_number", this.figureNumber);
        dataMap.put("image_URL", this.imageURL);
        dataMap.put("caption", this.caption);
        dataMap.put("info", this.type);

        return dataMap;
    }

    @Override
    public Document toDocument() {
        return new Document(toHashMap());
    }
}
