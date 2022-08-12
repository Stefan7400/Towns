package de.crackyman.towns.friendsmanager;

import de.crackyman.towns.TownPlayer.TownPlayer;
import de.crackyman.towns.persistance.database.townplayer.TownPlayerStorage;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

import java.util.Optional;
import java.util.UUID;

import static de.crackyman.towns.utils.Messages.ALREADY_SEND_FRIEND_REQUEST;
import static de.crackyman.towns.utils.Messages.NOT_INITIALIZED_YET;

public class FriendsManager implements IFriendManager{


    @Override
    public void sendFriendRequest(Player from, UUID to){
        Optional<TownPlayer> foundTownPlayerOptional = TownPlayerStorage.fetchTownPlayer(from);
        Optional<TownPlayer> foundToTownPlayerOptional = TownPlayerStorage.fetchTownPlayer(to);

        if(foundTownPlayerOptional.isEmpty() || foundToTownPlayerOptional.isEmpty()){
            //Should not be possible, but just to be safe
            return;
        }

        if(!foundTownPlayerOptional.get().isInitialized() || !foundToTownPlayerOptional.get().isInitialized()){
            //The sender or the receiver is not initialized
            from.sendMessage(NOT_INITIALIZED_YET);
            return;
        }
        TownPlayer toTownPlayer = foundToTownPlayerOptional.get();

        if(toTownPlayer.getIncomingFriendRequests().contains(from.getUniqueId())){
            from.sendMessage(ALREADY_SEND_FRIEND_REQUEST);
            return;
        }

        toTownPlayer.getIncomingFriendRequests().add(from.getUniqueId());
        //TODO make clickable!! [Accept] [Decline]
        toTownPlayer.getPlayer().sendMessage(Component.text(">> You received a friend request from "));





    }
}
