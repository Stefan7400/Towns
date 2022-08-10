package de.crackyman.towns.listener.mainlistener;

import com.google.inject.Inject;
import de.crackyman.towns.persistance.database.townplayer.TownPlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @Inject
    private TownPlayerManager townPlayerManager;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        player.sendMessage("YOO");
        townPlayerManager.initializeTownPlayer(player);
    }
}
