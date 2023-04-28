package dev.teraprath.points.api;

import dev.teraprath.points.Main;
import dev.teraprath.points.sql.SQLAdapter;
import dev.teraprath.points.sql.SQLAuthentication;
import org.bukkit.entity.Player;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public interface PointsAPI {

    static boolean hasRegistered(@Nonnull Player player) {
        return hasRegistered(player.getUniqueId());
    }

    static boolean hasRegistered(@Nonnull UUID uuid) {
        SQLAuthentication authentication = Main.getInstance().authentication;
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

    static int getPoints(@Nonnull Player player) {
        return getPoints(player.getUniqueId());
    }

    static int getPoints(@Nonnull UUID uuid) {

        SQLAuthentication authentication = Main.getInstance().authentication;
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

    static void setPoints(@Nonnull Player player, @Nonnegative int points) {
        setPoints(player.getUniqueId(), points);
    }

    static void setPoints(@Nonnull UUID uuid, @Nonnegative int points) {

        SQLAuthentication authentication = Main.getInstance().authentication;
        SQLAdapter adapter = new SQLAdapter(authentication);

        adapter.connect();
        adapter.update(String.format("UPDATE points_players SET points = %d WHERE uuid = '%s'", points, uuid));
        adapter.disconnect();

    }

    static void addPoints(@Nonnull Player player, @Nonnegative int points) {
        setPoints(player, getPoints(player) + points);
    }

    static void addPoints(@Nonnull UUID uuid, @Nonnegative int points) {
        setPoints(uuid, getPoints(uuid) + points);
    }

    static void removePoints(@Nonnull Player player, @Nonnegative int points) {
        setPoints(player, getPoints(player) - points);
    }

    static void removePoints(@Nonnull UUID uuid, @Nonnegative int points) {
        setPoints(uuid, getPoints(uuid) - points);
    }

    static void reset(@Nonnull Player player) {
        reset(player.getUniqueId());
    }

    static void reset(@Nonnull UUID uuid) {
        setPoints(uuid, 0);
    }

}
