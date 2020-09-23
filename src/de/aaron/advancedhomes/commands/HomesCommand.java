package de.aaron.advancedhomes.commands;

import de.aaron.advancedhomes.main.AdvancedHomes;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;


public class HomesCommand implements CommandExecutor {

    FileConfiguration config = AdvancedHomes.getPlugin().getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String labels, String[] args) {

        if (cmd.getName().equalsIgnoreCase("homes")) {

            if (!(sender instanceof Player)) {

                sender.sendMessage(AdvancedHomes.getOnlyplayer());

            } else {

                Player p = (Player) sender;

                if (!(args.length == 0)) {

                    p.sendMessage(AdvancedHomes.getHelp());

                } else if (config.getInt(p.getName() + ".Homes", SetHomeCommand.homenames.size()) != 0 && config.get(p.getName()) != null) {

                    p.sendMessage("§aHomes§7 (§3" + config.getInt(p.getName() + ".Homes",
                            SetHomeCommand.homenames.size()) + "§7)§7 : §3"
                            + config.getList(p.getName() + ".HomeNames"));

                } else
                    p.sendMessage(AdvancedHomes.getPrefix() + "§cDu besitzt keine Homes!");


            }

        }

        return true;
    }
}
