package me.lightingmax.fightpvp.CooldownManager;

import me.lightingmax.fightpvp.CommandsPvP;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class CooldownMethod extends BukkitRunnable {



    private int timeleft = 10;


    @Override
    public void run() {
        timeleft--;
        if(timeleft <= 0) {
            cancel();
            CommandsPvP.inwatingplayer1.remove(CommandsPvP.player1);
            CommandsPvP.inwatingplayer2.remove(CommandsPvP.player2);
            CommandsPvP.inplayingplayer2.add(CommandsPvP.player2);
            CommandsPvP.inplayingplayer1.add(CommandsPvP.player1);
            return;
        }
        CommandsPvP.player1.sendMessage(ChatColor.YELLOW + "Trận đấu bắt đầu trong " + ChatColor.GOLD + timeleft + ChatColor.YELLOW + "giây!");
        CommandsPvP.player2.sendMessage(ChatColor.YELLOW + "Trận đấu bắt đầu trong " + ChatColor.GOLD + timeleft + ChatColor.YELLOW + "giây!");
    }
}
