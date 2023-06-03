package com.playtheatria.jliii.generalutils.utils;

import com.playtheatria.jliii.generalutils.enums.Status;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public class PlayerMessage {
    public static void sendResponse(Player player, String message, Status status) {
        player.sendMessage(Component.text(message, status.getColor()));
    }
}
