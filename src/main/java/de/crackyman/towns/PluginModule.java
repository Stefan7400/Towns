package de.crackyman.towns;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import de.crackyman.towns.friendsmanager.FriendsManager;
import de.crackyman.towns.friendsmanager.IFriendManager;
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


        //FriendManager
        bind(FriendsManager.class).in(Singleton.class);
        bind(IFriendManager.class).to(FriendsManager.class);



        //Database
        bind(Database.class).in(Singleton.class);

        //Townplayer stuff
        bind(TownPlayerManager.class).in(Singleton.class);
        bind(TownPlayerStorage.class).in(Singleton.class);
        bind(TownPlayerCollectionManager.class).in(Singleton.class);
    }
}
