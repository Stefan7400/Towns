package de.crackyman.towns.inventories.manager;


import de.crackyman.towns.inventories.BaseInventory;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

/**
 * Holds all the currently opened Inventory of players
 */
public class UsedInventoryStorage {

    public final HashMap<UUID, BaseInventory> usedInventoryMap = new HashMap<>();


    protected void addUsedInventory(Player player, BaseInventory inventory){
        this.usedInventoryMap.put(player.getUniqueId(),inventory);
    }

    protected void remove(Player player){
        this.usedInventoryMap.remove(player.getUniqueId());
    }

    protected BaseInventory fetchBaseInventory(UUID uuid){
        return this.usedInventoryMap.get(uuid);
    }

}
