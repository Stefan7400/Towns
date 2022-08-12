package de.crackyman.towns.TownPlayer;

import org.bukkit.entity.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import static de.crackyman.towns.persistance.database.townplayer.TownPlayerCollectionManager.*;

public class TownPlayer {

    private final Player player;
    private int coins;
    private int xp;
    private List<UUID> friendsUUIDList;
    private List<UUID> incomingFriendRequests;


    private boolean isInitialized = false;

    public TownPlayer(Player player){
        this.player = player;
    }

    public void initData(HashMap<String,Object> dataMap){
        this.coins = (int) dataMap.get(TP_COLLECTION_COINS);
        this.friendsUUIDList = (List<UUID>) dataMap.get(TP_COLLECTION_FRIENDS_UUID);
        this.xp = (int) dataMap.get(TP_COLLECTION_XP);
        this.incomingFriendRequests = (List<UUID>) dataMap.get(TP_COLLECTION_INCOMEING_FRIEND_REQ);

        this.isInitialized = true;
    }

    public Player getPlayer(){
        return this.player;
    }

    public void setInitialized(boolean initialized) {
        isInitialized = initialized;
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


}
