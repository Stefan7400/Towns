package de.crackyman.towns.TownPlayer.utils;

import org.bson.Document;

import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

import static de.crackyman.towns.persistance.database.townplayer.TownPlayerCollectionManager.*;

public class TownPlayerUtils {

    public static HashMap<String,Object> valueParser(Document document){
        HashMap<String,Object> valueMap = new HashMap<>();
        valueMap.put(TP_COLLECTION_COINS,document.getInteger(TP_COLLECTION_COINS));
        valueMap.put(TP_COLLECTION_FRIENDS_UUID,document.getList(TP_COLLECTION_FRIENDS_UUID, UUID.class));
        valueMap.put(TP_COLLECTION_XP,document.getInteger(TP_COLLECTION_XP));
        valueMap.put(TP_COLLECTION_INCOMEING_FRIEND_REQ,document.getList(TP_COLLECTION_INCOMEING_FRIEND_REQ,UUID.class));
        return valueMap;
    }

    public static HashMap<String, Object> fetchInitialDataMap(){
        HashMap<String,Object> valueMap = new HashMap<>();
        valueMap.put(TP_COLLECTION_COINS,500);
        valueMap.put(TP_COLLECTION_FRIENDS_UUID, Collections.emptyList());
        valueMap.put(TP_COLLECTION_XP, 0);
        valueMap.put(TP_COLLECTION_INCOMEING_FRIEND_REQ,Collections.emptyList());
        return valueMap;
    }

    //TODO Maybe can use fetchInitialData?
    public static Document createNewTownPlayerDocument(UUID uuid){
        //TODO Find way to use uuid as UUID and not as String! -> MongoClientSettings??
        Document document = new Document("uuid", uuid.toString());
        //TODO USE THE initalDataMap for this????
        document.put(TP_COLLECTION_COINS,500);
        document.put(TP_COLLECTION_FRIENDS_UUID, Collections.emptyList());
        document.put(TP_COLLECTION_XP, 0);
        document.put(TP_COLLECTION_INCOMEING_FRIEND_REQ,Collections.emptyList());
        return document;
    }

}
