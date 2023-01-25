package it.polimi.gabrielegiusti.classes.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.Document;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PublicationDetails implements GenericModel{

    @JsonProperty
    private String journalName;

    @JsonProperty
    private String volume;

    @JsonProperty
    private int number;

    @JsonProperty
    private Date date;

    @JsonProperty
    private String pages;

    @JsonProperty
    private String editor;

    @JsonProperty
    private int numberOfCopiesSold;

    public PublicationDetails(){}

    public PublicationDetails(String journalName, String volume,
                              int number, Date date, String pages,
                              String editor, int numberOfCopiesSold) {
        this.journalName = journalName;
        this.volume = volume;
        this.number = number;
        this.date = date;
        this.pages = pages;
        this.editor = editor;
        this.numberOfCopiesSold = numberOfCopiesSold;
    }

    public String getJournalName() {
        return journalName;
    }

    public void setJournalName(String journalName) {
        this.journalName = journalName;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public int getNumberOfCopiesSold() {
        return numberOfCopiesSold;
    }

    public void setNumberOfCopiesSold(int numberOfCopiesSold) {
        this.numberOfCopiesSold = numberOfCopiesSold;
    }

    @Override
    public Map<String, Object> toHashMap() {
        Map<String, Object> dataMap = new HashMap<>();

        dataMap.put("journal_name", this.journalName);
        dataMap.put("volume", this.volume);
        dataMap.put("number", this.number);
        dataMap.put("date", this.date);
        dataMap.put("pages", this.pages);
        dataMap.put("editor", this.editor);
        dataMap.put("number_of_copies_sold", this.numberOfCopiesSold);

        return dataMap;
    }

    @Override
    public Document toDocument() {
        return new Document(toHashMap());
    }

}
