package de.crackyman.towns;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.crackyman.towns.commands.townplayercommands.FriendsCommand;
import de.crackyman.towns.commands.townplayercommands.SettingsCommand;
import de.crackyman.towns.configuration.Configuration;
import de.crackyman.towns.listener.mainlistener.InventoryClickListener;
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

    private Configuration configuration;

    @Override
    public void onEnable() {
        INSTANCE = this;
       this.database = injector.getInstance(Database.class);
       this.configuration = injector.getInstance(Configuration.class);

       registerListener();
       registerCommands();
    }



    @Override
    public void onDisable() {
        Configuration.getInstance().save();
    }

    private void registerListener(){
        List<Listener> listeners = List.of(injector.getInstance(PlayerJoinListener.class),
                injector.getInstance(PlayerQuitListener.class),
                injector.getInstance(InventoryClickListener.class));

        PluginManager pm = Bukkit.getPluginManager();

        listeners.forEach(listener -> pm.registerEvents(listener,this));
    }

    private void registerCommands(){
       FriendsCommand friendsCommand = injector.getInstance(FriendsCommand.class);
       getCommand("friends").setExecutor(friendsCommand);
       getCommand("friends").setTabCompleter(friendsCommand);

       getCommand("settings").setExecutor(injector.getInstance(SettingsCommand.class));
    }

    public static TownsMain getINSTANCE() {
        return INSTANCE;
    }
}
