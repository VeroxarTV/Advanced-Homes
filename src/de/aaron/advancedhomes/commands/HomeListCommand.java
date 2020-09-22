package de.aaron.advancedhomes.commands;

import de.aaron.advancedhomes.main.AdvancedHomes;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomeListCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String labels, String[] args) {

        if (cmd.getName().equalsIgnoreCase("homes")) {

            if (!(sender instanceof Player)) {

                sender.sendMessage(AdvancedHomes.getOnlyplayer());

            } else {

                Player p = (Player) sender;

                if (!(args.length == 0)) {

                    p.sendMessage(AdvancedHomes.getHelp());

                } else {

                    p.sendMessage(AdvancedHomes.getPrefix() + "§3Homes: " + SetHomeCommand.homes);
                }
            }

        }

        return true;
    }
}
