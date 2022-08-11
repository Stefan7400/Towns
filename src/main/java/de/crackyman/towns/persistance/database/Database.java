package de.crackyman.towns.persistance.database;

import com.google.inject.Inject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import de.crackyman.towns.configuration.Configuration;
import org.bson.Document;

import java.util.ArrayList;

public class Database {

    private Configuration configuration;

    //"mctownsdb"
    public static  String DB_NAME;
    private static final String TOWN_PLAYER_COLLECTION_NAME = "townplayercollection";
    private final MongoClient client = MongoClients.create("mongodb://localhost:27017");
    private final MongoDatabase db;

    private final MongoCollection<Document> townPlayerCollection;

    public Database(){
        init();
        this.db = client.getDatabase(DB_NAME);
        this.townPlayerCollection = checkForCollection(TOWN_PLAYER_COLLECTION_NAME,"uuid");
    }

    private void init(){
        this.configuration = Configuration.getInstance();
        DB_NAME = configuration.getDatabaseName();
    }

    private MongoCollection<Document> checkForCollection(String collectionName, final String index){
        if (!db.listCollectionNames().into(new ArrayList<>()).contains(collectionName)) {
            //Collection not found -> create it
            return createCollection(collectionName,index);
        }
        return db.getCollection(collectionName);
    }

    private MongoCollection<Document> createCollection(String collectionName, final String index){
        db.createCollection(collectionName);
        MongoCollection<Document> collection = db.getCollection(collectionName);
        collection.createIndex(Indexes.descending(index), new IndexOptions().unique(true));
        return collection;
    }

    public MongoCollection<Document> getTownPlayerCollection() {
        return this.townPlayerCollection;
    }
}
