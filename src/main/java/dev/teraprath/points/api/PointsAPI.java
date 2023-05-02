package dev.teraprath.points.api;

import dev.teraprath.points.sql.SQLAdapter;
import dev.teraprath.points.sql.SQLAuthentication;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PointsAPI {

    private static JavaPlugin pl;
    private static SQLAuthentication authentication;

    public PointsAPI(@Nonnull JavaPlugin plugin) {
        pl = plugin;
    }

    public void init() {
        Plugin depend = pl.getServer().getPluginManager().getPlugin("PointsAPI");
        if (depend != null) {
            FileConfiguration cfg = depend.getConfig();
            authentication = new SQLAuthentication(cfg.getString("mysql.host"), cfg.getInt("mysql.port"), cfg.getString("mysql.database"), cfg.getString("mysql.user"), cfg.getString("mysql.password"));
            depend.getLogger().info("Plugin registered: " + pl.getName() + "-" + pl.getDescription().getVersion());
        } else {
            pl.getLogger().warning("PointsAPI is not installed! Instructions: https://github.com/teraprath/PointsAPI/wiki/1.-Getting-Started");
            pl.getLogger().warning("Download on GitHub: https://github.com/teraprath/PointsAPI/releases/latest");
        }
        pl.getServer().getScheduler().runTaskAsynchronously(pl, task -> {
            new SQLAdapter(authentication).init();
        });
    }

    public static boolean hasRegistered(@Nonnull Player player) {
        return hasRegistered(player.getUniqueId());
    }

    public static boolean hasRegistered(@Nonnull UUID uuid) {

        SQLAdapter adapter = new SQLAdapter(authentication);

        adapter.connect();

        try {
            ResultSet res = adapter.query(String.format("SELECT * FROM points_players WHERE uuid = '%s'", uuid));
            if (res.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        adapter.disconnect();
        return false;
    }

    public static int getPoints(@Nonnull Player player) {
        return getPoints(player.getUniqueId());
    }

    public static int getPoints(@Nonnull UUID uuid) {

        SQLAdapter adapter = new SQLAdapter(authentication);

        adapter.connect();

        try {
            ResultSet res = adapter.query(String.format("SELECT * FROM points_players WHERE uuid = '%s'", uuid));
            if (res.next()) {
                return res.getInt("points");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        adapter.disconnect();
        return 0;

    }

    public static void setPoints(@Nonnull Player player, @Nonnegative int amount) {
        setPoints(player.getUniqueId(), amount);
    }

    public static void setPoints(@Nonnull UUID uuid, @Nonnegative int amount) {

        SQLAdapter adapter = new SQLAdapter(authentication);

        adapter.connect();
        adapter.update(String.format("UPDATE points_players SET points = %d WHERE uuid = '%s'", amount, uuid));
        adapter.disconnect();

    }

    public static void addPoints(@Nonnull Player player, @Nonnegative int amount) {
        setPoints(player, getPoints(player) + amount);
    }

    public static void addPoints(@Nonnull UUID uuid, @Nonnegative int amount) {
        setPoints(uuid, getPoints(uuid) + amount);
    }

    public static void removePoints(@Nonnull Player player, @Nonnegative int amount) {
        setPoints(player, getPoints(player) - amount);
    }

    public static void removePoints(@Nonnull UUID uuid, @Nonnegative int amount) {
        setPoints(uuid, getPoints(uuid) - amount);
    }

    public static void reset(@Nonnull Player player) {
        reset(player.getUniqueId());
    }

    public static void reset(@Nonnull UUID uuid) {
        setPoints(uuid, 0);
    }

    public static void register(@Nonnull UUID uuid, @Nonnegative int coins) {

        SQLAdapter adapter = new SQLAdapter(authentication);

        adapter.connect();
        adapter.update(String.format("INSERT IGNORE INTO points_players (uuid, points) VALUES ('%s', '%d')", uuid, 0));
        adapter.disconnect();
    }

}
