package de.aaron.advancedhomes.listners;

import de.aaron.advancedhomes.commands.HomeCommand;
import de.aaron.advancedhomes.main.AdvancedHomes;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class OnPlayerMovement implements Listener {

    @EventHandler

    public void onPlayerMovement (PlayerMoveEvent e) {

        Player p = e.getPlayer();

        if (HomeCommand.teleportingPlayers.containsKey(p.getUniqueId())) {

            if (e.getFrom().getX() != e.getTo().getX()) {

                HomeCommand.teleportingPlayers.remove(p.getUniqueId());

                Bukkit.getScheduler().cancelTask(HomeCommand.taskID);

                p.sendMessage(AdvancedHomes.getPrefix() + "§cTeleport abgebrochen!");

            } else if (e.getFrom().getY() != e.getTo().getY()) {

                HomeCommand.teleportingPlayers.remove(p.getUniqueId());

                Bukkit.getScheduler().cancelTask(HomeCommand.taskID);

                p.sendMessage(AdvancedHomes.getPrefix() + "§cTeleport abgebrochen!");

            } else if (e.getFrom().getZ() != e.getTo().getZ()) {

                HomeCommand.teleportingPlayers.remove(p.getUniqueId());

                Bukkit.getScheduler().cancelTask(HomeCommand.taskID);

                p.sendMessage(AdvancedHomes.getPrefix() + "§cTeleport abgebrochen!");

            }

        }

    }

}
