package me.lightingmax.fightpvp.GameControl;

import me.lightingmax.fightpvp.CommandsPvP;
import me.lightingmax.fightpvp.CooldownManager.EndArenaBackTime;
import me.lightingmax.fightpvp.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class GameControlPlayer extends BukkitRunnable {


    private GameControlTimer gameControlTimer;

    public static Player WhoWonGame;

    private EndArenaBackTime endArenaBackTime;

    private int playerlist = 0;

    @Override
    public void run() {
        for(Player playercheck : Bukkit.getServer().getOnlinePlayers()) {
            if(playercheck.getWorld().getName().equals(FileManager.get().getString("ArenaList." + CommandsPvP.ChoseMap + ".World")) && CommandsPvP.inwatingplayer1.contains(playercheck) || CommandsPvP.inplayingplayer2.contains(playercheck)) {
                playerlist = playerlist + 1;
            }
        }
        if((playerlist % 2) != 0) {
            CommandsPvP.MapIsRunning.remove(CommandsPvP.ChoseMap);
            for(Player getplayername : Bukkit.getServer().getOnlinePlayers()) {
                if(getplayername.getWorld().getName().equals("ArenaList." + CommandsPvP.ChoseMap + ".World")) {
                    if(CommandsPvP.inplayingplayer1.contains(getplayername)) {
                        //give the prize for player1 and end arena!!!!
                        cancel();
                        this.gameControlTimer = new GameControlTimer();
                        this.gameControlTimer.cancel();
                        this.endArenaBackTime = new EndArenaBackTime();
                        this.endArenaBackTime.runTaskTimer((Plugin) this, 0, 20);
                        CommandsPvP.isArenaIsActive = false;
                        WhoWonGame = CommandsPvP.player1;
                        CommandsPvP.player1.sendMessage("Chúc mừng bạn đã thắng cuộc thách đấu này");
                        CommandsPvP.inplayingplayer1.remove(CommandsPvP.player1);
                        CommandsPvP.inplayingplayer2.remove(CommandsPvP.player2);
                        CommandsPvP.player2.sendMessage("Bạn đã thua khi thách đấu với " + CommandsPvP.player1.getName() + "! Hãy cố gắng lần sau nhé :v");

                    }else if(CommandsPvP.inplayingplayer2.contains(getplayername)) {
                        //give the prize for player2 and end arena!!!!!
                        cancel();
                        WhoWonGame = CommandsPvP.player2;
                        this.gameControlTimer = new GameControlTimer();
                        this.gameControlTimer.cancel();
                        this.endArenaBackTime = new EndArenaBackTime();
                        CommandsPvP.isArenaIsActive = false;
                        this.endArenaBackTime.runTaskTimer((Plugin) this, 0, 20);
                        CommandsPvP.player2.sendMessage("Chúc mừng bạn đã thắng cuộc thách đấu này");
                        CommandsPvP.inplayingplayer1.remove(CommandsPvP.player1);
                        CommandsPvP.inplayingplayer2.remove(CommandsPvP.player2);
                        CommandsPvP.player1.sendMessage("Bạn đã thua khi thách đấu với " + CommandsPvP.player2.getName() + "! Hãy cố gắng lần sau nhé :v");

                    }
                 }
            }

        }
    }
}
