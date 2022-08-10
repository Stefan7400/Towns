package de.crackyman.towns;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.crackyman.towns.TownPlayer.TownPlayer;
import de.crackyman.towns.listener.mainlistener.PlayerJoinListener;
import de.crackyman.towns.listener.mainlistener.PlayerQuitListener;
import de.crackyman.towns.persistance.database.Database;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class TownsMain extends JavaPlugin {

    private static  TownsMain INSTANCE;
    private final Injector injector = Guice.createInjector(new PluginModule());
    private Database database;

    @Override
    public void onEnable() {
        INSTANCE = this;
       this.database = injector.getInstance(Database.class);


       registerListener();
    }



    @Override
    public void onDisable() {

    }

    private void registerListener(){
        List<Listener> listeners = List.of(injector.getInstance(PlayerJoinListener.class),
                injector.getInstance(PlayerQuitListener.class));

        PluginManager pm = Bukkit.getPluginManager();

        listeners.forEach(listener -> pm.registerEvents(listener,this));
    }

    public static TownsMain getINSTANCE() {
        return INSTANCE;
    }
}
