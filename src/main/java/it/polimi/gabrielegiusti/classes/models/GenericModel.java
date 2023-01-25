package it.polimi.gabrielegiusti.classes.models;

import org.bson.Document;

import java.util.Map;

public interface GenericModel {

    public Map<String, Object> toHashMap();

    public Document toDocument();
}
