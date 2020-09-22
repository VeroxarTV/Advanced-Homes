package de.aaron.advancedhomes.commands;

import de.aaron.advancedhomes.main.AdvancedHomes;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class DelHomeCommand implements CommandExecutor {

    FileConfiguration config = AdvancedHomes.getPlugin().getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String labels, String[] args) {

        if (cmd.getName().equalsIgnoreCase("delhome")) {

            if(!(sender instanceof Player)) {

                sender.sendMessage(AdvancedHomes.getOnlyplayer());

            } else {

                Player p = (Player) sender;

                if (!(args.length == 1)) {

                    p.sendMessage(AdvancedHomes.getHelp());

                } else if (config.contains(p.getName() + "." + args[0])) {

                    SetHomeCommand.homes.remove(args[0]);

                    config.set(p.getName() + "." + args[0], null);
                    config.set(p.getName() + ".Homes", SetHomeCommand.homes);

                    AdvancedHomes.getPlugin().saveConfig();

                    p.sendMessage(AdvancedHomes.getPrefix() + "§aDein Home wurde gelöscht!");

                } else
                    p.sendMessage(AdvancedHomes.getPrefix() + "§cDieses Home gibt es nicht!");


            }

        }

        return true;
    }
}
