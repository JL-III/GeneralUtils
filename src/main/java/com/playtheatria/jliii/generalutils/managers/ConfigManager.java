package com.playtheatria.jliii.generalutils.managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class ConfigManager {
    private final Plugin plugin;
    private FileConfiguration fileConfiguration;
    private ItemStack titanPickRedFortune;
    private ItemStack titanPickRedSilk;
    private ItemStack titanPickYellowFortune;
    private ItemStack titanPickYellowSilk;
    private ItemStack titanPickBlueFortune;
    private ItemStack titanPickBlueSilk;
    private ItemStack titanShovelRed;
    private ItemStack titanAxeRed;
    private ItemStack titanAxeYellow;
    private ItemStack titanAxeBlue;
    private ItemStack titanSwordRed;
    private ItemStack titanSwordYellow;
    private ItemStack titanSwordBlue;
    private ItemStack titanRodRed;

    public ConfigManager(Plugin plugin) {
        this.plugin = plugin;
        fileConfiguration = plugin.getConfig();
        titanPickRedFortune = loadItemStack("titanpickredfortune");
        titanPickRedSilk = loadItemStack("titanpickredsilk");
        titanPickYellowFortune = loadItemStack("titanpickyellowfortune");
        titanPickYellowSilk = loadItemStack("titanpickyellowsilk");
        titanPickBlueFortune = loadItemStack("titanpickbluefortune");
        titanPickBlueSilk = loadItemStack("titanpickbluesilk");
        titanShovelRed = loadItemStack("titanshovelred");
        titanAxeRed = loadItemStack("titanaxered");
        titanAxeYellow = loadItemStack("titanaxeyellow");
        titanAxeBlue = loadItemStack("titanaxeblue");
        titanSwordRed = loadItemStack("titanswordred");
        titanSwordYellow = loadItemStack("titanswordyellow");
        titanSwordBlue = loadItemStack("titanswordblue");
        titanRodRed = loadItemStack("titanrodred");
    }

    public void reload() {
        plugin.reloadConfig();
        fileConfiguration = plugin.getConfig();
        titanPickRedFortune = loadItemStack("titanpickredfortune");
        titanPickRedSilk = loadItemStack("titanpickredsilk");
        titanPickYellowFortune = loadItemStack("titanpickyellowfortune");
        titanPickYellowSilk = loadItemStack("titanpickyellowsilk");
        titanPickBlueFortune = loadItemStack("titanpickbluefortune");
        titanPickBlueSilk = loadItemStack("titanpickbluesilk");
        titanShovelRed = loadItemStack("titanshovelred");
        titanAxeRed = loadItemStack("titanaxered");
        titanAxeYellow = loadItemStack("titanaxeyellow");
        titanAxeBlue = loadItemStack("titanaxeblue");
        titanSwordRed = loadItemStack("titanswordred");
        titanSwordYellow = loadItemStack("titanswordyellow");
        titanSwordBlue = loadItemStack("titanswordblue");
        titanRodRed = loadItemStack("titanrodred");
    }

    private ItemStack loadItemStack(String target) {
        ItemStack targetItemstack = (ItemStack) fileConfiguration.get(target);
        if (targetItemstack != null) {
            Bukkit.getConsoleSender().sendMessage("Loaded " + target + " from the config.");
        } else {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Error loading " + target + " from the config. Check to ensure an entry exists!");
        }
        return targetItemstack;
    }

    public ItemStack getTitanPickRedFortune() {
        return titanPickRedFortune;
    }

    public ItemStack getTitanPickRedSilk() {
        return titanPickRedSilk;
    }

    public ItemStack getTitanPickYellowFortune() {
        return titanPickYellowFortune;
    }

    public ItemStack getTitanPickYellowSilk() {
        return titanPickYellowSilk;
    }

    public ItemStack getTitanPickBlueFortune() {
        return titanPickBlueFortune;
    }

    public ItemStack getTitanPickBlueSilk() {
        return titanPickBlueSilk;
    }

    public ItemStack getTitanShovelRed() {
        return titanShovelRed;
    }

    public ItemStack getTitanAxeRed() {
        return titanAxeRed;
    }

    public ItemStack getTitanAxeYellow() {
        return titanAxeYellow;
    }

    public ItemStack getTitanAxeBlue() {
        return titanAxeBlue;
    }

    public ItemStack getTitanSwordRed() {
        return titanSwordRed;
    }

    public ItemStack getTitanSwordYellow() {
        return titanSwordYellow;
    }

    public ItemStack getTitanSwordBlue() {
        return titanSwordBlue;
    }

    public ItemStack getTitanRodRed() {
        return titanRodRed;
    }


}
