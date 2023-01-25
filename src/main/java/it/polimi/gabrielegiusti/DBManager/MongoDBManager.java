package it.polimi.gabrielegiusti.DBManager;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

/**
 * This class is the manager that make the system able to perform requests of access to the data and handle all the
 * possible problems that can arise with operations of this type. In this class we can find all the useful methods
 * for data manipulation in a given database. The manager doesn't communicate directly with the user but there's
 * another layer, the effective service, that provides all the type of requests needed by the user.
 */
public class MongoDBManager {

    static protected Logger log = LogManager.getLogger(MongoDBManager.class);

    private boolean connectionActive = false;

    private static MongoDBManager instance;

    private MongoClient client;

    private MongoDBManager(){}

    /**
     * The method getInstance() create, if no one exist already, an instance of the connection of the database,
     * that allows the user to perform any of the CRUD operations on the given database. If an instance is already
     * present during the execution of the service, this method returns it, resulting in an improvement of performance.
     * @return the instance of the connection
     */
    public static MongoDBManager getInstance() {

        log.debug(" ### MongoDBManager.class|dbManager getInstance START.");
        if (instance == null){
            log.info(" ### MongoDBManager.class|dbManager object is empty:initialize.");
            synchronized (MongoDBManager.class){
                if (instance == null) instance = new MongoDBManager();
            }
        }
        log.debug(" ### MongoDBManager.class|dbManager getInstance FINISH.");
        return instance;
    }

    /**
     * This method performs an attempt in order to connect the service with the database. If the attempt has the right
     * result, the service establish a connection with the server. Otherwise, it rises an exception
     * @param username
     * @param password
     */
    public void connectWithUsernameAndPassword(String username, String password){
        final String URI = "mongodb://" + username + ":" + password + "@localhost:27017/admin?authSource=admin";

        if (!isConnectionActive()) {
            try {
                client = new MongoClient(new MongoClientURI(URI));
                this.connectionActive = true;
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public MongoClient getMongoClient(){
        return client;
    }

    public boolean isConnectionActive() {
        return connectionActive;
    }

    private MongoDatabase getDatabase(){
        return getMongoClient().getDatabase("SMBUD_Project");
    }

    private MongoCollection<Document> getCollection(String collectionName){
        return getDatabase().getCollection(collectionName);
    }

    public void createCollection(String collectionName){
        getDatabase().createCollection(collectionName);
    }

    public void addOneDocumentToCollection(Document document, String collectionName) {
        getCollection(collectionName).insertOne(document.append("lastUpdated", new SimpleDateFormat("yyyy.MM.dd&HH:mm:ss").format(new Date())));
    }

    public void addManyDocumentsToCollection(List<Document> documents, String collectionName) {
        getCollection(collectionName).insertMany(documents);
    }

    public void updateOneDocumentInACollection(Document query, Document newValue, String collectionName) {
        getCollection(collectionName).updateOne(query, newValue.append("lastUpdated", new SimpleDateFormat("yyyy.MM.dd&HH:mm:ss").format(new Date())));
    }

    public void updateOneDocumentInACollectionWithID(String id, Document newValue, String collection){
        getCollection(collection).updateOne(eq("_id", new ObjectId(id)), new Document("$set", newValue.append("lastUpdated", new SimpleDateFormat("yyyy.MM.dd&HH:mm:ss").format(new Date()))));
    }

    public void updateManyDocumentsInACollection(Document query, Document document, String collectionName) {
        getCollection(collectionName).updateMany(query, document);
    }

    public Document getOneDocumentFromCollection(Document query, String collectionName) {

        Document result = getCollection(collectionName).find(query).first();

        if (result == null){
            System.out.println("No result found.");
        }

        return result;
    }

    public Document getOneDocumentFromCollectionWithID(String id, String collection){

        try {
            return getCollection(collection).find(eq("_id", new ObjectId(id))).first();
        } catch (IllegalArgumentException e){
            System.out.println("L'id fornito non è nel giusto formato " + e);
            return null;
        }
    }

    public void deleteOneDocumentFromCollection(String id, String collection){

        if (getOneDocumentFromCollectionWithID(id, collection) != null) {
            getCollection(collection).deleteOne(eq("_id", new ObjectId(id)));
        } else {
            log.info("Documento non presente all'interno del database");
        }
    }

    public boolean closeConnection(){

        try {
            client = null;
            connectionActive = false;
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
