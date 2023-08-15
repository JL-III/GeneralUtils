package com.playtheatria.jliii.generalutils.commands;

import com.playtheatria.jliii.generalutils.GeneralUtils;
import com.playtheatria.jliii.generalutils.enums.Status;
import com.playtheatria.jliii.generalutils.managers.ConfigManager;
import com.playtheatria.jliii.generalutils.utils.PlayerMessenger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdminCommands implements CommandExecutor {

    private GeneralUtils plugin;
    private ConfigManager configManager;
    private PlayerMessenger playerMessenger;

    public AdminCommands(GeneralUtils plugin, ConfigManager configManager, PlayerMessenger playerMessenger) {
        this.plugin = plugin;
        this.configManager = configManager;
        this.playerMessenger = playerMessenger;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) {
            return false;
        }

        if (!player.hasPermission("generalutils.admincommands")) {
            player.sendMessage("No permission.");
            return true;
        }

        if (args.length == 0) return false;

        if (args.length == 1 && args[0].equalsIgnoreCase("version")) {
            plugin.getCustomLogger().sendLog("GeneralUtils version " + plugin.getDescription().getVersion());
            return true;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            configManager.reload();
            player.sendMessage(ChatColor.GREEN + "Reloaded config");
            plugin.getCustomLogger().sendLog("Reloaded Config");
            return true;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("tasks")) {
            playerMessenger.sendResponse(player, "Tasks found.", Status.VALID);
            plugin.getServer().getScheduler().getPendingTasks().forEach(task -> playerMessenger.sendMessage(player, task.getTaskId() + " - " + task.getOwner().getName()));
            return true;
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("tasks")) {
            List<BukkitTask> tasks = plugin.getServer().getScheduler().getPendingTasks();
            if (tasks.stream().noneMatch(task -> task.getOwner().getName().equalsIgnoreCase(args[1]))) {
                playerMessenger.sendResponse(player, "No tasks found.", Status.INVALID);
            } else {
                playerMessenger.sendResponse(player, "Tasks found.", Status.VALID);
                tasks.stream().filter(task -> task.getOwner().getName().equalsIgnoreCase(args[1])).forEach(task -> player.sendMessage(task.getTaskId() + " - " + task.getOwner().getName()));
            }

            return true;
        }
        return false;
    }
}
