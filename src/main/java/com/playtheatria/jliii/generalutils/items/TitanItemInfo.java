package com.playtheatria.jliii.generalutils.items;

import com.playtheatria.jliii.generalutils.enums.ToolColor;
import com.playtheatria.jliii.generalutils.enums.ToolStatus;
import com.playtheatria.jliii.generalutils.utils.Response;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class TitanItemInfo {

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
//    New variables for second edition titan tools
    public static final String STATUS_PREFIX = "● Status";
    public static final String CHARGE_PREFIX = "● Charge";
    public static final String INVALID_TOOL_CHECK = "Invalid call, This method is meant only for TitanTools!";

    public static Response<Integer> getTitanLoreIndex(@NotNull List<String> loreList, String PREFIX, Response<Boolean> isTitanToolResponse) {
        if (!isTitanToolResponse.isSuccess()) return Response.failure(INVALID_TOOL_CHECK);
        for (int i = 0; i < loreList.size(); i++){
            if (loreList.get(i).contains(PREFIX)) return Response.success(i);
        }
        return Response.failure("No " + PREFIX + " lore index was found.");
    }

    public static String getStatusLore(@NotNull ToolColor color, @NotNull ToolStatus status) {
        return color.getDarkColorCode() + " " + STATUS_PREFIX + color.getBrightColorCode() + " " + status.getString();
    }

    public static String getChargeLore(@NotNull ToolColor color, int amount) {
        return color.getDarkColorCode() + "  " + CHARGE_PREFIX + color.getBrightColorCode() + " " + amount;
    }

    /**
     * Gets the charge on a charged Titan tool or returns -1.
     * <p>
     *     This method iterates through a list of strings and checks if any match the CHARGE_PREFIX constant found in the TitanItemInfo class.
     *     Once found the method returns a parsed integer to the caller.
     * </p>
     * @param lore list of strings retrieved from the Titan tool
     * @param offset The offset of the substring since we are parsing the integer at the end of the string, the caller must handle any exception that is thrown.
     * @throws NumberFormatException This method makes an assumption that the end of the string will contain a number value if it contains the TitanItemInfo.CHARGE_PREFIX
     * @return Returns the Response of Integer on a Titan tool.
     * */

    public static Response<Integer> getCharge(@NotNull List<String> lore, Response<Boolean> isTitanToolResponse, Response<Boolean> hasChargeResponse, int offset) {
        if (!isTitanToolResponse.isSuccess()) return Response.failure(INVALID_TOOL_CHECK);
        if (!hasChargeResponse.isSuccess()) return Response.failure("This item is not a charged item, this is an error!");
        for (String string : lore) {
            if (string.contains(CHARGE_PREFIX)) {
                try {
                    return Response.success(Integer.parseInt(string.substring(offset)));
                } catch (NumberFormatException exception) {
                    return Response.failure("Could not parse integer from charge lore index");
                }
            }
        }
        return Response.failure("No charge lore was found in this set or lore.");
    }
//    New variables for second edition titan tools
    public static Response<Boolean> hasCharge(List<String> loreList, Response<Boolean> isTitanToolResponse) {
        if (!isTitanToolResponse.isSuccess()) return Response.failure(INVALID_TOOL_CHECK);
        for (String lore : loreList) {
            if (lore.contains(CHARGE_PREFIX)) return Response.success(true);
        }
        return Response.success(false);
    }

    public static Response<Boolean> isTitanTool(List<String> loreList){
        for (String lore : loreList) {
            if (lore.contains(ANCIENT_POWER_STRING) && lore.contains(ANCIENT_POWER_SYMBOL)) return Response.success(true);
        }
        return Response.success(false);
    }

    public static Response<Boolean> isChargedTitanTool(List<String> loreList, Response<Boolean> isTitanToolResponse) {
        if (!isTitanToolResponse.isSuccess()) return Response.failure(INVALID_TOOL_CHECK);
        for (String lore : loreList) {
            if (lore.contains(CHARGE_PREFIX)) return Response.success(true);
        }
        return Response.failure("Could not find charge lore on this Titan Tool! This is an error!");
    }

    public static Response<Boolean> isImbuedTitanTool(List<String> loreList, Response<Boolean> isTitanToolResponse) {
        if (!isTitanToolResponse.isSuccess()) return Response.failure(INVALID_TOOL_CHECK);
        for (String lore : loreList) {
            if (lore.contains(CHARGE_PREFIX)) return Response.success(false);
        }
        return Response.success(true);
    }

    /**
     * Checks to see if the item type matches the list of allowed types.
     * <p>
     *     This method checks if the ItemStack provided is a material that is within the List of Material Enum provided.
     *     Once found the method returns a parsed integer to the caller.
     * </p>
     * @param item The ItemStack being checked
     * @param ALLOWED_TYPES A list of Material Enums that are allowed.
     * @return Returns true if the ItemStack's material is within the ALLOWED_TYPES list.
     * */
    public static boolean isAllowedType(@NotNull ItemStack item, List<Material> ALLOWED_TYPES){
        return (ALLOWED_TYPES.contains(item.getType()));
    }

    public static boolean checkLore(ItemStack item, List<String> LORE){
        Response<List<String>> lore = getLore(item);
        if (!lore.isSuccess()) return false;
        for (String toolLore : lore.value()) {
            if (LORE.contains(toolLore)) {
                return true;
            }
        }
        return false;
    }

    public static Response<List<String>> getLore(@NotNull ItemStack item) {
        if (item.hasItemMeta() && item.getItemMeta().hasLore()) {
            return Response.success(item.getItemMeta().getLore());
        }
        return Response.failure("There is no lore on this item!");
    }

    public static boolean setLore(@NotNull ItemStack item, List<String> loreList) {
        if (item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            meta.setLore(loreList);
            item.setItemMeta(meta);
            return true;
        }
        return false;
    }

    /**
     * Retrieves the color from a String list of lore.
     * <p>
     * This method iterates through the provided list and returns the corresponding
     * ToolColor if found. If no color is found, a null value for ToolColor is returned along with a value for the Error message.
     * The caller should check the success of the method with Response.isSuccessful() before handling the object response since the ToolColor might be null.
     * </p>
     *
     * @param loreList A list of strings from which to retrieve the ToolColor Response
     * @return the corresponding Response wrapper value. If no color is found then an error message is provided in the error value.
     * @see Response Response<T> for the Response Object
     * @see ToolColor ToolColor for the enums
     */
    public static Response<ToolColor> getColor(List<String> loreList){
        for (String lore : loreList) {
            for (ToolColor toolColor : ToolColor.values()) {
                if (lore.contains(toolColor.getBrightColorCode())) {
                    return Response.success(toolColor);
                }
            }
        }
        return Response.failure("Could not match a color.");
    }

    /**
     * Retrieves the status of a Titan tool or ToolStatus.EMPTY
     * <p>
     *  This method iterates through the lore list and checks for a matching constant STATUS_PREFIX found in the TitanIteminfo class.
     *  If found, the method checks for a match against the string value of ToolStatus.OFF or ToolStatus.ON and returns the appropriate ToolStatus.
     *  If not found it returns ToolStatus.EMPTY
     * </p>
     * @param loreList A list of strings found on a TitanTool.
     * @return ToolStatus on the tool, the caller needs to account for the ToolStatus.EMPTY return case.
     * */
    public static Response<ToolStatus> getStatus(List<String> loreList, Response<Boolean> isTitanToolResponse) {
        if (!isTitanToolResponse.isSuccess()) Response.failure(INVALID_TOOL_CHECK);
        for (String lore : loreList) {
            if (lore.contains(STATUS_PREFIX)) {
                if (lore.contains(ToolStatus.OFF.getString())) {
                    return Response.success(ToolStatus.OFF);
                } else if (lore.contains(ToolStatus.ON.getString())) {
                    return Response.success(ToolStatus.ON);
                }
            }
        }
        return Response.failure("Could not retrieve a tool status.");
    }

    public static boolean isTitanType(ItemStack item, List<Material> ALLOWED_TYPES, Response<Boolean> isTitanToolResponse) {
        return isTitanToolResponse.isSuccess() && isAllowedType(item, ALLOWED_TYPES);
    }

}
