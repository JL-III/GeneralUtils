package com.playtheatria.jliii.generalutils.commands;

import com.playtheatria.jliii.generalutils.GeneralUtils;
import com.playtheatria.jliii.generalutils.enums.MessageStatus;
import com.playtheatria.jliii.generalutils.enums.ToolColor;
import com.playtheatria.jliii.generalutils.enums.ToolStatus;
import com.playtheatria.jliii.generalutils.items.TitanItemInfo;
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
            Response<List<String>> loreResponse = TitanItemInfo.getLore(itemStack);
            if (!loreResponse.isSuccess()) return false;
            List<String> lore = loreResponse.value();
            Response<Boolean> isTitanToolResponse = TitanItemInfo.isTitanTool(lore);
            sender.sendMessage("isTitanTool: " + (isTitanToolResponse.isSuccess() ? isTitanToolResponse.value() : isTitanToolResponse.error()));
            sender.sendMessage("Contains charge lore: " + TitanItemInfo.hasCharge(lore, isTitanToolResponse));
            sender.sendMessage("ToolColor: " + TitanItemInfo.getColor(lore));
            sender.sendMessage("ToolStatus: " + TitanItemInfo.getStatus(lore, isTitanToolResponse));
            sender.sendMessage("isChargedTitanTool: " + TitanItemInfo.isChargedTitanTool(lore, isTitanToolResponse));
            sender.sendMessage("chargeLoreIndex: " + TitanItemInfo.getTitanLoreIndex(lore, TitanItemInfo.CHARGE_PREFIX, isTitanToolResponse));
            sender.sendMessage("statusLoreIndex: " + TitanItemInfo.getTitanLoreIndex(lore, TitanItemInfo.STATUS_PREFIX, isTitanToolResponse));
            sender.sendMessage("Get charge amount: " + TitanItemInfo.getCharge(lore, isTitanToolResponse, TitanItemInfo.hasCharge(lore,isTitanToolResponse), 39));
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
