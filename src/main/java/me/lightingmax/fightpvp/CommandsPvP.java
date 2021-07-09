package me.lightingmax.fightpvp;

import me.lightingmax.fightpvp.CooldownManager.CooldownMethod;
import me.lightingmax.fightpvp.CooldownManager.InviteTimeUpMethod;
import me.lightingmax.fightpvp.GameControl.GameControlPlayer;
import me.lightingmax.fightpvp.GameControl.GameControlTimer;
import me.lightingmax.fightpvp.ItemManager.ItemCreateArena;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class CommandsPvP implements CommandExecutor {

    public static Player targetplayer;

    public static Player player1;
    public static Player player2;
    public static String ChoseMap;

    public static Location locationp1willtp;
    public static Location locationp2willtp;

    private CooldownMethod cooldownMethod;

    private InviteTimeUpMethod inviteTimeUpMethod;
    private GameControlPlayer gameControlPlayer;
    private GameControlTimer gameControlTimer;

    Main plugin;

    public static boolean isArenaIsActive = false;

    public static String ArenaName;

    public CommandsPvP(Main plugin) {
        this.plugin = plugin;
    }




    public static List<String> MapName = new ArrayList<>();


    public static ArrayList<String> MapIsRunning = new ArrayList<>();
    public static ArrayList<Player> whocancel = new ArrayList<>();
    public static ArrayList<Player> inwatingplayer1 = new ArrayList<>();
    public static ArrayList<Player> inwatingplayer2 = new ArrayList<>();
    public static ArrayList<Player> isgettinginvited = new ArrayList<>();
    public static ArrayList<Player> inplayingplayer1 = new ArrayList<>();
    public static ArrayList<Player> inplayingplayer2 = new ArrayList<>();


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("ConsoleReply"))));
        } else {
            Player p = (Player) sender;
            if(cmd.getName().equalsIgnoreCase("thachdau")) {
                if(args.length == 1 && args[0].equalsIgnoreCase("cancel") && whocancel.contains(player1)) {
                    isgettinginvited.remove(player2);
                    whocancel.remove(player1);
                    player1.sendMessage("Bạn đang hủy thành công cuộc giao kèo");
                    return true;
                }else{
                    p.sendMessage("Bạn không gửi bất kì lời mời thách đấu nào cả!");
                }

                if(args.length == 1 && args[0].equalsIgnoreCase("deny") && isgettinginvited.contains(player2)) {
                    whocancel.remove(player1);
                    isgettinginvited.remove(player2);
                    player2.sendMessage("Bạn đã từ chối cuộc thách đấu của {player}".replace("{player}", player1.getName()));
                    return true;
                }else{
                    p.sendMessage("Bạn không nhận được bất kì lời thách đấu nào cả!");
                }

                if (args.length == 1 && args[0].equalsIgnoreCase("accept")) {
                    if(isgettinginvited.contains(p)) {
                        whocancel.remove(player1);
                        isgettinginvited.remove(player2);
                        int index = new Random().nextInt(MapName.size());
                        ChoseMap = MapName.get(index);
                        inwatingplayer1.add(player1);
                        inwatingplayer1.add(player2);

                        World world = Bukkit.getWorld(Objects.requireNonNull(FileManager.get().getString("ArenaList." + ChoseMap + ".World")));

                        double loc1 = FileManager.get().getDouble("ArenaList." + ChoseMap + ".Player1" + ".x");
                        double loc2 = FileManager.get().getDouble("ArenaList." + ChoseMap + ".Player1" + ".y");
                        double loc3 = FileManager.get().getDouble("ArenaList." + ChoseMap + ".Player1" + ".z");


                        double loc4 = FileManager.get().getDouble("ArenaList." + ChoseMap + ".Player2" + ".x");
                        double loc5 = FileManager.get().getDouble("ArenaList." + ChoseMap + ".Player2" + ".y");
                        double loc6 = FileManager.get().getDouble("ArenaList." + ChoseMap + ".Player2" + ".z");


                        float yaw1 = (float) FileManager.get().getDouble("ArenaList." + ChoseMap + ".Player1" + ".Yaw");
                        float yaw2 = (float) FileManager.get().getDouble("ArenaList." + ChoseMap + ".Player2" + ".Yaw");

                        float pitch1 = (float) FileManager.get().getDouble("ArenaList." + ChoseMap + ".Player1" + ".Pitch");
                        float pitch2 = (float) FileManager.get().getDouble("ArenaList." + ChoseMap + ".Player2" + ".Pitch");


                        World worldp1now = player1.getWorld();
                        World worldp2now = player2.getWorld();
                        Location locationplayer1 = player1.getLocation();
                        Location locationplayer2 = player2.getLocation();

                        float yawp1 = player1.getLocation().getYaw();
                        float yawp2 = player2.getLocation().getYaw();

                        float pitchp1 = player1.getLocation().getPitch();
                        float pitchp2 = player2.getLocation().getPitch();

                        locationp1willtp = new Location(worldp1now, locationplayer1.getX(), locationplayer1.getY(), locationplayer1.getZ(), yawp1, pitchp1);
                        locationp2willtp = new Location(worldp2now, locationplayer2.getX(), locationplayer2.getY(), locationplayer2.getZ(), yawp2, pitchp2);

                        Location locp1 = new Location(world, loc1, loc2, loc3, yaw1, pitch1);
                        Location locp2 = new Location(world, loc4, loc5, loc6, yaw2, pitch2);

                        player1.teleport(locp1);
                        player2.teleport(locp2);

                        isArenaIsActive = true;
                        MapIsRunning.add(ChoseMap);

                        this.cooldownMethod = new CooldownMethod();
                        this.cooldownMethod.runTaskTimer(plugin, 0, 20);

                        this.gameControlPlayer = new GameControlPlayer();
                        this.gameControlPlayer.runTaskTimer(plugin, 0, 20);

                        this.gameControlTimer = new GameControlTimer();
                        this.gameControlTimer.runTaskTimer(plugin, 0, 20);

                        return true;
                    } else {
                        p.sendMessage(Objects.requireNonNull(plugin.getConfig().getString("DontHaveInvite")));
                    }
                    return true;
                }


                if (args.length == 2 && args[0].equalsIgnoreCase("invite") && args[1].length() >= 3) {
                    targetplayer = Bukkit.getPlayer(args[1]);
                    if (!isArenaIsActive) {
                        if (targetplayer == null) {
                            p.sendMessage(Objects.requireNonNull(plugin.getConfig().getString("OfflineOrNotExistPlayer")));
                            return true;
                        } else if (targetplayer == p) {
                            p.sendMessage(Objects.requireNonNull(plugin.getConfig().getString("SelfInvite")));
                            return true;
                        }
                        player1 = p;
                        player2 = targetplayer;
                        isgettinginvited.add(player2);
                        whocancel.add(p);
                        p.sendMessage(Objects.requireNonNull(plugin.getConfig().getString("SendedInvite")).replace("{targetplayer}", player2.getDisplayName()));
                        targetplayer.sendMessage("Người chơi {player} đã gửi cho bạn lời mời thách đấu. Bạn có 60 giây để trả lời!!".replace("{player}", player1.getDisplayName()));
                        this.inviteTimeUpMethod = new InviteTimeUpMethod();
                        this.inviteTimeUpMethod.runTaskTimer(plugin, 0, 20);
                        return true;
                    } else {
                        p.sendMessage("Có một trận đấu đang diễn ra đợi đi nào");
                    }
                    return true;
                }


                if (args.length >= 2 && args[0].equalsIgnoreCase("remove")) {
                    if (p.hasPermission("thachdau.remove")) {
                        if (args[1].length() > 3) {
                            if (FileManager.get().isConfigurationSection("ArenaList." + args[1])) {
                                FileManager.get().set("ArenaList." + args[1], null);
                                MapName.remove(args[1]);
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Removed")).replace("{arenaname}", args[1])));
                                return true;
                            } else {
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("NotAnExistArena"))));
                            }
                            return true;
                        } else {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("MissAgrument"))));
                        }
                        return true;
                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("NoPermission"))));
                    }
                }

                if (args.length == 2 && args[0].equalsIgnoreCase("admin") && args[1].equalsIgnoreCase("help")) {
                    for (String msg : plugin.getConfig().getStringList("Help-Admin")) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
                    }
                    return true;
                }


                if (args.length == 0) {
                    for (String msg : plugin.getConfig().getStringList("Help")) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
                    }
                    return true;
                }


                if (args[0].equalsIgnoreCase("help")) {
                    for (String msg : plugin.getConfig().getStringList("Help")) {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
                    }
                    return true;
                }


                if (args[0].equalsIgnoreCase("reload") && p.hasPermission("thachdau.reload")) {
                    plugin.reloadConfig();
                    plugin.saveConfig();
                    FileManager.reload();
                    FileManager.save();
                    for(int i = 0; i <= MapName.size(); i++) {
                        if(!FileManager.get().isConfigurationSection("ArenaName." + MapName.get(i))) {
                            MapName.remove(MapName.get(i));
                        }
                    }
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("Reloaded"))));
                    return true;
                }
                if (args.length >= 2) {
                    if (args[0].equalsIgnoreCase("create")) {
                        if (args[1].length() > 3 && p.hasPermission("thachdau.create")) {
                            ArenaName = args[1];
                            p.getInventory().clear();
                            p.getInventory().setItem(0, ItemCreateArena.WorldSelector);
                            p.getInventory().setItem(3, ItemCreateArena.LocationP1);
                            p.getInventory().setItem(4, ItemCreateArena.LocationP2);
                            p.getInventory().setItem(8, ItemCreateArena.TurnOffEditMode);
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("CreatedArena"))).replace("{arenaname}", ArenaName));
                            return true;
                        } else {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("NoPermission"))));
                        }
                    } else {
                        if (p.hasPermission("thachdau.create")) {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("MissAgrument"))));
                        } else {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("NoPermission"))));
                        }
                    }
                }
            }

        }
        return true;
    }

/*    public boolean isInt(String number) {
        try{
            Integer.parseInt(number);
        }catch (Exception e) {
            return false;
        }
        return true;
    }*/
}
