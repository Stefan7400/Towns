package de.crackyman.towns.commands.townplayercommands;

import com.google.inject.Inject;
import de.crackyman.towns.TownPlayer.TownPlayer;
import de.crackyman.towns.friendssystem.manager.IFriendManager;
import de.crackyman.towns.persistance.database.townplayer.TownPlayerStorage;
import de.crackyman.towns.utils.BukkitUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static de.crackyman.towns.utils.Messages.NOT_INITIALIZED_YET;
import static net.kyori.adventure.text.format.NamedTextColor.*;

public class FriendsCommand implements CommandExecutor, TabCompleter {

    private static final String ALLOW_FRIEND_REQUESTS = "allowFriendRequests";
    private static final String ADD = "add";

    private static final List<String> usableCommandsFirstArgs = List.of(ALLOW_FRIEND_REQUESTS,ADD);


    @Inject
    private IFriendManager friendManager;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!(sender instanceof Player)){
            //Is not a player
            return true;
        }

        Player player = (Player) sender;
        Optional<TownPlayer> townPlayerOptional = TownPlayerStorage.fetchTownPlayer(player);
        if(townPlayerOptional.isEmpty() || !townPlayerOptional.get().isInitialized()){
            player.sendMessage(NOT_INITIALIZED_YET);
            return true;
        }

        TownPlayer townPlayer = townPlayerOptional.get();

        if(args.length == 1){
            if(args[0].equals(ALLOW_FRIEND_REQUESTS)){
                this.friendManager.toggleAllowFriendRequest(townPlayer);
                return true;
            }
        }

        if(args[0].equals(ADD) && args.length == 2){
            //friends add name
            Optional<? extends Player> foundPlayerOptional = BukkitUtils.fetchOnlinePlayerForName(args[1]);
            foundPlayerOptional.ifPresentOrElse(foundPlayer -> friendManager.sendFriendRequest(townPlayerOptional.get(),foundPlayer.getUniqueId()),() -> {
                player.sendMessage(Component.text(">> The player ").color(TextColor.color(YELLOW)).append(
                        Component.text(args[1]).color(TextColor.color(GREEN)).append(Component.text(" is currently not online")).color(TextColor.color(YELLOW))));
                player.playSound(player, Sound.ENTITY_VILLAGER_NO,1,1);
            });

        }
        return true;
    }


    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length == 1){
            return usableCommandsFirstArgs.stream().filter(arg -> StringUtil.startsWithIgnoreCase(arg,args[0])).collect(Collectors.toList());
        }

        return Collections.emptyList();
    }
}
