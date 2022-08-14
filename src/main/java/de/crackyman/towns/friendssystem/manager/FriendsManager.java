package de.crackyman.towns.friendssystem.manager;

import de.crackyman.towns.TownPlayer.TownPlayer;
import de.crackyman.towns.persistance.database.townplayer.TownPlayerStorage;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Sound;

import java.util.Optional;
import java.util.UUID;

import static de.crackyman.towns.utils.Messages.ALREADY_SEND_FRIEND_REQUEST;
import static de.crackyman.towns.utils.Messages.NOT_INITIALIZED_YET;
import static net.kyori.adventure.text.format.NamedTextColor.*;

public class FriendsManager implements IFriendManager{


    @Override
    public void sendFriendRequest(TownPlayer from, UUID to){
        Optional<TownPlayer> foundToTownPlayerOptional = TownPlayerStorage.fetchTownPlayer(to);

        if(foundToTownPlayerOptional.isEmpty()){
            return;
        }

        if(!foundToTownPlayerOptional.get().isInitialized()){
            from.getPlayer().sendMessage(NOT_INITIALIZED_YET);
            return;
        }
        TownPlayer toTownPlayer = foundToTownPlayerOptional.get();

        if(toTownPlayer.getIncomingFriendRequests().contains(from.getPlayer().getUniqueId())){
            from.getPlayer().sendMessage(ALREADY_SEND_FRIEND_REQUEST);
            return;
        }

        toTownPlayer.getIncomingFriendRequests().add(from.getPlayer().getUniqueId());
        //TODO make clickable!! [Accept] [Decline]
        toTownPlayer.getPlayer().sendMessage(Component.text(">> You received a friend request from ").color(TextColor.color(BLUE)).
                append(Component.text(from.getPlayer().getName()).color(TextColor.color(GREEN))));
        toTownPlayer.getPlayer().playSound(toTownPlayer.getPlayer(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP,1,1);


    }

    @Override
    public void toggleAllowFriendRequest(TownPlayer townPlayer) {
        townPlayer.setAllowFriendRequests(!townPlayer.isAllowFriendRequests());
            townPlayer.getPlayer().sendMessage(Component.text(">> Incoming friend requests are now ").color(TextColor.color(BLUE)).
                    append(townPlayer.isAllowFriendRequests() ? Component.text("Enabled!").color(TextColor.color(GREEN)) :
                            Component.text("Disabled!").color(TextColor.color(RED))));

    }
}
