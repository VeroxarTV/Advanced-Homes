package de.aaron.advancedhomes.commands;

import de.aaron.advancedhomes.main.AdvancedHomes;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class SetHomeCommand implements CommandExecutor {

    FileConfiguration config = AdvancedHomes.getPlugin().getConfig();
    public static ArrayList<String> homes = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String labels, String[] args) {

        if (cmd.getName().equalsIgnoreCase("sethome")) {

            if (!(sender instanceof Player)) {

                sender.sendMessage(AdvancedHomes.getOnlyplayer());

            } else {

                Player p = (Player) sender;

                if (!(args.length == 1)) {

                    p.sendMessage(AdvancedHomes.getHelp());

                    return true;

                } else if (p.hasPermission("AdHo.Homes.*")) {

                    homes.add(args[0]);

                    config.set(p.getName() + ".Homes", homes);
                    config.set(p.getName() + "." + args[0] + ".World", p.getWorld().getName());
                    config.set(p.getName() + "." + args[0] + ".X", p.getLocation().getX());
                    config.set(p.getName() + "." + args[0] + ".Y", p.getLocation().getY());
                    config.set(p.getName() + "." + args[0] + ".Z", p.getLocation().getZ());
                    config.set(p.getName() + "." + args[0] + ".Yaw", p.getLocation().getYaw());
                    config.set(p.getName() + "." + args[0] + ".Pitch", p.getLocation().getPitch());
                    AdvancedHomes.getPlugin().saveConfig();

                    p.sendMessage(AdvancedHomes.getPrefix() + "§aDein Home wurde gesetzt!");

                } else if (!(config.contains(p.getName()))){

                    homes.add(args[0]);

                    config.set(p.getName() + ".Homes", homes);
                    config.set(p.getName() + "." + args[0] + ".World", p.getWorld().getName());
                    config.set(p.getName() + "." + args[0] + ".X", p.getLocation().getX());
                    config.set(p.getName() + "." + args[0] + ".Y", p.getLocation().getY());
                    config.set(p.getName() + "." + args[0] + ".Z", p.getLocation().getZ());
                    config.set(p.getName() + "." + args[0] + ".Yaw", p.getLocation().getYaw());
                    config.set(p.getName() + "." + args[0] + ".Pitch", p.getLocation().getPitch());
                    AdvancedHomes.getPlugin().saveConfig();

                    p.sendMessage(AdvancedHomes.getPrefix() + "§aDein Home wurde gesetzt!");

                } else if (!(config.getList(p.getName() + ".Homes", homes).size() < 3)) {

                    p.sendMessage(AdvancedHomes.getPrefix() + "§cDu besitzt schon 3 Homes!");

                } else {

                    homes.add(args[0]);

                    config.set(p.getName() + ".Homes", homes);
                    config.set(p.getName() + "." + args[0] + ".World", p.getWorld().getName());
                    config.set(p.getName() + "." + args[0] + ".X", p.getLocation().getX());
                    config.set(p.getName() + "." + args[0] + ".Y", p.getLocation().getY());
                    config.set(p.getName() + "." + args[0] + ".Z", p.getLocation().getZ());
                    config.set(p.getName() + "." + args[0] + ".Yaw", p.getLocation().getYaw());
                    config.set(p.getName() + "." + args[0] + ".Pitch", p.getLocation().getPitch());
                    AdvancedHomes.getPlugin().saveConfig();

                    p.sendMessage(AdvancedHomes.getPrefix() + "§aDein Home wurde gesetzt!");

                }
            }
        }

        return true;
    }
}
