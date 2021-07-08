package me.lightingmax.fightpvp.ItemManager;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemCreateArena {
    public static ItemStack WorldSelector;
    public static ItemStack LocationP1;
    public static ItemStack LocationP2;
    public static ItemStack TurnOffEditMode;

    public static void init() {
        createworldSeclector();
        createlocationp1();
        createlocationp2();
        createturnoffeditmode();
    }

    private static void createworldSeclector() {
        ItemStack itemSetWorld = new ItemStack(Material.BLAZE_ROD);
        ItemMeta meta = itemSetWorld.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.AQUA + "World Selector");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Right-click on the arena world to choose arena world");
        meta.setLore(lore);
        meta.setUnbreakable(true);
        itemSetWorld.setItemMeta(meta);
        WorldSelector = itemSetWorld;
    }
    private static void createlocationp1() {
        ItemStack itemlocation1 = new ItemStack(Material.BLAZE_ROD);
        ItemMeta meta = itemlocation1.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.GOLD + "Location Player 1");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Right-click to set the location of the player1 at your location");
        meta.setLore(lore);
        meta.setUnbreakable(true);
        itemlocation1.setItemMeta(meta);
        LocationP1 = itemlocation1;
    }
    private static void createlocationp2() {
        ItemStack itemlocation2 = new ItemStack(Material.BLAZE_ROD);
        ItemMeta meta = itemlocation2.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.GOLD + "Location Player 2");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Right-click to set the location of the player2 at your location");
        meta.setLore(lore);
        meta.setUnbreakable(true);
        itemlocation2.setItemMeta(meta);
        LocationP2 = itemlocation2;
    }

    private static void createturnoffeditmode() {
        ItemStack editmode = new ItemStack(Material.BARRIER);
        ItemMeta meta = editmode.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.RED + "Turn off Edit Mode");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + "Right-click to turn off edit mode");
        meta.setLore(lore);
        meta.setUnbreakable(true);
        editmode.setItemMeta(meta);
        TurnOffEditMode = editmode;
    }
}

