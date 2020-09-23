package de.aaron.advancedhomes.commands;

import de.aaron.advancedhomes.main.AdvancedHomes;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdHoCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("AdHo")) {

            if (!(sender instanceof Player)) {

                if (!(args.length == 1)) {

                    sender.sendMessage(AdvancedHomes.getHelp());

                } else {

                    switch (args[0]) {

                        case "help":
                            sender.sendMessage("§7***-***-§bAdvanced§7-§bHomes§7-***-***");
                            sender.sendMessage("§6/AdHo help §7≫ §aZeigt dir dieses Menü an!");
                            sender.sendMessage("§6/AdHo info §7≫ §aZeigt dir Informationen über das Plugin an!");
                            sender.sendMessage("§6/sethome [Name] §7≫ §aSetzt dein Home!");
                            sender.sendMessage("§6/delhome [Name] §7≫ §aLöscht dein Home!");
                            sender.sendMessage("§6/homes §7≫ §aZeigt dir eine Liste deiner Homes an!");
                            sender.sendMessage("§6/home [name] §7≫ §aTeleportiert dich zu deinem Home!");
                            sender.sendMessage("§7***-***-§bAdvanced§7-§bHomes§7-***-***");

                            break;

                        case "info":
                            sender.sendMessage("§7***-***-§bAdvanced§7-§bHomes§7-***-***");
                            sender.sendMessage("§6Version: §a" + AdvancedHomes.getPlugin().getDescription().getVersion());
                            sender.sendMessage("§6Author: §a" + AdvancedHomes.getPlugin().getDescription().getDescription());
                            sender.sendMessage("§7***-***-§bAdvanced§7-§bHomes§7-***-***");

                            break;

                        default:
                            sender.sendMessage(AdvancedHomes.getHelp());
                    }
                }
            } else {

                Player p = (Player) sender;

                if (!(p.hasPermission("AdvancedHomes.Help.Admin"))) {


                    if (!(args.length == 1)) {

                        p.sendMessage(AdvancedHomes.getHelp());

                    } else {

                        switch (args[0]) {

                            case "help":
                                p.sendMessage("§7***-***-§bAdvanced§7-§bHomes§7-***-***");
                                p.sendMessage("§6/AdHo help §7≫ §aZeigt dir dieses Menü an!");
                                p.sendMessage("§6/sethome [Name] §7≫ §aSetzt dein Home!");
                                p.sendMessage("§6/delhome [Name] §7≫ §aLöscht dein Home!");
                                p.sendMessage("§6/homes §7≫ §aZeigt dir eine Liste deiner Homes an!");
                                p.sendMessage("§6/home [name] §7≫ §aTeleportiert dich zu deinem Home!");
                                p.sendMessage("§7***-***-§bAdvanced§7-§bHomes§7-***-***");

                                break;

                            case "info":

                                p.sendMessage(AdvancedHomes.getNoperm());

                                break;

                            default:
                                p.sendMessage(AdvancedHomes.getHelp());


                        }

                    }


                } else if (!(args.length == 1)) {

                    p.sendMessage(AdvancedHomes.getHelp());

                } else {

                    switch (args[0]) {

                        case "help":
                            p.sendMessage("§7***-***-§bAdvanced§7-§bHomes§7-***-***");
                            p.sendMessage("§6/AdHo help §7≫ §aZeigt dir dieses Menü an!");
                            p.sendMessage("§6/AdHo info §7≫ §aZeigt dir Informationen über das Plugin an!");
                            p.sendMessage("§6/sethome [Name] §7≫ §aSetzt dein Home!");
                            p.sendMessage("§6/delhome [Name] §7≫ §aLöscht dein Home!");
                            p.sendMessage("§6/homes §7≫ §aZeigt dir eine Liste deiner Homes an!");
                            p.sendMessage("§6/home [name] §7≫ §aTeleportiert dich zu deinem Home!");
                            p.sendMessage("§7***-***-§bAdvanced§7-§bHomes§7-***-***");

                            break;

                        case "info":
                            p.sendMessage("§7***-***-§bAdvanced§7-§bHomes§7-***-***");
                            p.sendMessage("§6Version: §a" + AdvancedHomes.getPlugin().getDescription().getVersion());
                            p.sendMessage("§6Author: §a" + AdvancedHomes.getPlugin().getDescription().getDescription());
                            p.sendMessage("§7***-***-§bAdvanced§7-§bHomes§7-***-***");

                            break;

                        default:
                            p.sendMessage(AdvancedHomes.getHelp());

                    }
                }
            }
        }


        return true;
    }
}
