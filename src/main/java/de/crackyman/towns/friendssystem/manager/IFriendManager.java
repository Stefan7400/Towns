package de.crackyman.towns.friendssystem.manager;

import de.crackyman.towns.TownPlayer.TownPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface IFriendManager {

    /**
     *
     * @param from
     * @param to
     */
    void sendFriendRequest(TownPlayer from, UUID to);

    void toggleAllowFriendRequest(TownPlayer townPlayer);
}
