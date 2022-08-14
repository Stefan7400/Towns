package de.crackyman.towns.persistance.database.townplayer;

import com.google.inject.Inject;
import de.crackyman.towns.TownPlayer.TownPlayer;
import de.crackyman.towns.TownPlayer.utils.TownPlayerUtils;
import de.crackyman.towns.inventories.manager.IUsedInventoryManager;
import de.crackyman.towns.persistance.database.Callback;
import de.crackyman.towns.utils.BukkitUtils;
import org.bson.Document;
import org.bukkit.entity.Player;

import java.util.Optional;

//TODO ADD interface
public class TownPlayerManager {

    @Inject
    private IUsedInventoryManager usedInventoryManager;
    @Inject
    private TownPlayerCollectionManager townPlayerCollectionManager;

    @Inject
    private TownPlayerStorage townPlayerStorage;

    public void initializeTownPlayer(Player player){
        TownPlayer townPlayer = new TownPlayer(player);
        this.townPlayerStorage.storeTownPlayer(townPlayer);

        this.townPlayerCollectionManager.fetchStoredFromCollection(player.getUniqueId(), new Callback<>() {
            @Override
            public void onSuccess(Optional<Document> param) {
                param.ifPresentOrElse(townPlayer::initData, () -> {
                    townPlayerCollectionManager.addTownPlayerDocument(TownPlayerUtils.createNewTownPlayerDocument(player.getUniqueId()));
                    townPlayer.initData(TownPlayerUtils.createNewTownPlayerDocument(player.getUniqueId()));
                });
            }
        });
    }

    public void unloadTownPlayer(Player player){
        TownPlayerStorage.fetchTownPlayer(player).ifPresent(townPlayer -> {
            this.townPlayerStorage.removeTownPlayer(player);
            BukkitUtils.runAsync(() -> {
                this.townPlayerCollectionManager.updateTownPlayerDocument(townPlayer);
            });
        });
        //NOTE: Removal of the inventory might happen earlier then the save to the database, be careful when wanting to store some inventory stuff to the db
        //      in the future
        this.usedInventoryManager.remove(player);
    }


}
