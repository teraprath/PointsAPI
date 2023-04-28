package dev.teraprath.points.utils;

import dev.teraprath.points.Main;
import dev.teraprath.points.sql.SQLAdapter;
import dev.teraprath.points.sql.SQLAuthentication;
import org.bukkit.Bukkit;

import javax.annotation.Nonnull;
import java.util.UUID;

public class RegisterUtils {

    private final int startValue;

    public RegisterUtils() {
        this.startValue = Main.getInstance().getConfig().getInt("points.start_value");
    }

    public void register(@Nonnull UUID uuid) {
        Bukkit.getServer().getScheduler().runTaskAsynchronously(Main.getInstance(), task -> {

            SQLAdapter adapter = new SQLAdapter(Main.authentication);

            adapter.connect();
            adapter.update(String.format("INSERT IGNORE INTO points_players (uuid, points) VALUES ('%s', '%d')", uuid, startValue));
            adapter.disconnect();

        });
    }

}
