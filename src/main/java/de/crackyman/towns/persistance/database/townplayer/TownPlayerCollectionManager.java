package de.crackyman.towns.persistance.database.townplayer;

import com.google.inject.Inject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import de.crackyman.towns.TownPlayer.TownPlayer;
import de.crackyman.towns.persistance.database.Database;
import org.bson.Document;

import java.util.Optional;
import java.util.UUID;

public class TownPlayerCollectionManager {

    public static final String TP_COLLECTION_COINS = "townplayer_coins";
    public static final String TP_COLLECTION_FRIENDS_UUID = "friends_uuid";
    public static final String TP_COLLECTION_XP = "townplayer_xp";


    private final MongoCollection<Document> collection;

    @Inject
    private TownPlayerCollectionManager(Database database){
        this.collection = database.getTownPlayerCollection();
    }

    public void addTownPlayerDocument(Document document) {
        this.collection.insertOne(document);
    }

    public void updateTownPlayerDocument(TownPlayer townPlayer){
        Document searchQuery = new Document("uuid", townPlayer.getPlayer().getUniqueId().toString());

        Document updatedDoc = new Document().
                append(TP_COLLECTION_COINS,townPlayer.getCoins())
                .append(TP_COLLECTION_FRIENDS_UUID, townPlayer.getFriendsUUIDList())
                .append(TP_COLLECTION_XP, townPlayer.getXp());


        Document setQueryDoc = new Document();
        setQueryDoc.append("$set",updatedDoc);

        collection.updateOne(searchQuery,setQueryDoc);

    }

    public Optional<Document> fetchStoredFromCollection(UUID uuid) {
        return  Optional.ofNullable(collection.find(Filters.eq("uuid",uuid.toString())).first());
    }


}
