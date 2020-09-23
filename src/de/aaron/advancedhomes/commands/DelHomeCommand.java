package de.aaron.advancedhomes.commands;

import de.aaron.advancedhomes.main.AdvancedHomes;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class DelHomeCommand implements CommandExecutor {

    FileConfiguration config = AdvancedHomes.getPlugin().getConfig();

    HashMap<UUID, Integer> delhome = new HashMap<>();
    HashMap<UUID, Integer> delhomeDelay = new HashMap<>();

    int confirmID;
    int delayID;

    int counter = 20;
    int delaycounter = 60;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String labels, String[] args) {

        if (cmd.getName().equalsIgnoreCase("delhome")) {

            if (!(sender instanceof Player)) {

                sender.sendMessage(AdvancedHomes.getOnlyplayer());

            } else {

                Player p = (Player) sender;

                    if (!(args.length == 1)) {

                        if (args.length == 2) {

                            if (args[0].equalsIgnoreCase("*")) {

                                if (args[1].equalsIgnoreCase("confirm")) {

                                    if (delhome.containsKey(p.getUniqueId())) {

                                        config.set(p.getName(), null);

                                        AdvancedHomes.getPlugin().saveConfig();

                                        p.sendMessage(AdvancedHomes.getPrefix() + "§aAlle deine Homes wurden gelöscht!");

                                        delhome.remove(p.getUniqueId());
                                        delhomeDelay.put(p.getUniqueId(), delayID);


                                    } else
                                        p.sendMessage(AdvancedHomes.getPrefix() + "§cDu musst zu erst §6/delhome * §ceingeben!");

                                } else
                                    p.sendMessage(AdvancedHomes.getHelp());

                            } else
                                p.sendMessage(AdvancedHomes.getHelp());

                        } else
                            p.sendMessage(AdvancedHomes.getHelp());

                    } else if (config.contains(p.getName() + "." + args[0].toLowerCase())) {

                        SetHomeCommand.homes.get(p.getUniqueId());
                        SetHomeCommand.homenames = config.getStringList(p.getName() + ".HomeNames");

                        SetHomeCommand.homes.remove(p.getUniqueId(), args[0].toLowerCase());
                        SetHomeCommand.homenames.remove(args[0].toLowerCase());

                        config.set(p.getName() + "." + args[0].toLowerCase(), null);
                        config.set(p.getName() + ".Homes", SetHomeCommand.homenames.size());
                        config.set(p.getName() + ".HomeNames", SetHomeCommand.homenames);

                        AdvancedHomes.getPlugin().saveConfig();

                        p.sendMessage(AdvancedHomes.getPrefix() + "§aDein Home wurde gelöscht!");

                    } else if (!(args[0].equalsIgnoreCase("*"))) {

                        p.sendMessage(AdvancedHomes.getPrefix() + "§cDieses Home existiert nicht!");

                    } else if (config.getInt(p.getName() + ".Homes", SetHomeCommand.homenames.size()) != 0 &&
                            config.get(p.getName() + ".Homes", SetHomeCommand.homenames) != null) {

                        if (!(delhomeDelay.containsKey(p.getUniqueId()))) {

                            p.sendMessage(AdvancedHomes.getPrefix() + "§cWillst du wirklich alle Homes löschen?");
                            p.sendMessage(AdvancedHomes.getPrefix() + "§7(§6/delhome * confirm§7)");

                            delhome.put(p.getUniqueId(), confirmID);


                            confirmID = Bukkit.getScheduler().scheduleSyncRepeatingTask(AdvancedHomes.getPlugin(), () -> {

                                counter--;

                                if (counter <= 0) {

                                    Bukkit.getScheduler().cancelTask(confirmID);
                                    delhome.remove(p.getUniqueId());

                                }

                            }, 0, 20);

                            delayID = Bukkit.getScheduler().scheduleSyncRepeatingTask(AdvancedHomes.getPlugin(), () -> {

                                delaycounter--;

                                if (delaycounter <= 0) {

                                    Bukkit.getScheduler().cancelTask(delhomeDelay.get(p.getUniqueId()));
                                    p.sendMessage(AdvancedHomes.getPrefix() + "§cDu kannst §6/delhome * §cwieder nutzen!");
                                    delhomeDelay.remove(p.getUniqueId());
                                }

                            }, 0, 20);

                        } else
                            p.sendMessage(AdvancedHomes.getPrefix() + "§cBitte warte noch §6" + delaycounter + " Sekunden!");

                    } else
                        p.sendMessage(AdvancedHomes.getPrefix() + "§cDu besitzt keine Homes!");
            }

        }

        return true;
    }
}
