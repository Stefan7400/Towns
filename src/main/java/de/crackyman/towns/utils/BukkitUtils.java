package de.crackyman.towns.utils;

import de.crackyman.towns.TownsMain;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Optional;

public class BukkitUtils {

    public static void runAsync(Runnable runnable){
        Bukkit.getServer().getScheduler().runTaskAsynchronously(TownsMain.getINSTANCE(),runnable);
    }

    public static Optional<? extends Player> fetchOnlinePlayerForName(String givenPlayerName){
        return Bukkit.getServer().getOnlinePlayers().stream().filter(player -> player.getName().equals(givenPlayerName)).findFirst();
    }

}
