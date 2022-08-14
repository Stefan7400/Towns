package de.crackyman.towns;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import de.crackyman.towns.commands.townplayercommands.SettingsCommand;
import de.crackyman.towns.friendssystem.manager.FriendsManager;
import de.crackyman.towns.friendssystem.manager.IFriendManager;
import de.crackyman.towns.inventories.manager.IUsedInventoryManager;
import de.crackyman.towns.inventories.manager.UsedInventoryStorage;
import de.crackyman.towns.inventories.manager.UsedUsedInventoryManager;
import de.crackyman.towns.listener.mainlistener.InventoryClickListener;
import de.crackyman.towns.listener.mainlistener.InventoryCloseListener;
import de.crackyman.towns.listener.mainlistener.PlayerJoinListener;
import de.crackyman.towns.listener.mainlistener.PlayerQuitListener;
import de.crackyman.towns.persistance.database.Database;
import de.crackyman.towns.persistance.database.townplayer.TownPlayerCollectionManager;
import de.crackyman.towns.persistance.database.townplayer.TownPlayerManager;
import de.crackyman.towns.persistance.database.townplayer.TownPlayerStorage;

public class PluginModule extends AbstractModule {
    @Override
    protected void configure() {


        //Listener
        bind(PlayerJoinListener.class).in(Singleton.class);
        bind(PlayerQuitListener.class).in(Singleton.class);
        bind(InventoryCloseListener.class).in(Singleton.class);
        bind(InventoryClickListener.class).in(Singleton.class);


        //FriendManager
        bind(FriendsManager.class).in(Singleton.class);
        bind(IFriendManager.class).to(FriendsManager.class);

        //Inventory
        bind(UsedInventoryStorage.class).in(Singleton.class);
        bind(UsedUsedInventoryManager.class).in(Singleton.class);
        bind(IUsedInventoryManager.class).to(UsedUsedInventoryManager.class);

        //commands
        bind(SettingsCommand.class).in(Singleton.class);



        //Database
        bind(Database.class).in(Singleton.class);

        //Townplayer stuff
        bind(TownPlayerManager.class).in(Singleton.class);
        bind(TownPlayerStorage.class).in(Singleton.class);
        bind(TownPlayerCollectionManager.class).in(Singleton.class);
    }
}
