package de.kandiedel.lobbySystem;

import de.kandiedel.lobbySystem.listeners.JoinListener;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class LobbySystem extends JavaPlugin {

    public static final String PREFIX = ChatColor.GRAY + "[" +
            ChatColor.DARK_PURPLE + "LobbySystem" +
            ChatColor.GRAY + "] " + ChatColor.GRAY;

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new JoinListener(getConfig()), this);

        saveDefaultConfig();

        getServer().getConsoleSender().sendMessage("");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[✔] "
                + ChatColor.DARK_PURPLE + getDescription().getName()
                + ChatColor.GRAY + " wurde erfolgreich aktiviert!");
        getServer().getConsoleSender().sendMessage("     "
                + ChatColor.GRAY + "Entwickelt von "
                + ChatColor.DARK_PURPLE + "Kandiedel 🛠️");
        getServer().getConsoleSender().sendMessage("");
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage("");
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[✖] "
                + ChatColor.DARK_PURPLE + getDescription().getName()
                + ChatColor.GRAY + " wurde erfolgreich deaktiviert!");
        getServer().getConsoleSender().sendMessage("     "
                + ChatColor.GRAY + "Entwickelt von "
                + ChatColor.DARK_PURPLE + "Kandiedel 🛠️");
        getServer().getConsoleSender().sendMessage("");
    }
}
