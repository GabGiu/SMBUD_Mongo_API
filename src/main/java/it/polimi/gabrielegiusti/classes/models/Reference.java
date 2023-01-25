package it.polimi.gabrielegiusti.classes.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reference implements GenericModel{

    @JsonProperty
    private List<Object> author;

    @JsonProperty
    private String title;

    @JsonProperty
    private int year;

    @JsonProperty
    private String DOI;

    public Reference(){}

    public Reference(List<Object> author, String title, int year, String DOI) {
        this.author = author;
        this.title = title;
        this.year = year;
        this.DOI = DOI;
    }

    public List<Object> getAuthor() {
        return author;
    }

    public void setAuthor(List<Object> author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDOI() {
        return DOI;
    }

    public void setDOI(String DOI) {
        this.DOI = DOI;
    }

    @Override
    public Map<String, Object> toHashMap() {
        Map<String, Object> dataMap = new HashMap<>();

        List<Document> tmpAuthor = new ArrayList<>();

        for (Object item: this.author){
            tmpAuthor.add(new Document((HashMap<String, Object>) item));
        }

        dataMap.put("author", tmpAuthor);
        dataMap.put("title", this.title);
        dataMap.put("year", this.year);
        dataMap.put("ref_DOI", this.DOI);

        return dataMap;
    }

    @Override
    public Document toDocument() {
        return new Document(toHashMap());
    }
}
