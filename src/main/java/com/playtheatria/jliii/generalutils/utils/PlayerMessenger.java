package com.playtheatria.jliii.generalutils.utils;

import com.playtheatria.jliii.generalutils.enums.Status;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

public class PlayerMessenger {

    public void sendResponse(Player player, String message, Status status) {
        player.sendMessage(Component.text(message, status.getColor()));
    }

    public void sendActionBar(Player player, String title, Status status) {
        player.sendActionBar(Component.text(title, status.getColor()));
    }

    public void sendMessage(Player player, String message) {
        player.sendMessage(Component.text(message));
    }

}
