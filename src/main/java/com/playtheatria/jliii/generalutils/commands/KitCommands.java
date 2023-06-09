package com.playtheatria.jliii.generalutils.commands;

import com.playtheatria.jliii.generalutils.items.ItemCreator;
import com.playtheatria.jliii.generalutils.utils.CustomLogger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class KitCommands implements CommandExecutor {

    private CustomLogger logger;
    private final String COMMON = "common";
    private final String UNCOMMON = "uncommon";
    private final String SUPER = "super";
    private final String EPIC = "epic";
    private final String ULTRA = "ultra";
    private final String EXCAVATOR = "excavator";
    private final String SUN_FISH = "sunfish";
    private final String NIGHT_FISH = "nightfish";
    private final String ETHEREAL_FRAGMENT = "etherealfragment";
    private final String CHRISTMAS_PICK = "christmaspick";
    private final String GINGERBREAD_MAN = "gingerbreadman";
    private final String TITAN_PICK_RED_FORTUNE = "titanpickredfortune";
    private final String TITAN_PICK_RED_SILK = "titanpickredsilk";
    private final String TITAN_PICK_YELLOW_FORTUNE = "titanpickyellowfortune";
    private final String TITAN_PICK_YELLOW_SILK = "titanpickyellowsilk";
    private final String TITAN_PICK_BLUE_FORTUNE = "titanpickbluefortune";
    private final String TITAN_PICK_BLUE_SILK = "titanpickbluesilk";
    private final String TITAN_SHOVEL_RED = "titanshovelred";
    private final String TITAN_AXE_RED = "titanaxered";
    private final String TITAN_AXE_YELLOW = "titanaxeyellow";
    private final String TITAN_AXE_BLUE = "titanaxeblue";
    private final String TITAN_SWORD_RED = "titanswordred";
    private final String TITAN_SWORD_YELLOW = "titanswordyellow";
    private final String TITAN_SWORD_BLUE = "titanswordblue";
    private final String TITAN_ROD_RED = "titanrodred";


    //TODO this needs to be implemented.
    private final String CASINO_KEY = "casinokey";
    private String PLAYER_NAME;

    public KitCommands(CustomLogger logger) {
        this.logger = logger;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender.hasPermission("titan.kit.give")) {
            if ("kit".equalsIgnoreCase(args[0]) && args.length == 3) {
                if (Bukkit.getPlayer(args[2]) != null) {
                    Player player = Bukkit.getPlayer(args[2]);
                    if (player == null) return false;
                    PLAYER_NAME = player.getName();
                    Inventory inventory = player.getInventory();
                    switch (args[1]) {
                        case COMMON -> reportResult(COMMON, inventory.addItem(ItemCreator.powerCrystalCommon));
                        case UNCOMMON -> reportResult(UNCOMMON, inventory.addItem(ItemCreator.powerCrystalUncommon));
                        case SUPER -> reportResult(SUPER, inventory.addItem(ItemCreator.powerCrystalSuper));
                        case EPIC -> reportResult(EPIC, inventory.addItem(ItemCreator.powerCrystalEpic));
                        case ULTRA -> reportResult(ULTRA, inventory.addItem(ItemCreator.powerCrystalUltra));
                        case EXCAVATOR -> reportResult(EXCAVATOR, inventory.addItem(ItemCreator.excavator));
                        case SUN_FISH -> reportResult(SUN_FISH, inventory.addItem(ItemCreator.sunFish));
                        case NIGHT_FISH -> reportResult(NIGHT_FISH, inventory.addItem(ItemCreator.nightFish));
                        case ETHEREAL_FRAGMENT -> reportResult(ETHEREAL_FRAGMENT, inventory.addItem(ItemCreator.etherealFragment));
                        case CHRISTMAS_PICK -> reportResult(CHRISTMAS_PICK, inventory.addItem(ItemCreator.christmasPick));
                        case GINGERBREAD_MAN -> reportResult(GINGERBREAD_MAN, inventory.addItem(ItemCreator.gingerbreadMan));
                        case TITAN_PICK_RED_FORTUNE -> reportResult(TITAN_PICK_RED_FORTUNE, inventory.addItem(ItemCreator.titanPickRedFortune));
                        case TITAN_PICK_RED_SILK -> reportResult(TITAN_PICK_RED_SILK, inventory.addItem(ItemCreator.titanPickRedSilk));
                        case TITAN_PICK_YELLOW_FORTUNE -> reportResult(TITAN_PICK_YELLOW_FORTUNE, inventory.addItem(ItemCreator.titanPickYellowFortune));
                        case TITAN_PICK_YELLOW_SILK -> reportResult(TITAN_PICK_YELLOW_SILK, inventory.addItem(ItemCreator.titanPickYellowSilk));
                        case TITAN_PICK_BLUE_FORTUNE -> reportResult(TITAN_PICK_BLUE_FORTUNE,  inventory.addItem(ItemCreator.titanPickBlueFortune));
                        case TITAN_PICK_BLUE_SILK -> reportResult(TITAN_PICK_BLUE_SILK,  inventory.addItem(ItemCreator.titanPickBlueSilk));
                        case TITAN_SHOVEL_RED -> reportResult(TITAN_SHOVEL_RED, inventory.addItem(ItemCreator.titanShovelRed));
                        case TITAN_AXE_RED -> reportResult(TITAN_AXE_RED, inventory.addItem(ItemCreator.titanAxeRed));
                        case TITAN_AXE_YELLOW -> reportResult(TITAN_AXE_YELLOW, inventory.addItem(ItemCreator.titanAxeYellow));
                        case TITAN_AXE_BLUE -> reportResult(TITAN_AXE_BLUE, inventory.addItem(ItemCreator.titanAxeBlue));
                        case TITAN_SWORD_RED -> reportResult(TITAN_SWORD_RED, inventory.addItem(ItemCreator.titanSwordRed));
                        case TITAN_SWORD_YELLOW -> reportResult(TITAN_SWORD_YELLOW, inventory.addItem(ItemCreator.titanSwordYellow));
                        case TITAN_SWORD_BLUE -> reportResult(TITAN_SWORD_BLUE, inventory.addItem(ItemCreator.titanSwordBlue));
                        case TITAN_ROD_RED -> reportResult(TITAN_ROD_RED, inventory.addItem(ItemCreator.titanRodRed));
                        default -> sender.sendMessage(ChatColor.DARK_RED + "This kit does not exist");
                    }
                }
            }
        }
        return false;
    }

    private void reportResult(String item, HashMap<Integer, ItemStack> droppedItems) {
        if (droppedItems.isEmpty()) {
            logger.sendLog(PLAYER_NAME + " received their " + item);
        } else {
            logger.sendLog(PLAYER_NAME + " did not receive their " + item + " due to a full inventory.");
        }
    }

}
