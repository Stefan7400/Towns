package de.crackyman.towns.utils;

import de.crackyman.towns.TownsMain;
import org.bukkit.Bukkit;

public class BukkitUtils {

    public static void runAsync(Runnable runnable){
        Bukkit.getServer().getScheduler().runTaskAsynchronously(TownsMain.getINSTANCE(),runnable);
    }

}
