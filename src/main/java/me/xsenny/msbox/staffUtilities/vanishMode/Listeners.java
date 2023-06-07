package me.xsenny.msbox.staffUtilities.vanishMode;

import me.xsenny.msbox.MsBox;
import me.xsenny.msbox.utils.Color;
import me.xsenny.msbox.utils.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class Listeners implements Listener {

    @EventHandler
    public static void onBreak(BlockBreakEvent e){
        if (Methods.isPlayerVanished(e.getPlayer())){
            e.getPlayer().sendMessage(Color.cc("&3" + "You cant do it while you're vanished", true));
            e.setCancelled(true);
        }
    }
    @EventHandler
    public static void onGiveDamage(EntityDamageByEntityEvent e){
        if (e.getDamager() instanceof Player){
            Player p = (Player) e.getDamager();
            if (Methods.isPlayerVanished(p)){
                p.sendMessage(Color.cc("&3" + "You cant do it while you're vanished", true));
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public static void onDamage(EntityDamageByBlockEvent e){
        if (e.getEntity() instanceof Player)
            if (Methods.isPlayerVanished((Player) e.getEntity()))
                e.setCancelled(true);
    }
    @EventHandler
    public static void onDamage1(EntityDamageEvent e){
        if (e.getEntity() instanceof Player)
            if (Methods.isPlayerVanished((Player) e.getEntity()))
                e.setCancelled(true);
    }
    @EventHandler
    public static void itemDrop(PlayerDropItemEvent e){
        if (Methods.isPlayerVanished(e.getPlayer())){
            e.getPlayer().sendMessage(Color.cc("&3" + "You cant do it while you're vanished", true));
            e.setCancelled(true);
        }
    }
    @EventHandler
    public static void itemGet(EntityPickupItemEvent e){
        if (e.getEntity() instanceof Player){
            Player p = (Player) e.getEntity();
            if (Methods.isPlayerVanished(p)){
                p.sendMessage(Color.cc("&3" + "You cant do it while you're vanished", true));
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if (Methods.isPlayerVanished(p)){
            p.sendMessage(Color.cc("&7You joined with the vanish mode already activated, use /v to turn it off", true));
        }else if (!(p.isOp() || p.hasPermission(Permission.VANISH.getPermission()) || p.hasPermission(Permission.SEE_VANISH.getPermission()) || p.hasPermission(Permission.STAFF.getPermission()) || p.hasPermission(Permission.ALL.getPermission()))){
            for (Player staff : Bukkit.getOnlinePlayers()){
                if (Methods.isPlayerVanished(staff)){
                    p.hidePlayer(MsBox.plugin, staff);
                }
            }
        }
    }
}
