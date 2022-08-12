package de.crackyman.towns.commands.townplayercommands;

import com.google.inject.Inject;
import de.crackyman.towns.TownPlayer.TownPlayer;
import de.crackyman.towns.TownPlayer.utils.TownPlayerUtils;
import de.crackyman.towns.friendsmanager.IFriendManager;
import de.crackyman.towns.persistance.database.townplayer.TownPlayerStorage;
import de.crackyman.towns.utils.BukkitUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

import static de.crackyman.towns.utils.Messages.NOT_INITIALIZED_YET;
import static net.kyori.adventure.text.format.NamedTextColor.*;

public class FriendsCommand implements CommandExecutor {

    @Inject
    private IFriendManager friendManager;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)){
            //Is not a player
            return true;
        }

        Player player = (Player) sender;

        if(args[0].equals("add") && args.length == 2){
            //friends add name
            Optional<? extends Player> foundPlayerOptional = BukkitUtils.fetchOnlinePlayerForName(args[1]);
            foundPlayerOptional.ifPresentOrElse(foundPlayer -> friendManager.sendFriendRequest(player,foundPlayer.getUniqueId()),() -> {
                player.sendMessage(Component.text(">> The player ").color(TextColor.color(YELLOW)).append(
                        Component.text(args[1]).color(TextColor.color(GREEN)).append(Component.text(" is currently not online")).color(TextColor.color(YELLOW))));
                player.playSound(player, Sound.ENTITY_VILLAGER_NO,1,1);
            });

        }
        return true;
    }



}
