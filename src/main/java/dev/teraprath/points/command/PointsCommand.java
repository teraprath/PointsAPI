package dev.teraprath.points.command;

import dev.teraprath.points.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class PointsCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length > 0) {
            final OfflinePlayer player = Bukkit.getOfflinePlayer(args[0]);
            new MessageUtils().print(sender, player, args[0]);
        } else {
            if (sender instanceof Player) {
                new MessageUtils().print(sender, (Player) sender, null);
            } else {
                sender.sendMessage("points <Player>");
            }
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> list = new ArrayList<>();
        String current = args[args.length - 1].toLowerCase();

        if (args.length == 1) {
            Bukkit.getOnlinePlayers().forEach(player -> {
                list.add(player.getName());
            });
        }

        list.removeIf(s -> !s.toLowerCase().startsWith(current));
        return list;
    }

}
