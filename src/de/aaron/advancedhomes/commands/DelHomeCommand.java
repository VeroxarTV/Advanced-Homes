package de.aaron.advancedhomes.commands;

import de.aaron.advancedhomes.main.AdvancedHomes;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class DelHomeCommand implements CommandExecutor {

    FileConfiguration config = AdvancedHomes.getPlugin().getConfig();

    ArrayList<UUID> delhome = new ArrayList<>();
    ArrayList<UUID> delhomeDelay = new ArrayList<>();

    int taskID;

    int counter = 20;
    int delaycounter = 60;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String labels, String[] args) {

        if (cmd.getName().equalsIgnoreCase("delhome")) {

            if(!(sender instanceof Player)) {

                sender.sendMessage(AdvancedHomes.getOnlyplayer());

            } else {

                Player p = (Player) sender;

                if (!(delhomeDelay.contains(p.getUniqueId()) && args.length == 1)) {

                    if (!(args.length == 1)) {

                        if (args.length == 2) {

                            if (args[0].equalsIgnoreCase("*")) {

                                if (args[1].equalsIgnoreCase("confirm")) {

                                    if (delhome.contains(p.getUniqueId())) {

                                        config.set(p.getName(), null);

                                        AdvancedHomes.getPlugin().saveConfig();

                                        p.sendMessage(AdvancedHomes.getPrefix() + "§aAlle deine Homes wurden gelöscht!");

                                        delhome.remove(p.getUniqueId());
                                        delhomeDelay.add(p.getUniqueId());

                                    } else
                                        p.sendMessage(AdvancedHomes.getPrefix() + "§cDu musst zu erst §6/delhome * §ceingeben!");

                                } else
                                    p.sendMessage(AdvancedHomes.getHelp());

                            } else
                                p.sendMessage(AdvancedHomes.getHelp());

                        } else
                            p.sendMessage(AdvancedHomes.getHelp());

                    } else if (config.contains(p.getName() + "." + args[0].toLowerCase())) {

                        SetHomeCommand.homes.remove(args[0].toLowerCase());

                        config.set(p.getName() + "." + args[0].toLowerCase(), null);
                        config.set(p.getName() + ".Homes", SetHomeCommand.homes);

                        AdvancedHomes.getPlugin().saveConfig();

                        p.sendMessage(AdvancedHomes.getPrefix() + "§aDein Home wurde gelöscht!");

                    } else if (!(args[0].equalsIgnoreCase("*"))) {

                        p.sendMessage(AdvancedHomes.getPrefix() + "§cDieses Home gibt es nicht!");

                    } else if (config.getInt(p.getName() + ".Homes", SetHomeCommand.homes.size()) != 0 && config.get(p.getName() + ".Homes", SetHomeCommand.homes) != null) {

                        p.sendMessage(AdvancedHomes.getPrefix() + "§cWillst du wirklich alle Homes löschen?");
                        p.sendMessage(AdvancedHomes.getPrefix() + "§7(§6/delhome * confirm§7)");

                        delhome.add(p.getUniqueId());


                        taskID = Bukkit.getScheduler().scheduleSyncRepeatingTask(AdvancedHomes.getPlugin(), () -> {

                            counter--;
                            delaycounter--;

                            if (counter <= 0) {

                                Bukkit.getScheduler().cancelTask(taskID);
                                delhome.remove(p.getUniqueId());

                            }

                            if (delaycounter <= 0) {

                                p.sendMessage(AdvancedHomes.getPrefix() + "§cDu kannst §6/delhome * §cwieder nutzen!");
                                delhomeDelay.remove(p.getUniqueId());
                            }

                        }, 0, 20);

                    } else
                        p.sendMessage(AdvancedHomes.getPrefix() + "§cDu besitzt keine Homes!");

                } else
                    p.sendMessage(AdvancedHomes.getPrefix() + "§cBitte warte noch §6" + delaycounter + " Sekunden");
            }

        }

        return true;
    }
}
