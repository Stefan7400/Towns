package de.crackyman.towns.commands.townplayercommands;

import com.google.inject.Inject;
import de.crackyman.towns.inventories.BaseInventory;
import de.crackyman.towns.inventories.SettingsInventory;
import de.crackyman.towns.inventories.manager.IUsedInventoryManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class SettingsCommand implements CommandExecutor {


    @Inject
    private IUsedInventoryManager inventoryManager;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(!(sender instanceof Player) && args.length != 0){
            //Is not a player;
        }
        Player player = (Player) sender;

        inventoryManager.addUsedInventory(player,new SettingsInventory());



        return true;
    }
}
