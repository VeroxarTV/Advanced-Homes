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

                } else if (p.hasPermission("AdHo.Homes.9")) {

                    if (config.contains(p.getName() + "." + args[0].toLowerCase())) {

                        p.sendMessage(AdvancedHomes.getPrefix() + "§cDieses Home hast du bereits gesetzt!");

                    } else {

                        if (config.getInt(p.getName() + ".Homes", homes.size()) < 9) {

                            if (!(args[0].equalsIgnoreCase("*"))) {

                                homes.add(args[0].toLowerCase());

                                config.set(p.getName() + ".Homes", homes.size());
                                config.set(p.getName() + ".HomeNames", homes);
                                config.set(p.getName() + "." + args[0].toLowerCase() + ".World", p.getWorld().getName());
                                config.set(p.getName() + "." + args[0].toLowerCase() + ".X", p.getLocation().getX());
                                config.set(p.getName() + "." + args[0].toLowerCase() + ".Y", p.getLocation().getY());
                                config.set(p.getName() + "." + args[0].toLowerCase() + ".Z", p.getLocation().getZ());
                                config.set(p.getName() + "." + args[0].toLowerCase() + ".Yaw", p.getLocation().getYaw());
                                config.set(p.getName() + "." + args[0].toLowerCase() + ".Pitch", p.getLocation().getPitch());
                                AdvancedHomes.getPlugin().saveConfig();

                                homes.remove(args[0].toLowerCase());

                                p.sendMessage(AdvancedHomes.getPrefix() + "§aDein Home wurde gesetzt!");

                            } else
                                p.sendMessage(AdvancedHomes.getPrefix() + "§cDas ist ein Ungültiger Name!");

                        } else
                            p.sendMessage(AdvancedHomes.getPrefix() + "§cDu besitzt schon 9 Homes!");
                    }

                } else if (!(config.contains(p.getName()))){

                    if (!(args[0].equalsIgnoreCase("*"))) {

                        homes.add(args[0].toLowerCase());

                        config.set(p.getName() + ".Homes", homes.size());
                        config.set(p.getName() + ".HomeNames", homes);
                        config.set(p.getName() + "." + args[0].toLowerCase() + ".World", p.getWorld().getName());
                        config.set(p.getName() + "." + args[0].toLowerCase() + ".X", p.getLocation().getX());
                        config.set(p.getName() + "." + args[0].toLowerCase() + ".Y", p.getLocation().getY());
                        config.set(p.getName() + "." + args[0].toLowerCase() + ".Z", p.getLocation().getZ());
                        config.set(p.getName() + "." + args[0].toLowerCase() + ".Yaw", p.getLocation().getYaw());
                        config.set(p.getName() + "." + args[0].toLowerCase() + ".Pitch", p.getLocation().getPitch());
                        AdvancedHomes.getPlugin().saveConfig();

                        homes.remove(args[0].toLowerCase());

                        p.sendMessage(AdvancedHomes.getPrefix() + "§aDein Home wurde gesetzt!");

                    } else
                        p.sendMessage(AdvancedHomes.getPrefix() + "§cDas ist ein Ungültiger Name!");

                } else if (config.getInt(p.getName() + ".Homes", homes.size()) >= 3) {

                    if (config.contains(p.getName() + "." + args[0].toLowerCase())) {

                        p.sendMessage(AdvancedHomes.getPrefix() + "§cDieses Home hast du bereits gesetzt!");

                    } else
                    p.sendMessage(AdvancedHomes.getPrefix() + "§cDu besitzt schon 3 Homes!");

                } else {

                    if (config.contains(p.getName() + "." + args[0].toLowerCase())) {

                        p.sendMessage(AdvancedHomes.getPrefix() + "§cDieses Home hast du bereits gesetzt!");

                    } else {

                        if (!(args[0].equalsIgnoreCase("*"))) {

                            homes.add(args[0].toLowerCase());

                            config.set(p.getName() + ".Homes", homes.size());
                            config.set(p.getName() + ".HomeNames", homes);
                            config.set(p.getName() + "." + args[0].toLowerCase() + ".World", p.getWorld().getName());
                            config.set(p.getName() + "." + args[0].toLowerCase() + ".X", p.getLocation().getX());
                            config.set(p.getName() + "." + args[0].toLowerCase() + ".Y", p.getLocation().getY());
                            config.set(p.getName() + "." + args[0].toLowerCase() + ".Z", p.getLocation().getZ());
                            config.set(p.getName() + "." + args[0].toLowerCase() + ".Yaw", p.getLocation().getYaw());
                            config.set(p.getName() + "." + args[0].toLowerCase() + ".Pitch", p.getLocation().getPitch());
                            AdvancedHomes.getPlugin().saveConfig();

                            homes.remove(args[0].toLowerCase());

                            p.sendMessage(AdvancedHomes.getPrefix() + "§aDein Home wurde gesetzt!");

                        } else
                            p.sendMessage(AdvancedHomes.getPrefix() + "§cDas ist ein Ungültiger Name!");
                    }
                }
            }
        }

        return true;
    }
}
