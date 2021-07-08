package me.lightingmax.fightpvp;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class FileManager {

    private static File file;
    private static FileConfiguration customFile;

    public static void setup() {
        file = new File(Objects.requireNonNull(Bukkit.getServer().getPluginManager().getPlugin("FightPVP")).getDataFolder(), "ArenaDatabase.yml");

        if(!file.exists()) {
            try {
                file.createNewFile();
            }catch (IOException e){
                //owo
            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get() {
        return customFile;
    }

    public static void save() {
        try{
            customFile.save(file);
        }catch (IOException e){
            System.out.println("[FightPvP] Could not save the file!");
        }
    }

    public static void reload() {
        customFile = YamlConfiguration.loadConfiguration(file);
    }

}
