package de.crackyman.towns.inventories.manager;

import de.crackyman.towns.inventories.BaseInventory;
import de.crackyman.towns.listener.mainlistener.InventoryCloseListener;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public interface IUsedInventoryManager {

    void addUsedInventory(Player player, BaseInventory inventory);

    void remove(Player player);

    boolean itemsClickable(Player player, Inventory inventory);

    void onInventoryClick(Player player, int slot);
}
