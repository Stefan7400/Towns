package de.crackyman.towns.inventories;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import javax.annotation.Nullable;
import java.util.HashMap;

public abstract class BaseInventory {

    protected final Inventory currentInventory;

    /**
     * Shows how many slots for items this inventory provides
     * WILL BE USEFUL FOR MULTIPAGE Inventories
     */
    private int usableSlotAmount;

    private boolean itemsInInventoryAreClickable = false;

    /**
     * Holds the a {@link Runnable} which holds the action for when a certain slot is clicked
     */
    private final HashMap<Integer,Runnable> actionMap;

    public BaseInventory(@Nullable InventoryHolder owner, int size, Component name){
        this.currentInventory = Bukkit.createInventory(owner,size,name);
        create();
        this.actionMap = fetchSlotActions();
    }

    abstract void create();

    abstract HashMap<Integer,Runnable> fetchSlotActions();

    public Inventory getInventory(){
        return this.currentInventory;
    }

    public boolean areItemsClickable(){
        return this.itemsInInventoryAreClickable;
    }

    public HashMap<Integer, Runnable> getActionMap() {
        return actionMap;
    }
}
