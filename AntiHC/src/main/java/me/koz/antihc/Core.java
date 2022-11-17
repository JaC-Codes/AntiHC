package me.koz.antihc;

import me.koz.antihc.listeners.CommandBlocker;
import me.koz.antihc.listeners.PlayerJoin;
import me.koz.antihc.listeners.Verification;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Core extends JavaPlugin {

    private static Core instance;

    public static Core getInstance() {
        return instance;
    }

    public void onEnable() {

        instance = this;
        File file = new File(getDataFolder(), "config.yml");
        if (!file.exists())
            saveDefaultConfig();
        long duration = System.currentTimeMillis();
        String prefix = "§3[" + getDescription().getName() + " " + getDescription().getVersion() + "] ";
        Bukkit.getConsoleSender().sendMessage(prefix + "§6=== ENABLE START ===");
        Bukkit.getConsoleSender().sendMessage(prefix + "§aLoading §dListeners");
        registerEvents();
        Bukkit.getConsoleSender().sendMessage(prefix + "§aLoading §dCommands");
        Bukkit.getConsoleSender().sendMessage(prefix + "§aMade by §dKoz");
        Bukkit.getConsoleSender().sendMessage(
                prefix + "§6=== ENABLE §aCOMPLETE §6(Took §d" + (System.currentTimeMillis() - duration) + "ms§6) ===");


    }

    private void registerEvents() {
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new CommandBlocker(this), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Verification(this), this);
    }

    public void onDisable() {
        long duration = System.currentTimeMillis();
        String prefix = "§3[" + getDescription().getName() + " " + getDescription().getVersion() + "] ";
        Bukkit.getConsoleSender().sendMessage(prefix + "§6=== DISABLING ===");
        Bukkit.getConsoleSender().sendMessage(prefix + "§aDisabling §dListeners");
        Bukkit.getConsoleSender().sendMessage(prefix + "§aDisabling §dCommands");
        Bukkit.getConsoleSender().sendMessage(prefix + "§aMade by §dKoz");
        Bukkit.getConsoleSender().sendMessage(
                prefix + "§6=== DISABLE §aCOMPLETE §6(Took §d" + (System.currentTimeMillis() - duration) + "ms§6) =");
    }

}
