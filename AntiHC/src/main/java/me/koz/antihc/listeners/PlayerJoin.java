package me.koz.antihc.listeners;

import me.koz.antihc.Core;
import me.koz.antihc.utils.CC;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.*;

import java.util.ArrayList;
import java.util.UUID;

public class PlayerJoin implements Listener {

    private final Core core;
    public static ArrayList<UUID> HC = new ArrayList<UUID>();

    public PlayerJoin(Core core) {
        this.core = core;
    }
    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        Player p = (Player) e.getPlayer();
        if (!(HC.contains(p.getUniqueId()))) {
            if (!(p.hasPermission("antihc.bypass"))) {
                HC.add(p.getUniqueId());
                String verify = this.core.getConfig().getString("verify.verify-message");
                if (this.core.getConfig().getBoolean("verify.join-title.enabled")) {
                    p.sendTitle(CC.translate("&a&lVerification"), CC.translate("&eYou must verify by typing &c") + verify + CC.translate(" &eto begin playing!"), 30, 150, 30);
                    if (this.core.getConfig().getBoolean("verify.join-actionbar.enabled")) {
                        String message = CC.translate(this.core.getConfig().getString("verify.join-actionbar.message"));
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
                        if (this.core.getConfig().getBoolean("verifychat.join-message.enabled")) {
                            p.sendMessage(CC.translate(this.core.getConfig().getString("verifychat.join-message.message")));
                        }
                    }
                }
            }
        }
    }
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        if (HC.contains(p.getUniqueId())) {
            e.setTo(e.getFrom());
        }
    }
    @EventHandler
    public void damageEvent(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            if (HC.contains(p.getUniqueId())) {
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void playerDropItem(PlayerDropItemEvent e) {
        Player p = (Player) e.getPlayer();
        if (HC.contains(p.getUniqueId())) {
            e.setCancelled(true);
        }
    }
    @EventHandler
    public void playerPickupItem(PlayerPickupArrowEvent e) {
        Player p = (Player) e.getPlayer();
        if (HC.contains(p.getUniqueId())) {
            e.setCancelled(true);
        }
    }
}
