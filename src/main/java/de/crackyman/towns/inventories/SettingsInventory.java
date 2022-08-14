package de.crackyman.towns.inventories;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class SettingsInventory extends BaseInventory{


    public SettingsInventory() {
        super(null, 27, Component.text("Settings"));
    }

    @Override
    void create() {
        this.currentInventory.setItem(3,new ItemStack(Material.STONE));
    }

    @Override
    HashMap<Integer, Runnable> fetchSlotActions() {
        HashMap<Integer,Runnable> actionsMap = new HashMap<>();
        actionsMap.put(3,() -> {this.currentInventory.getViewers().forEach(viewer -> viewer.sendMessage(Component.text("Clciekd!")));});
        return actionsMap;
    }
}
