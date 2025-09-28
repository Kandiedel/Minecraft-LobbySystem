package de.kandiedel.lobbySystem.listeners;

import de.kandiedel.lobbySystem.LobbySystem;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class JoinListener implements Listener {

    private final FileConfiguration config;

    public JoinListener(FileConfiguration config) {
        this.config = config;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        preparePlayer(player);
        giveLobbyItems(player);
        teleportToLobby(player);

        event.setJoinMessage(LobbySystem.PREFIX + ChatColor.DARK_PURPLE + player.getName() + ChatColor.GRAY + " hat die Lobby betreten!");
    }

    private void preparePlayer(Player player) {
        player.getInventory().clear();

        player.setGameMode(org.bukkit.GameMode.ADVENTURE);
        player.setHealth(20.0);
        player.setFoodLevel(20);

        boolean allowFly = config.getBoolean("lobby.allow-fly", false);
        player.setAllowFlight(allowFly);
    }

    private void teleportToLobby(Player player) {
        String worldName = config.getString("lobby.world", "world");
        World world = Bukkit.getWorld(worldName);

        if (world == null) {
            player.sendMessage("§cDie Lobby-Welt konnte nicht gefunden werden!");
            return;
        }

        double x = config.getDouble("lobby.spawn.x", 0.5);
        double y = config.getDouble("lobby.spawn.y", 64);
        double z = config.getDouble("lobby.spawn.z", 0.5);
        float yaw = (float) config.getDouble("lobby.spawn.yaw", 0);
        float pitch = (float) config.getDouble("lobby.spawn.pitch", 0);

        player.teleport(new org.bukkit.Location(world, x, y, z, yaw, pitch));
    }

    private void giveLobbyItems(Player player) {
        if (config.isConfigurationSection("items")) {
            for (String key : config.getConfigurationSection("items").getKeys(false)) {
                String path = "items." + key;

                int slot = config.getInt(path + ".slot", 0);
                Material material = Material.getMaterial(config.getString(path + ".material", "STONE"));
                if (material == null) material = Material.STONE;

                String name = ChatColor.translateAlternateColorCodes('&', config.getString(path + ".name", "Item"));
                List<String> loreList = config.getStringList(path + ".lore");
                for (int i = 0; i < loreList.size(); i++) {
                    loreList.set(i, ChatColor.translateAlternateColorCodes('&', loreList.get(i)));
                }

                ItemStack item = new ItemStack(material);
                ItemMeta meta = item.getItemMeta();
                if (meta != null) {
                    meta.setDisplayName(name);
                    meta.setLore(loreList);
                    item.setItemMeta(meta);
                }

                player.getInventory().setItem(slot, item);
            }
        }
    }
}
