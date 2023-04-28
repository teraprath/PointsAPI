package dev.teraprath.points.utils;

import dev.teraprath.points.Main;
import dev.teraprath.points.api.PointsAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;

public class PointsUtils {

    private final String self;
    private final String others;
    private final String playerNotFound;

    public PointsUtils() {

        String prefix = Main.getInstance().getConfig().getString("language.prefix");
        this.self = prefix + Main.getInstance().getConfig().getString("language.self");
        this.others = prefix + Main.getInstance().getConfig().getString("language.others");;
        this.playerNotFound = prefix + Main.getInstance().getConfig().getString("language.player_not_found");
    }

    public void print(@Nonnull CommandSender sender, @Nonnull OfflinePlayer player, String target) {
        Bukkit.getServer().getScheduler().runTaskAsynchronously(Main.getInstance(), task -> {
            if (sender instanceof Player && ((Player) sender).getUniqueId() == player.getUniqueId()) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', self.replace("%points%", PointsAPI.getPoints(player.getUniqueId()) + "")));
            } else {
                if (PointsAPI.hasRegistered(player.getUniqueId())) {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', others.replace("%points%", PointsAPI.getPoints(player.getUniqueId()) + "").replace("%player%", target)));
                } else {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', playerNotFound.replace("%player%", target)));
                }
            }
        });
    }

}
