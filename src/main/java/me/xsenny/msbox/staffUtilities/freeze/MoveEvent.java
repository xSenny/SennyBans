package me.xsenny.msbox.staffUtilities.freeze;

import me.xsenny.msbox.MsBox;
import me.xsenny.msbox.utils.Color;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.persistence.PersistentDataType;

public class MoveEvent implements Listener {

    @EventHandler
    public static void onPlayerMove(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if (p.getPersistentDataContainer().has(new NamespacedKey(MsBox.plugin, "freeze"), PersistentDataType.INTEGER) && p.getPersistentDataContainer().get(new NamespacedKey(MsBox.plugin, "freeze"), PersistentDataType.INTEGER) == 1){
            p.sendMessage(Color.cc("&cYou are freezed right now by a staff member.", true));
            e.setCancelled(true);
        }
    }

}
