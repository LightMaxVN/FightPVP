package me.lightingmax.fightpvp.CooldownManager;



import me.lightingmax.fightpvp.CommandsPvP;
import me.lightingmax.fightpvp.GameControl.GameControlPlayer;
import me.lightingmax.fightpvp.GameControl.GameControlTimer;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class EndArenaBackTime extends BukkitRunnable {

    private int timeleft = 10;

    private GameControlTimer gameControlTimer;

    @Override
    public void run() {
        this.gameControlTimer = new GameControlTimer();
        if(this.gameControlTimer.isCancelled()) {
            timeleft--;
            if(timeleft == 0) {
                if(GameControlPlayer.WhoWonGame == CommandsPvP.player1) {
                    cancel();
                   GameControlPlayer.WhoWonGame.teleport(CommandsPvP.locationp1willtp);
                   GameControlPlayer.WhoWonGame.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Đã dịch chuyển bạn về!"));
                }else if(GameControlPlayer.WhoWonGame == CommandsPvP.player2) {
                    cancel();
                    GameControlPlayer.WhoWonGame.teleport(CommandsPvP.locationp2willtp);
                    GameControlPlayer.WhoWonGame.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6Đã dịch chuyển bạn về!"));
                }
            }
        }
    }
}
