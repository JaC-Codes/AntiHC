package me.koz.antihc.listeners;

import me.koz.antihc.Core;
import me.koz.antihc.utils.CC;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;


public class Verification implements Listener {


    private final Core core;

    public Verification(Core core) {
        this.core = core;
    }
    @EventHandler
    public void verifyCheck(AsyncPlayerChatEvent e) {
        String input = e.getMessage();
        String verify = this.core.getConfig().getString("verify.verify-message");
        Player p = (Player) e.getPlayer();
        if (!(PlayerJoin.HC.contains(p.getUniqueId()))) return;
        if (input.equalsIgnoreCase(verify)) {
            PlayerJoin.HC.remove(p.getUniqueId());
                if (this.core.getConfig().getBoolean("verify.verified-title.enabled")) {
                    p.sendTitle(CC.translate("&a&lVerified!"), CC.translate("&eVerification Successful! You are now verified!"), 30, 150, 30);
                    if (this.core.getConfig().getBoolean("verify.verified-actionbar.enabled")) {
                        String message = CC.translate(this.core.getConfig().getString("verify.verified-actionbar.message"));
                        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(message));
                        if (this.core.getConfig().getBoolean("verifychat.verified-message.enabled")) {
                            p.sendMessage(CC.translate(this.core.getConfig().getString("verifychat.verified-message.message")));
                        }
                    }
                }
            }
        }
    }