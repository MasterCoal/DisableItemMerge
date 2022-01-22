package Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class DisableItemMerge extends JavaPlugin implements Listener, CommandExecutor {


    public boolean isEnabled = false;

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        Objects.requireNonNull(Bukkit.getPluginCommand("itemdisable")).setExecutor(this);

    }

    @Override
    public void onDisable() {
    }

    @EventHandler
    public void OnItemMergeEvent(ItemMergeEvent e) {
        if (isEnabled) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void OnPickup(EntityPickupItemEvent e) {
        if (isEnabled) {
            e.setCancelled(true);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equals("itemdisable")) {
            if (sender instanceof Player) {
                if (isEnabled) {
                    isEnabled = false;
                    sender.sendMessage(ChatColor.RED + "Item Merge Disabled");
                    return true;
                }
                sender.sendMessage(ChatColor.GREEN + "Item Merge Enabled");
                isEnabled = true;
            }

        }
        return true;
    }
}