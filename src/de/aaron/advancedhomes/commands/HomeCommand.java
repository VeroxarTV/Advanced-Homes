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

    public static int taskID;
    public static HashMap<UUID, Integer> teleportingPlayers = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String labels, String[] args) {

        if (cmd.getName().equalsIgnoreCase("home")) {

            if (!(sender instanceof  Player)) {

                sender.sendMessage(AdvancedHomes.getOnlyplayer());

            } else {

                Player p = (Player) sender;

                if (!(args.length == 1)) {

                    p.sendMessage(AdvancedHomes.getHelp());

                } else if (config.get(p.getName() + "." + args[0].toLowerCase()) == null) {

                    p.sendMessage(AdvancedHomes.getPrefix() + "§cDieses Home existiert nicht!");

                } else {

                    World world = Bukkit.getWorld(config.getString(p.getName() + "." + args[0].toLowerCase() + ".World"));
                    double x = config.getDouble(p.getName() + "." + args[0].toLowerCase() + ".X");
                    double y = config.getDouble(p.getName() + "." + args[0].toLowerCase() + ".Y");
                    double z = config.getDouble(p.getName() + "." + args[0].toLowerCase() + ".Z");
                    float yaw = (float) config.getDouble(p.getName() + "." + args[0].toLowerCase() + ".Yaw");
                    float pitch = (float) config.getDouble(p.getName() + "." + args[0].toLowerCase() + ".Pitch");

                    Location home = new Location(world, x, y, z, yaw, pitch);

                    p.sendMessage(AdvancedHomes.getPrefix() + "§3Du wirst in 3 Sekunden teleportiert!");

                    teleportingPlayers.put(p.getUniqueId(), taskID);

                    taskID = Bukkit.getScheduler().scheduleSyncDelayedTask(AdvancedHomes.getPlugin(), () -> {

                        p.teleport(home);
                        teleportingPlayers.remove(p.getUniqueId());

                    }, 20*3);



                }

            }

        }


        return true;
    }
}
