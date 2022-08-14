package de.crackyman.towns.listener.mainlistener;

import com.google.inject.Inject;
import de.crackyman.towns.inventories.manager.IUsedInventoryManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryCloseListener implements Listener {

    @Inject
    private IUsedInventoryManager usedInventoryManager;

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event){
        usedInventoryManager.remove((Player) event.getPlayer());
    }
}
