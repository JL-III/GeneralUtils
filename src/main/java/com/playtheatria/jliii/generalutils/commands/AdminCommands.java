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
        if (!(sender instanceof Player player)) {
            if (args[0].equalsIgnoreCase("debug")) {
                ItemStack itemStack = configManager.getTestTool();
                sender.sendMessage(ChatColor.LIGHT_PURPLE + "--------------------Debug--------------------");
                sender.sendMessage("Contains charge lore: " + TitanItemInfo.hasCharge(itemStack));
                List<String> lore = TitanItemInfo.getLore(itemStack);
                if (lore.size() < 1) return false;
                sender.sendMessage("Charge lore index: " + TitanItemInfo.getChargeLoreIndex(lore));
                Response<ToolColor> toolColorResponse = TitanItemInfo.getColor(lore);
                Response<ToolStatus> toolStatusResponse = TitanItemInfo.getStatus(lore);
                sender.sendMessage("ToolColor: " + (toolColorResponse.errorMessage() != null ? toolColorResponse.errorMessage() : toolColorResponse.value()));
                sender.sendMessage("ToolColor: " + (toolStatusResponse.errorMessage() != null ? toolStatusResponse.errorMessage() : toolStatusResponse.value()));
                try {
                    sender.sendMessage("Length of string: " + lore.get(TitanItemInfo.getChargeLoreIndex(lore)).length());
                    sender.sendMessage("Length of CHARGE_PREFIX " + TitanItemInfo.CHARGE_PREFIX.length());
                    if (toolColorResponse.isSuccess()) {
                        sender.sendMessage("Length of generated string: " + TitanItemInfo.getChargeLore(toolColorResponse.value(), 0).length());
                    }
                    sender.sendMessage("Get charge amount: " + TitanItemInfo.getCharge(lore, 39));
                } catch (NumberFormatException exception) {
                    exception.printStackTrace();
                    sender.sendMessage("An error occurred getting the charge from the tool.");
                }

                if (itemStack.getItemMeta().hasCustomModelData()) {
                    sender.sendMessage("Current custom model data: " + itemStack.getItemMeta().getCustomModelData());
                } else {
                    sender.sendMessage("This item does not have custom model data.");
                }
                return true;
            }
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
