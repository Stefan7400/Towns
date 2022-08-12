package de.crackyman.towns.friendsmanager;

import org.bukkit.entity.Player;

import java.util.UUID;

public interface IFriendManager {

    /**
     *
     * @param from
     * @param to
     */
    void sendFriendRequest(Player from, UUID to);
}
