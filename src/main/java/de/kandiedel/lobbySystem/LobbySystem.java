package de.kandiedel.lobbySystem;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class LobbySystem extends JavaPlugin {

    @Override
    public void onEnable() {
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
