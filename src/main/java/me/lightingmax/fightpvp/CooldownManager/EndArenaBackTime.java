package me.lightingmax.fightpvp.CooldownManager;



import org.bukkit.scheduler.BukkitRunnable;

public class EndArenaBackTime extends BukkitRunnable {

    private int timeleft = 10;

    @Override
    public void run() {
        timeleft--;
        if(timeleft == 0) {

        }
    }
}
