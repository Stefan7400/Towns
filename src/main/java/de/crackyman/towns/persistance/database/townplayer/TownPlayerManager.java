package de.crackyman.towns.persistance.database.townplayer;

import com.google.inject.Inject;
import de.crackyman.towns.TownPlayer.TownPlayer;
import de.crackyman.towns.TownPlayer.utils.TownPlayerUtils;
import de.crackyman.towns.utils.BukkitUtils;
import org.bson.Document;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

public class TownPlayerManager {

    @Inject
    private TownPlayerCollectionManager townPlayerCollectionManager;

    @Inject
    private TownPlayerStorage townPlayerStorage;

    public void initializeTownPlayer(Player player){
        TownPlayer townPlayer = new TownPlayer(player);
        this.townPlayerStorage.storeTownPlayer(townPlayer);
        BukkitUtils.runAsync(() -> {
            Optional<Document> documentOptional = this.townPlayerCollectionManager.fetchStoredFromCollection(player.getUniqueId());
            documentOptional.ifPresentOrElse(document -> townPlayer.initData(TownPlayerUtils.valueParser(document)),() -> {
                this.townPlayerCollectionManager.addTownPlayerDocument(TownPlayerUtils.createNewTownPlayerDocument(player.getUniqueId()));
                townPlayer.initData(TownPlayerUtils.fetchInitialDataMap());
            });

        });
    }

    public void unloadTownPlayer(Player player){
        TownPlayerStorage.fetchTownPlayer(player).ifPresent(townPlayer -> {
            this.townPlayerStorage.removeTownPlayer(player);
            BukkitUtils.runAsync(() -> {
                this.townPlayerCollectionManager.updateTownPlayerDocument(townPlayer);
            });
        });
    }


}
