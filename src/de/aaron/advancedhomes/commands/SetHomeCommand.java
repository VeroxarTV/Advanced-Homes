package de.aaron.advancedhomes.commands;

import de.aaron.advancedhomes.main.AdvancedHomes;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class SetHomeCommand implements CommandExecutor {

    public static ArrayList<String> homes = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String labels, String[] args) {

        if (cmd.getName().equalsIgnoreCase("sethome")) {

            if (!(sender instanceof Player)) {

                sender.sendMessage(AdvancedHomes.getOnlyplayer());

            } else {

                Player p = (Player) sender;

                FileConfiguration config = AdvancedHomes.getPlugin().getConfig();


                if (!(p.hasPermission("AdvancedHomes.sethome.*"))) {

                    if (!(args.length == 1)) {

                        p.sendMessage(AdvancedHomes.getHelp());

                    } else {

                        if (!(homes.size() <= 3)) {

                            p.sendMessage(AdvancedHomes.getPrefix() + "§cDu darfst nur maximal 3 Homes besitzen!");

                        } else if (!(config.contains(p.getName() + "." + args[0]))) {

                            config.set(p.getName() + "." + args[0] + ".World", p.getWorld().getName());
                            config.set(p.getName() + "." + args[0] + ".X", p.getLocation().getX());
                            config.set(p.getName() + "." + args[0] + ".Y", p.getLocation().getY());
                            config.set(p.getName() + "." + args[0] + ".Z", p.getLocation().getZ());
                            config.set(p.getName() + "." + args[0] + ".Yaw", p.getLocation().getYaw());
                            config.set(p.getName() + "." + args[0] + ".Pitch", p.getLocation().getPitch());
                            AdvancedHomes.getPlugin().saveConfig();

                            p.sendMessage(AdvancedHomes.getPrefix() + "§aDein Home wurde gesetzt!");

                            homes.add(args[0]);

                        } else
                            p.sendMessage(AdvancedHomes.getPrefix() + "§cDieses Home hast du schon gesetzt, §6/home " + args[0] + " §cum dich zu teleportieren!");
                    }

                } else {

                    config.set(p.getName() + args[0] + "." + ".World", p.getWorld().getName());
                    config.set(p.getName() + args[0] + "." + ".X", p.getLocation().getX());
                    config.set(p.getName() + args[0] + "." + ".Y", p.getLocation().getY());
                    config.set(p.getName() + args[0] + "." + ".Z", p.getLocation().getZ());
                    config.set(p.getName() + args[0] + "." + ".Yaw", p.getLocation().getYaw());
                    config.set(p.getName() + args[0] + "." + ".Pitch", p.getLocation().getPitch());
                    AdvancedHomes.getPlugin().saveConfig();

                    p.sendMessage(AdvancedHomes.getPrefix() + "§aDein Home wurde gesetzt!");

                    homes.add(args[0]);

                }

            }
        }

        return true;
    }
}
