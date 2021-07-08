package me.lightingmax.fightpvp;

import me.lightingmax.fightpvp.ItemManager.ItemCreateArena;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        PluginManager pm = getServer().getPluginManager();
        ItemCreateArena.init();
        FileManager.setup();
        FileManager.get().options().copyDefaults(true);
        FileManager.save();
        pm.registerEvents(new EventManager(this), this);
        this.getCommand("thachdau").setExecutor(new CommandsPvP(this));
        getConfig().options().copyDefaults();
        saveDefaultConfig();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        saveDefaultConfig();
    }

}
