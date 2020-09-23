package de.aaron.advancedhomes.main;

import de.aaron.advancedhomes.commands.*;
import de.aaron.advancedhomes.listners.OnPlayerMovement;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;

public class AdvancedHomes extends JavaPlugin {

    private static AdvancedHomes plugin;

    private final static String prefix = "§7[§bADVANCED-HOMES§7] §r";
    private final static String noperm = AdvancedHomes.getPrefix() + "§cDazu hast du keine Rechte!";
    private final static String help = AdvancedHomes.getPrefix() + "§3Für Hilfe: §6/adho help";
    private final static String onlyplayer = AdvancedHomes.getPrefix() + "§cDieser Befehl ist nur für Spieler zugelassen!";

    @Override
    public void onEnable() {

        plugin = this;

        Bukkit.getConsoleSender().sendMessage(AdvancedHomes.getPrefix() + "§aDas Plugin wurde gestartet!");

        getCommand("adho").setExecutor(new AdHoCommands());
        getCommand("sethome").setExecutor(new SetHomeCommand());
        getCommand("delhome").setExecutor(new DelHomeCommand());
        getCommand("homes").setExecutor(new HomesCommand());
        getCommand("home").setExecutor(new HomeCommand());

        getServer().getPluginManager().registerEvents(new OnPlayerMovement(), this);

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
