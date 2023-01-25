package it.polimi.gabrielegiusti.classes.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.Document;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScientificArticleModel implements GenericModel{

    @JsonProperty
    private String title;

    @JsonProperty("abstract")
    private String article_abstract;

    @JsonProperty
    private int year;

    @JsonProperty
    private List<String> metadata;

    @JsonProperty
    private String type;

    @JsonProperty
    private String DOI;

    @JsonProperty
    private List<Author> authors;

    @JsonProperty
    private PublicationDetails publicationDetails;

    @JsonProperty
    private List<Section> sections;

    @JsonProperty
    private List<Reference> bibliography;

    @JsonProperty
    private List<Figure> figures;

    public ScientificArticleModel(){}

    public ScientificArticleModel(String title, String article_abstract, int year, List<String> metadata, String type,
                                  String DOI, List<Author> authors, PublicationDetails publicationDetails,
                                  List<Section> sections, List<Reference> bibliography) {
        this.title = title;
        this.article_abstract = article_abstract;
        this.year = year;
        this.metadata = metadata;
        this.type = type;
        this.DOI = DOI;
        this.authors = authors;
        this.publicationDetails = publicationDetails;
        this.sections = sections;
        this.bibliography = bibliography;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticle_abstract() {
        return article_abstract;
    }

    public void setArticle_abstract(String article_abstract) {
        this.article_abstract = article_abstract;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDOI() {
        return DOI;
    }

    public void setDOI(String DOI) {
        this.DOI = DOI;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public PublicationDetails getPublicationDetails() {
        return publicationDetails;
    }

    public void setPublicationDetails(PublicationDetails publicationDetails) {
        this.publicationDetails = publicationDetails;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public List<String> getMetadata() {
        return metadata;
    }

    public void setMetadata(List<String> metadata) {
        this.metadata = metadata;
    }

    public List<Reference> getBibliography() {
        return bibliography;
    }

    public void setBibliography(List<Reference> bibliography) {
        this.bibliography = bibliography;
    }

    public List<Figure> getFigures() {
        return figures;
    }

    public void setFigures(List<Figure> figures) {
        this.figures = figures;
    }

    @Override
    public String toString() {
        return "ScientificArticleModel{" +
                "title='" + title + '\'' +
                ", article_abstract='" + article_abstract + '\'' +
                ", year=" + year +
                ", metadata=" + metadata +
                ", type='" + type + '\'' +
                ", DOI='" + DOI + '\'' +
                ", authors=" + authors +
                ", publicationDetails=" + publicationDetails +
                ", sections=" + sections +
                ", bibliography=" + bibliography +
                ", figures=" + figures +
                '}';
    }

    @Override
    public Map<String, Object> toHashMap() {
        Map<String, Object> dataMap = new HashMap<>();
        List<Document> tmpAuthors = new ArrayList<>();
        List<Document> tmpSections = new ArrayList<>();
        List<Document> tmpFigures = new ArrayList<>();
        List<Document> tmpReference = new ArrayList<>();

        for (Author author: this.authors){
            tmpAuthors.add(author.toDocument());
        }

        for (Section section: this.sections){
            tmpSections.add(section.toDocument());
        }

        for (Figure figure: this.figures){
            tmpFigures.add(figure.toDocument());
        }

        for (Reference reference: this.bibliography){
            tmpReference.add(reference.toDocument());
        }

        dataMap.put("title", this.title);
        dataMap.put("abstract", this.article_abstract);
        dataMap.put("metadata", this.metadata);
        dataMap.put("year", this.year);
        dataMap.put("type", this.type);
        dataMap.put("DOI", this.DOI);
        dataMap.put("authors", tmpAuthors);
        dataMap.put("publication_details", this.publicationDetails.toDocument());
        dataMap.put("sections", tmpSections);
        dataMap.put("bibliography", tmpReference);
        dataMap.put("figures", tmpFigures);

        return dataMap;
    }

    @Override
    public Document toDocument() {
        return new Document(toHashMap());
    }
}
