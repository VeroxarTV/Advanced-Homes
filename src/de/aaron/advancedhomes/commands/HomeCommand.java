package de.aaron.advancedhomes.commands;

import de.aaron.advancedhomes.main.AdvancedHomes;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class HomeCommand implements CommandExecutor {

    FileConfiguration config = AdvancedHomes.getPlugin().getConfig();

    private static int delayID;
    public static int waitID;
    public static HashMap<UUID, Integer> teleportingPlayers = new HashMap<>();
    public static HashMap<UUID, Integer> teleportingPlayersDelay = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String labels, String[] args) {

        if (cmd.getName().equalsIgnoreCase("home")) {

            if (!(sender instanceof Player)) {

                sender.sendMessage(AdvancedHomes.getOnlyplayer());

            } else {

                Player p = (Player) sender;

                if (!(args.length == 1)) {

                    p.sendMessage(AdvancedHomes.getHelp());

                } else if (config.get(p.getName() + "." + args[0].toLowerCase()) == null) {

                    p.sendMessage(AdvancedHomes.getPrefix() + "§cDieses Home existiert nicht!");

                } else if (!(teleportingPlayersDelay.containsKey(p.getUniqueId()))) {

                    World world = Bukkit.getWorld(config.getString(p.getName() + "." + args[0].toLowerCase() + ".World"));
                    double x = config.getDouble(p.getName() + "." + args[0].toLowerCase() + ".X");
                    double y = config.getDouble(p.getName() + "." + args[0].toLowerCase() + ".Y");
                    double z = config.getDouble(p.getName() + "." + args[0].toLowerCase() + ".Z");
                    float yaw = (float) config.getDouble(p.getName() + "." + args[0].toLowerCase() + ".Yaw");
                    float pitch = (float) config.getDouble(p.getName() + "." + args[0].toLowerCase() + ".Pitch");

                    Location home = new Location(world, x, y, z, yaw, pitch);

                    p.sendMessage(AdvancedHomes.getPrefix() + "§3Du wirst in 3 Sekunden teleportiert!");

                    teleportingPlayersDelay.put(p.getUniqueId(), delayID);
                    teleportingPlayers.put(p.getUniqueId(), HomeCommand.waitID);


                    waitID = Bukkit.getScheduler().scheduleSyncDelayedTask(AdvancedHomes.getPlugin(), () -> {

                        if (teleportingPlayers.containsKey(p.getUniqueId())) {

                            p.teleport(home);
                            teleportingPlayers.remove(p.getUniqueId());
                        }

                        Bukkit.getScheduler().cancelTask(waitID);

                    }, 20 * 3);

                    delayID = Bukkit.getScheduler().scheduleSyncDelayedTask(AdvancedHomes.getPlugin(), () -> {

                            teleportingPlayersDelay.remove(p.getUniqueId());
                            Bukkit.getScheduler().cancelTask(delayID);

                    },20*5);

                } else
                    p.sendMessage(AdvancedHomes.getPrefix() + "§cBitte warte ca. 5 Sekunden!");

            }

        }


        return true;
    }
}
