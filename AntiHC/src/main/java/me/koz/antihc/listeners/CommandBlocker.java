package me.koz.antihc.listeners;

import me.koz.antihc.Core;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;

public class CommandBlocker implements Listener {

    private final Core core;

    public CommandBlocker(Core core) {
        this.core = core;
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPreprocess(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        if (PlayerJoin.HC.contains(p.getUniqueId())) {
            String command = e.getMessage();
            List<String> hccmds = this.core.getConfig().getStringList("commandblocker.blocked-commands");

            for (String hccmd : hccmds) {
                if (command.toLowerCase().startsWith(hccmd)) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
