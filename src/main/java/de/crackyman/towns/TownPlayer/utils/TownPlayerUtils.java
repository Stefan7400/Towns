package de.crackyman.towns.TownPlayer.utils;

import org.bson.Document;

import java.util.Collections;
import java.util.HashMap;
import java.util.UUID;

import static de.crackyman.towns.persistance.database.townplayer.TownPlayerCollectionManager.*;

public class TownPlayerUtils {

    //TODO maybe not needed in the future???
    public static HashMap<String, Object> fetchInitialDataMap(){
        HashMap<String,Object> valueMap = new HashMap<>();
        valueMap.put(TP_COLLECTION_COINS,500);
        valueMap.put(TP_COLLECTION_FRIENDS_UUID, Collections.emptyList());
        valueMap.put(TP_COLLECTION_XP, 0);
        valueMap.put(TP_COLLECTION_INCOMEING_FRIEND_REQ,Collections.emptyList());
        valueMap.put(TP_ALLOW_FRIEND_REQUESTS,true);
        return valueMap;
    }

    //TODO Maybe can use fetchInitialData?
    public static Document createNewTownPlayerDocument(UUID uuid){
        //TODO Find way to use uuid as UUID and not as String! -> MongoClientSettings??
        Document document = new Document("uuid", uuid.toString());
        document.putAll(fetchInitialDataMap());
        return document;
    }


    public static <T> T handleNullValue(T value, T standardValue){
        if(value == null){
            return standardValue;
        }
        return value;

    }

}
