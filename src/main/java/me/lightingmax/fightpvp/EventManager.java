package me.lightingmax.fightpvp;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

public class EventManager implements Listener {

    Main plugin;

    public EventManager(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Action action = e.getAction();
        ItemStack item = p.getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        if(meta.getDisplayName().equals(ChatColor.AQUA + "World Selector")) {
            if(action.equals(Action.RIGHT_CLICK_AIR)) {
                World world = p.getWorld();
                FileManager.get().createSection("ArenaList." + CommandsPvP.ArenaName);
                FileManager.get().set("ArenaList." + CommandsPvP.ArenaName + ".World", world.getName());
                FileManager.save();
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("SuccessAddWorld"))).replace("{arenaname}", CommandsPvP.ArenaName).replace("{world}", world.getName()));
            }else{
                if(action.equals(Action.RIGHT_CLICK_BLOCK)) {
                    World world = p.getWorld();
                    FileManager.get().createSection("ArenaList." + CommandsPvP.ArenaName);
                    FileManager.get().set("ArenaList." + CommandsPvP.ArenaName + ".World", world.getName());
                    FileManager.save();
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("SuccessAddWorld"))).replace("{arenaname}", CommandsPvP.ArenaName).replace("{world}", world.getName()));
                }
            }
        }
        if(meta.getDisplayName().equals(ChatColor.GOLD + "Location Player 1")) {
            if(action.equals(Action.RIGHT_CLICK_AIR)) {
                Location l = p.getLocation();
                FileManager.get().set("ArenaList." + CommandsPvP.ArenaName + ".Player1" + ".x", l.getX());
                FileManager.get().set("ArenaList." + CommandsPvP.ArenaName + ".Player1" + ".y", l.getY());
                FileManager.get().set("ArenaList." + CommandsPvP.ArenaName + ".Player1" + ".z", l.getZ());
                FileManager.get().set("ArenaList." + CommandsPvP.ArenaName + ".Player1" + ".Yaw", l.getYaw());
                FileManager.get().set("ArenaList." + CommandsPvP.ArenaName + ".Player1" + ".Pitch", l.getPitch());
                FileManager.save();
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("SetLocation1"))));
            }else{
                if(action.equals(Action.RIGHT_CLICK_BLOCK)) {
                    Location l = p.getLocation();
                    FileManager.get().set("ArenaList." + CommandsPvP.ArenaName + ".Player1" + ".x", l.getX());
                    FileManager.get().set("ArenaList." + CommandsPvP.ArenaName + ".Player1" + ".y", l.getY());
                    FileManager.get().set("ArenaList." + CommandsPvP.ArenaName + ".Player1" + ".z", l.getZ());
                    FileManager.get().set("ArenaList." + CommandsPvP.ArenaName + ".Player1" + ".Yaw", l.getYaw());
                    FileManager.get().set("ArenaList." + CommandsPvP.ArenaName + ".Player1" + ".Pitch", l.getPitch());
                    FileManager.save();
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("SetLocation1"))));
                }
            }
        }
        if(meta.getDisplayName().equals(ChatColor.GOLD + "Location Player 2")) {
            if(action.equals(Action.RIGHT_CLICK_AIR)) {
                Location l = p.getLocation();
                FileManager.get().set("ArenaList." + CommandsPvP.ArenaName + ".Player2" + ".x", l.getX());
                FileManager.get().set("ArenaList." + CommandsPvP.ArenaName + ".Player2" + ".y", l.getY());
                FileManager.get().set("ArenaList." + CommandsPvP.ArenaName + ".Player2" + ".z", l.getZ());
                FileManager.get().set("ArenaList." + CommandsPvP.ArenaName + ".Player2" + ".Yaw", l.getYaw());
                FileManager.get().set("ArenaList." + CommandsPvP.ArenaName + ".Player2" + ".Pitch", l.getPitch());
                FileManager.save();
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("SetLocation2"))));
            }else{
                if(action.equals(Action.RIGHT_CLICK_BLOCK)) {
                    Location l = p.getLocation();
                    FileManager.get().set("ArenaList." + CommandsPvP.ArenaName + ".Player2" + ".x", l.getX());
                    FileManager.get().set("ArenaList." + CommandsPvP.ArenaName + ".Player2" + ".y", l.getY());
                    FileManager.get().set("ArenaList." + CommandsPvP.ArenaName + ".Player2" + ".z", l.getZ());
                    FileManager.get().set("ArenaList." + CommandsPvP.ArenaName + ".Player2" + ".Yaw", l.getYaw());
                    FileManager.get().set("ArenaList." + CommandsPvP.ArenaName + ".Player2" + ".Pitch", l.getPitch());
                    FileManager.save();
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("SetLocation2"))));
                }
            }
        }
        if(meta.getDisplayName().equals(ChatColor.RED + "Turn off Edit Mode")) {
            if (action.equals(Action.RIGHT_CLICK_AIR)) {
                    CommandsPvP.MapName.add(CommandsPvP.ArenaName);
                    p.getInventory().clear();
                    plugin.saveConfig();
            }else{
                if(action.equals(Action.RIGHT_CLICK_BLOCK)) {
                    p.getInventory().clear();
                    plugin.saveConfig();
                }
            }
        }

    }
    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        if(meta.getDisplayName().equals(ChatColor.RED + "Turn off Edit Mode")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onBreakBlock(BlockBreakEvent e) {
        Player p = e.getPlayer();
        ItemStack item = p.getInventory().getItemInMainHand();
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        if(meta.getDisplayName().equals(ChatColor.AQUA + "World Selector")) {
            e.setCancelled(true);
        }
        if(meta.getDisplayName().equals(ChatColor.GOLD + "Location Player 1")) {
            e.setCancelled(true);
        }
        if(meta.getDisplayName().equals(ChatColor.GOLD + "Location Player 2")) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPreparePvP(PlayerMoveEvent e) {
        if(CommandsPvP.inwatingplayer1.contains(CommandsPvP.player1)) {
            e.setCancelled(true);
        }
        if(CommandsPvP.inwatingplayer2.contains(CommandsPvP.player2)) {
            e.setCancelled(true);
        }
    }
}

