package me.lightingmax.fightpvp.CooldownManager;

import me.lightingmax.fightpvp.CommandsPvP;
import org.bukkit.scheduler.BukkitRunnable;

public class InviteTimeUpMethod extends BukkitRunnable {


    private int timeleft = 60;

    @Override
    public void run() {
        if (CommandsPvP.inplayingplayer2.contains(CommandsPvP.player2)) {
            cancel();
        } else {
            timeleft--;
            if (timeleft <= 0) {
                cancel();
                CommandsPvP.isgettinginvited.remove(CommandsPvP.player2);
                CommandsPvP.whocancel.remove(CommandsPvP.player1);
                CommandsPvP.player2.sendMessage("Lời mời thách đấu đến bạn đã hết hạn!");
            }
        }
    }
}
