package me.lightingmax.fightpvp.GameControl;

import me.lightingmax.fightpvp.CommandsPvP;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class GameControlTimer extends BukkitRunnable {

    private int timeleft = 300;

    private GameControlPlayer gameControlPlayer;

    @Override
    public void run() {
        timeleft--;
        if(timeleft == 0) {
            cancel();
            this.gameControlPlayer = new GameControlPlayer();
            this.gameControlPlayer.cancel();
            CommandsPvP.isArenaIsActive = false;
            CommandsPvP.inplayingplayer1.remove(CommandsPvP.player1);
            CommandsPvP.inplayingplayer2.remove(CommandsPvP.player2);
            Bukkit.broadcastMessage("Trận đấu giữa " + CommandsPvP.player1.getName() + " và " + CommandsPvP.player2 + " đã hòa!");
        }
    }

}
