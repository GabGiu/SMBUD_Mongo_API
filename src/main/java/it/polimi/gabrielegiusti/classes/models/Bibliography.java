package it.polimi.gabrielegiusti.classes.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bibliography implements GenericModel{

    @JsonProperty
    private List<Reference> references;

    public Bibliography(){}

    public Bibliography(List<Reference> references) {
        this.references = references;
    }

    public List<Reference> getReferences() {
        return references;
    }

    public void setReferences(List<Reference> references) {
        this.references = references;
    }

    @Override
    public Map<String, Object> toHashMap() {
        Map<String, Object> dataMap = new HashMap<>();

        List<Document> tmpRef = new ArrayList<>();

        if (this.references != null) {
            for (Reference reference : this.references) {
                tmpRef.add(reference.toDocument());
            }
            dataMap.put("references", tmpRef);
        } else {
            dataMap.put("references", null);
        }



        return dataMap;
    }

    @Override
    public Document toDocument() {
        return new Document(toHashMap());
    }
}
