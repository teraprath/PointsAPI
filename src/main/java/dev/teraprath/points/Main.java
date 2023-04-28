package dev.teraprath.points;

import dev.teraprath.points.command.AdminCommand;
import dev.teraprath.points.command.PointsCommand;
import dev.teraprath.points.listener.LoginListener;
import dev.teraprath.points.sql.SQLAdapter;
import dev.teraprath.points.sql.SQLAuthentication;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;
    public static SQLAuthentication authentication;

    @Override
    public void onEnable() {

        instance = this;

        getConfig().options().copyDefaults(true);
        saveConfig();

        authentication = new SQLAuthentication(getConfig().getString("mysql.host"), getConfig().getInt("mysql.port"), getConfig().getString("mysql.database"), getConfig().getString("mysql.user"), getConfig().getString("mysql.password"));
        new SQLAdapter(authentication).init();

        registerEvents();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerCommands() {
        getCommand("points").setExecutor(new PointsCommand());
        getCommand("apoints").setExecutor(new AdminCommand());
    }

    private void registerEvents() {
        final PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(new LoginListener(), this);

    }

    public static Main getInstance() {
        return instance;
    }

}
