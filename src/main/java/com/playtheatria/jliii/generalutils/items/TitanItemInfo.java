package com.playtheatria.jliii.generalutils.items;

import com.playtheatria.jliii.generalutils.enums.ToolColor;
import com.playtheatria.jliii.generalutils.enums.ToolStatus;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class TitanItemInfo {

//    New variables for second edition titan tools
    public static final String STATUS_PREFIX = "● Status";
    public static final String CHARGE_PREFIX = "● Charge";

    public static int getChargeLoreIndex(List<String> loreList) {
        for (int i = 0; i < loreList.size(); i++){
            if (loreList.get(i).contains(CHARGE_PREFIX)) return i;
        }
        return -1;
    }

    public String createStatus(ToolColor color, ToolStatus status) {
        return color.getDarkColorCode() + " " + STATUS_PREFIX + color.getBrightColorCode() + " " + status.getString();
    }

    public String createChargeLore(ToolColor color, int amount) {
        return color.getDarkColorCode() + "  " + CHARGE_PREFIX + color.getBrightColorCode() + " " + amount;
    }

//    New variables for second edition titan tools
    public static final String ANCIENT_POWER_STRING = "Ancient Power";
    public static final String ANCIENT_POWER_SYMBOL = "Ω";

    public static final String EXCAVATION_TOOL_DISPLAY_NAME = "§x§f§b§b§5§0§0§lE§x§f§6§a§7§1§3§lx§x§f§1§9§9§2§7§lc§x§e§c§8§b§3§a§la§x§e§7§7§d§4§e§lv§x§e§2§6§f§6§1§la§x§d§d§6§1§7§5§lt§x§d§9§5§4§8§8§li§x§d§4§4§6§9§c§lo§x§c§f§3§8§a§f§ln §x§c§a§2§a§c§3§lT§x§c§5§1§c§d§6§lo§x§c§0§0§e§e§a§lo§x§b§b§0§0§f§d§ll   ";

    public static final List<Material> ALLOWED_TITAN_TYPES = new ArrayList<>() {
        {
            add(Material.DIAMOND_PICKAXE);
            add(Material.NETHERITE_PICKAXE);
            add(Material.DIAMOND_SHOVEL);
            add(Material.NETHERITE_SHOVEL);
            add(Material.DIAMOND_AXE);
            add(Material.NETHERITE_AXE);
        }
    };

    public static final List<Material> ALLOWED_PICK_TYPES = new ArrayList<>(){
        {
            add(Material.DIAMOND_PICKAXE);
            add(Material.NETHERITE_PICKAXE);
        }
    };

    public static final List<Material> ALLOWED_AXE_TYPES = new ArrayList<>(){
        {
            add(Material.DIAMOND_AXE);
            add(Material.NETHERITE_AXE);
        }
    };

    public static final List<Material> ALLOWED_SHOVEL_TYPES = new ArrayList<>(){
        {
            add(Material.DIAMOND_SHOVEL);
            add(Material.NETHERITE_SHOVEL);
        }
    };

    //TODO this method now needs to check for a value in the charge lore string
    public static boolean hasCharge(ItemStack item) {
        for (String lore : getLore(item)) {
            if (lore.contains(CHARGE_PREFIX)) return true;
        }
        return false;
    }

    public static boolean isTitanTool(ItemStack item){
        for (String lore : getLore(item)) {
            if (lore.contains(ANCIENT_POWER_STRING) && lore.contains(ANCIENT_POWER_SYMBOL)) return true;
        }
        return false;
    }

    public static boolean isChargedTitanTool(ItemStack item) {
        if (!isTitanTool(item)) return false;
        for (String lore : getLore(item)) {
            if (lore.contains(CHARGE_PREFIX)) return true;
        }
        return false;
    }

    public static boolean isImbuedTitanTool(ItemStack item) {
        if (!isTitanTool(item)) return false;
        for (String lore : getLore(item)) {
            if (lore.contains(CHARGE_PREFIX)) return false;
        }
        return true;
    }

    public static boolean isAllowedTitanType(ItemStack item){
        if (item == null) return false;
        return (ALLOWED_TITAN_TYPES.contains(item.getType()));
    }

    public static boolean isAllowedPickType(ItemStack item){
        if (item == null) return false;
        return (ALLOWED_PICK_TYPES.contains(item.getType()));
    }

    public static boolean isAllowedAxeType(ItemStack item){
        if (item == null) return false;
        return (ALLOWED_AXE_TYPES.contains(item.getType()));
    }

    public static boolean isAllowedShovelType(ItemStack item){
        if (item == null) return false;
        return (ALLOWED_SHOVEL_TYPES.contains(item.getType()));
    }

    public static boolean checkLore(ItemStack item, List<String> LORE){
        for (String toolLore : getLore(item)) {
            if (LORE.contains(toolLore)) {
                return true;
            }
        }
        return false;
    }

    public static List<String> getLore(ItemStack item) {
        if (item.hasItemMeta() && item.getItemMeta().hasLore()) {
            return item.getItemMeta().getLore();
        }
        return new ArrayList<>();
    }

    public static boolean setLore(ItemStack item, List<String> loreList) {
        if (item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            meta.setLore(loreList);
            item.setItemMeta(meta);
            return true;
        }
        return false;
    }

    /**
     * Retrieves the color from the item's lore.
     * <p>
     * This method iterates through the lore of the provided item and returns the corresponding
     * ToolColor if found. If no color is found, ToolColor.NONE is returned as the default value.
     * The caller should take into account the possibility of a ToolColor.NONE return value
     * and validate or handle it accordingly.
     * </p>
     *
     * @param item the ItemStack from which to retrieve the color
     * @return the corresponding ToolColor enum value, or ToolColor.NONE if no color is found
     * @see ToolColor ToolColor for the defined color codes
     */
    public static ToolColor getColor(ItemStack item){
        for (String lore : getLore(item)) {
            if (lore.contains(ToolColor.RED.getBrightColorCode())) {
                return ToolColor.RED;
            } else if (lore.contains(ToolColor.YELLOW.getBrightColorCode())) {
                return ToolColor.YELLOW;
            } else if (lore.contains(ToolColor.BLUE.getBrightColorCode())) {
                return ToolColor.BLUE;
            }
        }
        return ToolColor.NONE;
    }

    public static boolean isTitanPick(ItemStack item) {
        return isTitanTool(item)
                && isAllowedPickType(item);
    }

    public static boolean isTitanAxe(ItemStack item) {
        return isTitanTool(item)
                && isAllowedAxeType(item);
    }

    public static boolean isTitanShovel(ItemStack item) {
        return isTitanTool(item) && isAllowedShovelType(item);
    }

    public static boolean isChargedOrImbuedTitanPick(ItemStack item) {
        if(!isTitanPick(item)) return false;
        return (isImbuedTitanTool(item) || isChargedTitanTool(item));
    }

    public static boolean isChargedOrImbuedTitanAxe(ItemStack item) {
        if(!isTitanAxe(item)) return false;
        return (isImbuedTitanTool(item) || isChargedTitanTool(item));
    }

    public static boolean isChargedOrImbuedTitanShovel(ItemStack item) {
        if(!isTitanShovel(item)) return false;
        return (isImbuedTitanTool(item) || isChargedTitanTool(item));
    }
}
