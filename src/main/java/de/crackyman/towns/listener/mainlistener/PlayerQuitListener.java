package de.crackyman.towns.listener.mainlistener;

import com.google.inject.Inject;
import de.crackyman.towns.inventories.manager.IUsedInventoryManager;
import de.crackyman.towns.persistance.database.townplayer.TownPlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    @Inject
    private TownPlayerManager townPlayerManager;

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        this.townPlayerManager.unloadTownPlayer(player);
    }
}
