package de.aaron.advancedhomes.main;

import de.aaron.advancedhomes.commands.AdHoCommands;
import de.aaron.advancedhomes.commands.DelHomeCommand;
import de.aaron.advancedhomes.commands.HomeListCommand;
import de.aaron.advancedhomes.commands.SetHomeCommand;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdvancedHomes extends JavaPlugin {

    private static AdvancedHomes plugin;

    private final static String prefix = "§7[§bADVANCED-HOMES§7] §r";
    private final static String noperm = AdvancedHomes.getPrefix() + "§cDazu hast du keine Rechte!";
    private final static String help = AdvancedHomes.getPrefix() + "§3Für Hilfe: §6/AdHo help";
    private final static String onlyplayer = AdvancedHomes.getPrefix() + "§cDieser Befehl ist nur für Spieler zugelassen!";
    private final File homes = new File("plugins/AdvancedHomes/Homes.yml");
    private final YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(homes);

    @Override
    public void onEnable() {

        plugin = this;

        Bukkit.getConsoleSender().sendMessage(AdvancedHomes.getPrefix() + "§aDas Plugin wurde gestartet!");

        getCommand("AdHo").setExecutor(new AdHoCommands());
        getCommand("sethome").setExecutor(new SetHomeCommand());
        getCommand("delhome").setExecutor(new DelHomeCommand());
        getCommand("homes").setExecutor(new HomeListCommand());

        List<String> homes = yamlConfiguration.getStringList("Homes");
        SetHomeCommand.homes = (ArrayList<String>) homes;
        yamlConfiguration.set("Homes", null);

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

        if (SetHomeCommand.homes.size() > 0) {

            yamlConfiguration.set("Homes", SetHomeCommand.homes);
            try {
                yamlConfiguration.save(this.homes);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}
