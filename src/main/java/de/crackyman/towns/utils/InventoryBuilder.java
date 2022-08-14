package de.crackyman.towns.utils;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import java.util.function.Consumer;

public class InventoryBuilder {

    private Inventory inventory;


    public InventoryBuilder(int size,String inventoryName) {
        this.inventory = Bukkit.createInventory(null,size, Component.text(inventoryName));
    }

    public InventoryBuilder fillInventory(Consumer<Inventory> fillUp){
        fillUp.accept(this.inventory);
        return this;
    }


    public Inventory build(){
        return this.inventory;
    }





}
