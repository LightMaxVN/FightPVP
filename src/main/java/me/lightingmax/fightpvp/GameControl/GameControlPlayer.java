package me.lightingmax.fightpvp.GameControl;

import me.lightingmax.fightpvp.CommandsPvP;
import me.lightingmax.fightpvp.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class GameControlPlayer extends BukkitRunnable {


    private GameControlTimer gameControlTimer;

    @Override
    public void run() {
        int playerlist = 0;
        for(Player playercheck : Bukkit.getServer().getOnlinePlayers()) {
            if(playercheck.getWorld().getName().equals(FileManager.get().getString("ArenaList." + CommandsPvP.ChoseMap + ".World")) && CommandsPvP.inwatingplayer1.contains(playercheck) || CommandsPvP.inplayingplayer2.contains(playercheck)) {
                playerlist = playerlist + 1;
            }
        }
        if(playerlist == 1) {
            CommandsPvP.MapIsRunning.remove(CommandsPvP.ChoseMap);
            for(Player getplayername : Bukkit.getServer().getOnlinePlayers()) {
                if(getplayername.getWorld().getName().equals("ArenaList." + CommandsPvP.ChoseMap + ".World")) {
                    if(CommandsPvP.inplayingplayer1.contains(getplayername)) {
                        //give the prize for player1 and end arena!!!!
                        cancel();
                        this.gameControlTimer = new GameControlTimer();
                        this.gameControlTimer.cancel();
                        CommandsPvP.player1.sendMessage("Chúc mừng bạn đã thắng cuộc thách đấu này");
                        CommandsPvP.inplayingplayer1.remove(CommandsPvP.player1);
                        CommandsPvP.inplayingplayer2.remove(CommandsPvP.player2);
                        CommandsPvP.player2.sendMessage("Bạn đã thua khi thách đấu với " + CommandsPvP.player1.getName() + "! Hãy cố gắng lần sau nhé ;v");

                    }else if(CommandsPvP.inplayingplayer2.contains(getplayername)) {
                        //give the prize for player2 and end arena!!!!!
                        cancel();
                        this.gameControlTimer = new GameControlTimer();
                        this.gameControlTimer.cancel();
                        CommandsPvP.player2.sendMessage("Chúc mừng bạn đã thắng cuộc thách đấu này");
                        CommandsPvP.inplayingplayer1.remove(CommandsPvP.player1);
                        CommandsPvP.inplayingplayer2.remove(CommandsPvP.player2);
                        CommandsPvP.player1.sendMessage("Bạn đã thua khi thách đấu với " + CommandsPvP.player2.getName() + "! Hãy cố gắng lần sau nhé ;v");

                    }
                 }
            }

        }
    }
}