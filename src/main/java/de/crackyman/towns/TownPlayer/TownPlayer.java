package de.crackyman.towns.TownPlayer;

import de.crackyman.towns.TownPlayer.utils.TownPlayerUtils;
import org.bson.Document;
import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static de.crackyman.towns.persistance.database.townplayer.TownPlayerCollectionManager.*;

public class TownPlayer {

    private final Player player;
    private int coins;
    private int xp;
    private List<UUID> friendsUUIDList;
    private List<UUID> incomingFriendRequests;
    private boolean allowFriendRequests = true;
    private boolean isInitialized = false;

    public TownPlayer(Player player){
        this.player = player;
    }


    public void initData(Document document){
        this.coins = document.getInteger(TP_COLLECTION_COINS);
        this.friendsUUIDList = document.getList(TP_COLLECTION_FRIENDS_UUID, UUID.class);
        this.xp = document.getInteger(TP_COLLECTION_XP);
        this.incomingFriendRequests = document.getList(TP_COLLECTION_INCOMEING_FRIEND_REQ,UUID.class);
        this.allowFriendRequests = TownPlayerUtils.handleNullValue(document.getBoolean(TP_ALLOW_FRIEND_REQUESTS),true);

        this.isInitialized = true;
    }

    public Player getPlayer(){
        return this.player;
    }

    public boolean isInitialized() {
        return isInitialized;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public List<UUID> getFriendsUUIDList() {
        return friendsUUIDList;
    }

    public void setFriendsUUIDList(List<UUID> friendsUUIDList) {
        if(friendsUUIDList == null){
            friendsUUIDList = Collections.emptyList();
            return;
        }
        this.friendsUUIDList = friendsUUIDList;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public List<UUID> getIncomingFriendRequests() {
        return incomingFriendRequests;
    }

    public void setIncomingFriendRequests(List<UUID> incomingFriendRequests) {
        if(incomingFriendRequests == null){
            this.incomingFriendRequests = Collections.emptyList();
            return;
        }
        this.incomingFriendRequests = incomingFriendRequests;
    }

    public boolean isAllowFriendRequests() {
        return allowFriendRequests;
    }

    public void setAllowFriendRequests(boolean allowFriendRequests) {
        this.allowFriendRequests = allowFriendRequests;
    }
}
