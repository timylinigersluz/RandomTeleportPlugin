package ch.ksrminecraft.randomteleport;

import ch.ksrminecraft.randomteleport.commands.TeleportCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class RandomTeleport extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getCommand("rtp").setExecutor(new TeleportCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
