package de.crackyman.towns.listener.mainlistener;

import com.google.inject.Inject;
import de.crackyman.towns.inventories.manager.IUsedInventoryManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryClickListener implements Listener {

    @Inject
    private IUsedInventoryManager usedInventoryManager;

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        usedInventoryManager.onInventoryClick((Player) event.getWhoClicked(),event.getSlot());
        event.setCancelled(usedInventoryManager.itemsClickable((Player) event.getWhoClicked(),event.getClickedInventory()));
    }
}
