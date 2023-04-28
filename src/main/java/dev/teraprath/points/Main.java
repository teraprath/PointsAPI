package dev.teraprath.points;

import dev.teraprath.points.command.PointsCommand;
import dev.teraprath.points.listener.LoginListener;
import dev.teraprath.points.sql.SQLAdapter;
import dev.teraprath.points.sql.SQLAuthentication;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;
    public SQLAuthentication authentication;

    @Override
    public void onEnable() {

        instance = this;

        getConfig().options().copyDefaults(true);
        saveConfig();

        this.authentication = new SQLAuthentication(getConfig().getString("mysql.host"), getConfig().getInt("mysql.port"), getConfig().getString("mysql.database"), getConfig().getString("mysql.user"), getConfig().getString("mysql.password"));
        new SQLAdapter(authentication).init();

        register();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void register() {
        final PluginManager pm = Bukkit.getServer().getPluginManager();
        pm.registerEvents(new LoginListener(), this);
        getCommand("points").setExecutor(new PointsCommand());

    }

    public static Main getInstance() {
        return instance;
    }

}
