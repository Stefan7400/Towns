package de.crackyman.towns.inventories.manager;

import com.google.inject.Inject;
import de.crackyman.towns.inventories.BaseInventory;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Optional;

public class UsedUsedInventoryManager implements IUsedInventoryManager {

    @Inject
    private UsedInventoryStorage usedInventoryStorage;

    @Override
    public void addUsedInventory(Player player, BaseInventory inventory) {
        player.openInventory(inventory.getInventory());
        this.usedInventoryStorage.addUsedInventory(player,inventory);
    }

    @Override
    public void remove(Player player) {
        this.usedInventoryStorage.remove(player);
    }

    @Override
    public boolean itemsClickable(Player player, Inventory inventory) {
        BaseInventory baseInventory = this.usedInventoryStorage.fetchBaseInventory(player.getUniqueId());
        if(baseInventory == null){
            return true;
        }

        return baseInventory.areItemsClickable();
    }

    @Override
    public void onInventoryClick(Player player, int slot) {
        BaseInventory baseInventory = this.usedInventoryStorage.fetchBaseInventory(player.getUniqueId());
        if(baseInventory == null){
            //Just to be safe
            return;
        }
        //TODO is null?? FIX
        Optional.of(baseInventory.getActionMap().get(slot)).ifPresent(Runnable::run);
    }

}
