package dev.teraprath.points.command;

import dev.teraprath.points.Main;
import dev.teraprath.points.api.PointsAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class AdminCommand implements CommandExecutor, TabCompleter {

    private final FileConfiguration cfg = Main.getInstance().getConfig();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 2) {
            final Player target = Bukkit.getPlayer(args[0]);
            final int amount = Integer.parseInt(args[2]);
            String message;
            if (target != null && amount >= 0) {
                switch (args[1].toLowerCase()) {
                    case "set":
                        PointsAPI.setPoints(target, amount);
                        message = cfg.getString("language.player_points_set");
                        sender.sendMessage(message != null ? ChatColor.translateAlternateColorCodes('&', message.replace("%amount%", amount + "").replace("%player%", target.getName())) : "");
                        break;
                    case "add":
                        PointsAPI.addPoints(target, amount);
                        message = cfg.getString("language.player_points_added");
                        sender.sendMessage(message != null ? ChatColor.translateAlternateColorCodes('&', message.replace("%amount%", amount + "").replace("%player%", target.getName())) : "");
                        break;
                    case "remove":
                        PointsAPI.removePoints(target, amount);
                        message = cfg.getString("language.player_points_removed");
                        sender.sendMessage(message != null ? ChatColor.translateAlternateColorCodes('&', message.replace("%amount%", amount + "").replace("%player%", target.getName())) : "");
                        break;
                    default:
                        String wrongUsage = cfg.getString("language.wrong_usage");
                        sender.sendMessage(wrongUsage != null ? ChatColor.translateAlternateColorCodes('&', wrongUsage.replace("%usage%", "/apoints <player> [set|remove|add] <amount>")) : "");
                        break;
                }
            } else {
                wrongUsage(sender);
            }
        } else {
            wrongUsage(sender);
        }

        return false;
    }

    private void wrongUsage(@Nonnull CommandSender sender) {
        String wrongUsage = cfg.getString("language.wrong_usage");
        sender.sendMessage(wrongUsage != null ? ChatColor.translateAlternateColorCodes('&', wrongUsage.replace("%usage%", "/apoints <player> [set|remove|add] <amount>")) : "");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        String current = args[args.length - 1].toLowerCase();

        switch (args.length) {
            case 1:
                Bukkit.getOnlinePlayers().forEach(player -> {
                    list.add(player.getName());
                });
                break;
            case 2:
                list.add("set");
                list.add("add");
                list.add("remove");
                break;
            case 3:
                list.add("amount");
            default:
                break;
        }

        list.removeIf(s -> !s.toLowerCase().startsWith(current));
        return list;
    }
}
