package de.crackyman.towns.persistance.database;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import org.bson.Document;

import javax.xml.crypto.Data;
import java.util.ArrayList;

public class Database {

    public static final String DB_NAME = "mctownsdb";
    private static final String TOWN_PLAYER_COLLECTION_NAME = "townplayercollection";
    private final MongoClient client = MongoClients.create("mongodb://localhost:27017");
    private final MongoDatabase db;

    private final MongoCollection<Document> townPlayerCollection;

    public Database(){
        this.db = client.getDatabase(DB_NAME);
        this.townPlayerCollection = checkForCollection(TOWN_PLAYER_COLLECTION_NAME,"uuid");
    }

    private MongoCollection<Document> checkForCollection(String collectionName, final String index){
        if (!db.listCollectionNames().into(new ArrayList<>()).contains(collectionName)) {
            //Collection not found -> create it
            return createColletion(collectionName,index);
        }
        return db.getCollection(collectionName);
    }

    private MongoCollection<Document> createColletion(String collectionName, final String index){
        db.createCollection(collectionName);
        MongoCollection<Document> collection = db.getCollection(collectionName);
        collection.createIndex(Indexes.descending(index), new IndexOptions().unique(true));
        return collection;
    }

    public MongoCollection<Document> getTownPlayerCollection() {
        return this.townPlayerCollection;
    }
}
