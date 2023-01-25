package it.polimi.gabrielegiusti.engines;


import it.polimi.gabrielegiusti.DBManager.MongoDBManager;
import it.polimi.gabrielegiusti.classes.models.*;
import it.polimi.gabrielegiusti.utils.CollectionNames;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class articlesEngine {

    static protected Logger log = LogManager.getLogger(articlesEngine.class);

    private String operation_ID;

    public articlesEngine(){}

    public articlesEngine(String operation_ID){
        this.operation_ID = operation_ID;
    }

    public boolean createArticles(ScientificArticleModel scientificArticleModel){

        log.info(" - " + operation_ID + " - **** articlesEngine.class|createArticles|Start");

        connectIfDisconnected();

        Document articleDocument = scientificArticleModel.toDocument();

        if (MongoDBManager.getInstance().getOneDocumentFromCollection(articleDocument, CollectionNames.Articles.toString()) == null){
            MongoDBManager.getInstance().addOneDocumentToCollection(articleDocument, CollectionNames.Articles.toString());
            log.info(" - " + operation_ID + " - **** newsEngine.class|createNews|Stop");
            return true;
        }

        System.out.println("Il documento è già presente all'interno del database");

        log.info(" - " + operation_ID + " - **** articlesEngine.class|createArticles|Stop");

        return false;
    }

    public boolean createMultipleArticles(List<ScientificArticleModel> scientificArticleModels){

        log.info(" - " + operation_ID + " - **** articlesEngine.class|createMultipleArticles|Start");

        if (scientificArticleModels == null) return false;

        connectIfDisconnected();

        List<Document> documents = new ArrayList<>();

        Document articleDocument;
        for (ScientificArticleModel scientificArticleModel : scientificArticleModels) {
            articleDocument = scientificArticleModel.toDocument();
            if (MongoDBManager.getInstance().getOneDocumentFromCollection(articleDocument,
                    CollectionNames.Articles.toString()) == null) {
                documents.add(articleDocument);
            }
        }

        MongoDBManager.getInstance().addManyDocumentsToCollection(documents, CollectionNames.Articles.toString());

        return true;
    }

    public ScientificArticleModel readArticles(String id){
        log.info(" - " + operation_ID + " - **** articlesEngine.class|readArticles|Start");

        connectIfDisconnected();

        Document result;

        ScientificArticleModel __result = new ScientificArticleModel();

        if (MongoDBManager.getInstance().getOneDocumentFromCollectionWithID(id, CollectionNames.Articles.toString()) != null){

            result = MongoDBManager.getInstance().getOneDocumentFromCollectionWithID(id, CollectionNames.Articles.toString());

            __result.setTitle(result.getString("title"));
            __result.setDOI(result.getString("DOI"));
            __result.setArticle_abstract(result.getString("abstract"));
            __result.setType(result.getString("type"));
            __result.setYear(result.getInteger("year"));
            __result.setAuthors(result.getList("authors", Author.class));
            __result.setPublicationDetails(result.get("publicationDetails", PublicationDetails.class));
            __result.setSections(result.getList("sections", Section.class));
            __result.setBibliography(result.get("bibliography", List.class));
            __result.setFigures(result.get("figures", List.class));

            log.info(" - " + operation_ID + " - **** newsEngine.class|readArticles|Stop");

            return __result;
        }

        log.info(" - " + operation_ID + " - **** newsEngine.class|readArticles|Stop");

        return null;
    }

    public boolean updateArticles(String id, ScientificArticleModel scientificArticleModel){
        log.info(" - " + operation_ID + " - **** newsEngine.class|updateArticles|Start");

        connectIfDisconnected();

        Document newsDocument = scientificArticleModel.toDocument();

        if (MongoDBManager.getInstance().getOneDocumentFromCollectionWithID(id, CollectionNames.Articles.toString()) != null) {
            MongoDBManager.getInstance().updateOneDocumentInACollectionWithID(id, newsDocument, CollectionNames.Articles.toString());
            log.info(" - " + operation_ID + " - **** newsEngine.class|updateArticles|Stop");
            return true;
        }

        System.out.println("Non è possibile eseguire l'update perché l'elemento non è presente all'interno del database");

        log.info(" - " + operation_ID + " - **** newsEngine.class|updateArticles|Stop");

        return false;
    }

    public boolean deleteArticles(String id){
        log.info(" - " + operation_ID + " - **** newsEngine.class|deleteArticles|Start");

        connectIfDisconnected();

        if (MongoDBManager.getInstance().getOneDocumentFromCollectionWithID(id, CollectionNames.Articles.toString()) != null){
            MongoDBManager.getInstance().deleteOneDocumentFromCollection(id, CollectionNames.Articles.toString());
            log.info(" - " + operation_ID + " - **** newsEngine.class|deleteArticles|Stop");
            return true;
        }

        System.out.println("Il documento no è presente all'interno del database");

        log.info(" - " + operation_ID + " - **** newsEngine.class|deleteArticles|Stop");

        return false;
    }

    private void connectIfDisconnected(){
        if (!MongoDBManager.getInstance().isConnectionActive()){
            MongoDBManager.getInstance().connectWithUsernameAndPassword("admin", "admin");
        }
    }
}
