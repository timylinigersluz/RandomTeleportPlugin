package ch.ksrminecraft.randomteleport.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;

public class TeleportCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        // Spieler extrahieren und Argument-Check
        if ((!(commandSender instanceof Player)) || (strings.length != 0)) {
            return true;
        }

        Player player = (Player) commandSender;

        // Grenzen der Worldborder holen
        World world = player.getWorld();
        int maxWorldSize = getMaxWorldSize();

        // Zielort bestimmen (x, y, z) -> nextDouble() gibt eine Zahl 0.0 und 1.0
        Random random = new Random();

        int x = (int) ((random.nextDouble() * maxWorldSize) - (maxWorldSize /2));
        int z = (int) ((random.nextDouble() * maxWorldSize) - (maxWorldSize /2));
        int y = world.getHighestBlockYAt(x, z);

        Location targetLocation = new Location(world, x, y, z);

        // Spieler teleportieren und Nachricht schicken
        player.teleport(targetLocation);
        player.sendMessage(Component.text(ChatColor.GREEN + "Teleportiere nach " + x + ", " + y + ", " + z));

        return true;
    }

    private int getMaxWorldSize() {

        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("server.properties"));
            String maxSizeStr = properties.getProperty("max-world-size");
            return Integer.parseInt(maxSizeStr);
        } catch (Exception e) {
            e.printStackTrace();
            return 1024; // Standard-Wert zurückgeben, falls ein Fehler auftritt
        }

    }

}
