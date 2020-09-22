package de.aaron.advancedhomes.main;

import de.aaron.advancedhomes.commands.AdHoCommands;
import de.aaron.advancedhomes.commands.SetHomeCommand;
import de.aaron.advancedhomes.commands.DelHomeCommand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class AdvancedHomes extends JavaPlugin {

    private static AdvancedHomes plugin;

    private final static String prefix = "§7[§bADVANCED-HOMES§7] §r";
    private final static String noperm = AdvancedHomes.getPrefix() + "§cDazu hast du keine Rechte!";
    private final static String help = AdvancedHomes.getPrefix() + "§3Für Hilfe: §6/AdHo help";
    private final static String onlyplayer = AdvancedHomes.getPrefix() + "§cDieser Befehl ist nur für Spieler zugelassen!";

    @Override
    public void onEnable() {

        plugin = this;

        Bukkit.getConsoleSender().sendMessage(AdvancedHomes.getPrefix() + "§aDas Plugin wurde gestartet!");

        getCommand("AdHo").setExecutor(new AdHoCommands());
        getCommand("sethome").setExecutor(new SetHomeCommand());
        getCommand("delhome").setExecutor(new DelHomeCommand());

    }

    public static String getPrefix() {
        return prefix;
    }

    public static AdvancedHomes getPlugin() {
        return plugin;
    }

    public static String getNoperm() {
        return noperm;
    }

    public static String getHelp() {
        return help;
    }

    public static String getOnlyplayer() {
        return onlyplayer;
    }

    @Override
    public void onDisable() {

        Bukkit.getConsoleSender().sendMessage(AdvancedHomes.getPrefix() + "§cDas Pugin wurde abgeschaltet!");

    }
}
