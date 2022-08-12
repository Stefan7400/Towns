package de.crackyman.towns.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

import static net.kyori.adventure.text.format.NamedTextColor.RED;
import static net.kyori.adventure.text.format.NamedTextColor.YELLOW;

public class Messages {

    public static final Component NOT_INITIALIZED_YET = Component.text(">> Please wait one moment..").color(TextColor.color(RED));
    public static final Component ALREADY_SEND_FRIEND_REQUEST = Component.text(">> There is already a active friend request").color(TextColor.color(YELLOW));


}
