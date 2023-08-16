package com.playtheatria.jliii.generalutils.utils;

import com.playtheatria.jliii.generalutils.enums.MessageStatus;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public class PlayerMessenger {

    public void sendResponse(Player player, String message, MessageStatus messageStatus) {
        player.sendMessage(Component.text(message, messageStatus.getColor()));
    }

    public void sendActionBar(Player player, String title, MessageStatus messageStatus) {
        player.sendActionBar(Component.text(title, messageStatus.getColor()));
    }

    public void sendMessage(Player player, String message) {
        player.sendMessage(Component.text(message));
    }

}
