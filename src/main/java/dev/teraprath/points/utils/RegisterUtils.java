package dev.teraprath.points.utils;

import dev.teraprath.points.Main;
import dev.teraprath.points.api.PointsAPI;
import org.bukkit.Bukkit;

import javax.annotation.Nonnull;
import java.util.UUID;

public class RegisterUtils {

    private final int startValue;

    public RegisterUtils() {
        this.startValue = Main.getInstance().getConfig().getInt("start_points");
    }

    public void register(@Nonnull UUID uuid) {
        Bukkit.getServer().getScheduler().runTaskAsynchronously(Main.getInstance(), task -> {
            PointsAPI.register(uuid, startValue);
        });
    }

}
