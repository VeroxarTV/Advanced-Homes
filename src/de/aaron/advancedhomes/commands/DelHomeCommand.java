package de.aaron.advancedhomes.commands;

import de.aaron.advancedhomes.main.AdvancedHomes;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.Set;

public class DelHomeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String labels, String[] args) {

        if (cmd.getName().equalsIgnoreCase("delhome")) {

            if (!(sender instanceof Player)) {

                sender.sendMessage(AdvancedHomes.getOnlyplayer());

            } else {

                Player p = (Player) sender;
                FileConfiguration config = AdvancedHomes.getPlugin().getConfig();

                if (!(args.length == 1)) {

                    p.sendMessage(AdvancedHomes.getHelp());

                } else if (!(SetHomeCommand.homes.size() > 0)) {

                    p.sendMessage(AdvancedHomes.getPrefix() + "§aDu besitzt noch kein Home," + " §6/sethome [name] §aum es zu setzen!");

                } else if (!(config.contains(p.getName() +  "." + args[0]))) {

                    p.sendMessage(AdvancedHomes.getPrefix() + "§aDieses Home gibt es nicht," + " §6/sethome [name] §aum es zu setzen!");

                } else {

                    config.set(p.getName() + "." + args[0], null);
                    AdvancedHomes.getPlugin().saveConfig();

                    p.sendMessage(AdvancedHomes.getPrefix() + "§cDein Home wurde gelöscht!");

                    SetHomeCommand.homes.remove(args[0]);

                }
            }
        }


        return true;
    }
}
