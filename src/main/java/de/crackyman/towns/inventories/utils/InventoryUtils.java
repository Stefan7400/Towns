package de.crackyman.towns.inventories.utils;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryUtils {


    public static void fillUpRemainingSlots(Inventory inventory, ItemStack itemStack){
        for(int slot = 0; slot < inventory.getSize(); slot++){
            ItemStack itemAtSlot = inventory.getItem(slot);
            if(itemAtSlot != null && itemAtSlot.getType() == Material.AIR){
                inventory.setItem(slot,itemAtSlot.clone());
            }
        }
    }
}
