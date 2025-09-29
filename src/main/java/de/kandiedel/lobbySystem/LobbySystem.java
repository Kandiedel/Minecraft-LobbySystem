package de.kandiedel.lobbySystem;

import de.kandiedel.lobbySystem.listeners.JoinListener;
import de.kandiedel.lobbySystem.listeners.LobbyListener;
import de.kandiedel.lobbySystem.utils.MySQLManager;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class LobbySystem extends JavaPlugin {

    public static final String PREFIX = ChatColor.GRAY + "[" +
            ChatColor.DARK_PURPLE + "LobbySystem" +
            ChatColor.GRAY + "] " + ChatColor.GRAY;

    private MySQLManager mySQLManager;

    @Override
    public void onEnable() {

        saveDefaultConfig();

        getServer().getPluginManager().registerEvents(new JoinListener(getConfig()), this);
        getServer().getPluginManager().registerEvents(new LobbyListener(), this);

        mySQLManager = new MySQLManager(
                getConfig().getString("MySQL.host"),
                getConfig().getInt("MySQL.port"),
                getConfig().getString("MySQL.database"),
                getConfig().getString("MySQL.username"),
                getConfig().getString("MySQL.password"));
        mySQLManager.connect();

        getServer().getConsoleSender().sendMessage("");
        getServer().getConsoleSender().sendMessage(ChatColor.WHITE + "========================================================");
        getServer().getConsoleSender().sendMessage("");
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[ ✔ ] "
                + ChatColor.DARK_PURPLE + getDescription().getName()
                + ChatColor.GRAY + " wurde erfolgreich aktiviert!");
        getServer().getConsoleSender().sendMessage("     "
                + ChatColor.GRAY + "Entwickelt von "
                + ChatColor.DARK_PURPLE + "Kandiedel 🛠️");
        getServer().getConsoleSender().sendMessage("");
        getServer().getConsoleSender().sendMessage(ChatColor.WHITE + "========================================================");
        getServer().getConsoleSender().sendMessage("");
    }

    @Override
    public void onDisable() {

        mySQLManager.disconnect();

        getServer().getConsoleSender().sendMessage("");
        getServer().getConsoleSender().sendMessage(ChatColor.WHITE + "==========================================================");
        getServer().getConsoleSender().sendMessage("");
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[ ✖ ] "
                + ChatColor.DARK_PURPLE + getDescription().getName()
                + ChatColor.GRAY + " wurde erfolgreich deaktiviert!");
        getServer().getConsoleSender().sendMessage("     "
                + ChatColor.GRAY + "Entwickelt von "
                + ChatColor.DARK_PURPLE + "Kandiedel 🛠️");
        getServer().getConsoleSender().sendMessage("");
        getServer().getConsoleSender().sendMessage(ChatColor.WHITE + "==========================================================");
        getServer().getConsoleSender().sendMessage("");
    }
}
