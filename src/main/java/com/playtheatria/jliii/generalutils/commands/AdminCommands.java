package com.playtheatria.jliii.generalutils.commands;

import com.playtheatria.jliii.generalutils.GeneralUtils;
import com.playtheatria.jliii.generalutils.enums.MessageStatus;
import com.playtheatria.jliii.generalutils.items.TitanItem;
import com.playtheatria.jliii.generalutils.managers.ConfigManager;
import com.playtheatria.jliii.generalutils.utils.PlayerMessenger;
import com.playtheatria.jliii.generalutils.utils.Response;
import org.bukkit.ChatColor;
import org.bukkit.block.CommandBlock;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
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
        if (sender instanceof CommandBlock) return false;
        if (!sender.hasPermission("generalutils.admincommands")) return false;
        if (args[0].equalsIgnoreCase("debug")) {
            ItemStack itemStack = !(sender instanceof Player player) ? configManager.getTestTool() : player.getInventory().getItemInMainHand();
            sender.sendMessage(ChatColor.LIGHT_PURPLE + "--------------------Debug--------------------");
            Response<List<String>> loreResponse = TitanItem.getLore(itemStack);
            if (!loreResponse.isSuccess()) return false;
            List<String> lore = loreResponse.value();
            boolean isTitanTool = TitanItem.isTitanTool(lore);
            sender.sendMessage("isTitanTool: " + isTitanTool);
            sender.sendMessage("Contains charge lore: " + TitanItem.hasChargeLore(lore, isTitanTool));
            sender.sendMessage("ToolColor: " + TitanItem.getColor(lore));
            sender.sendMessage("ToolStatus: " + TitanItem.getStatus(lore, isTitanTool));
            sender.sendMessage("isChargedTitanTool: " + TitanItem.isChargedTitanTool(lore, isTitanTool));
            sender.sendMessage("chargeLoreIndex: " + TitanItem.getTitanLoreIndex(lore, TitanItem.CHARGE_PREFIX, isTitanTool));
            sender.sendMessage("statusLoreIndex: " + TitanItem.getTitanLoreIndex(lore, TitanItem.STATUS_PREFIX, isTitanTool));
            sender.sendMessage("Get charge amount: " + TitanItem.getCharge(lore, isTitanTool, TitanItem.hasChargeLore(lore, isTitanTool), 39));
            if (itemStack.getItemMeta().hasCustomModelData()) {
                sender.sendMessage("Current custom model data: " + itemStack.getItemMeta().getCustomModelData());
            } else {
                sender.sendMessage("This item does not have custom model data.");
            }
            return true;
        }
        if (!(sender instanceof Player player)) {
            return false;
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
            playerMessenger.sendResponse(player, "Tasks found.", MessageStatus.VALID);
            plugin.getServer().getScheduler().getPendingTasks().forEach(task -> playerMessenger.sendMessage(player, task.getTaskId() + " - " + task.getOwner().getName()));
            return true;
        }

        if (args.length == 2 && args[0].equalsIgnoreCase("tasks")) {
            List<BukkitTask> tasks = plugin.getServer().getScheduler().getPendingTasks();
            if (tasks.stream().noneMatch(task -> task.getOwner().getName().equalsIgnoreCase(args[1]))) {
                playerMessenger.sendResponse(player, "No tasks found.", MessageStatus.INVALID);
            } else {
                playerMessenger.sendResponse(player, "Tasks found.", MessageStatus.VALID);
                tasks.stream().filter(task -> task.getOwner().getName().equalsIgnoreCase(args[1])).forEach(task -> player.sendMessage(task.getTaskId() + " - " + task.getOwner().getName()));
            }

            return true;
        }
        return false;
    }
}
