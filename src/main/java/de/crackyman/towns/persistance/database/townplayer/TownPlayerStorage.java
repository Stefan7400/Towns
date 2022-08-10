package de.crackyman.towns.persistance.database.townplayer;

import de.crackyman.towns.TownPlayer.TownPlayer;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

public class TownPlayerStorage {

    private static final HashMap<UUID, TownPlayer> townPlayerStorage = new HashMap<>();


    protected void storeTownPlayer(TownPlayer townPlayer){
        townPlayerStorage.put(townPlayer.getPlayer().getUniqueId(),townPlayer);
    }

    protected void removeTownPlayer(Player player){
        townPlayerStorage.remove(player.getUniqueId());
    }

    protected Collection<TownPlayer> fetchAllTownPlayer(){
        return townPlayerStorage.values();
    }

    public static Optional<TownPlayer> fetchTownPlayer(Player player){
        UUID uuid = player.getUniqueId();
        if(townPlayerStorage.containsKey(uuid)){
            return Optional.of(townPlayerStorage.get(uuid));
        }
        return Optional.empty();
    }

}
