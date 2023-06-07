package me.xsenny.msbox.listeners;

import me.xsenny.msbox.MsBox;
import me.xsenny.msbox.database.Database;
import me.xsenny.msbox.methods.ShortMethods;
import me.xsenny.msbox.utils.Color;
import me.xsenny.msbox.utils.Permission;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BanListener implements Listener {

    @EventHandler
    public static void onJoin(PlayerLoginEvent e){
        if (ShortMethods.isPlayerBanned(e.getPlayer().getUniqueId().toString())){
            if (!MsBox.permBans.contains(e.getPlayer().getUniqueId().toString())){
                long endOfBan = MsBox.currentTempBans.get(e.getPlayer().getUniqueId().toString());
                long now = System.currentTimeMillis();
                long diff = endOfBan - now;
                if (diff <= 0){
                    MsBox.currentTempBans.remove(e.getPlayer().getUniqueId());
                    Database.onUpdate("DELETE FROM BANS WHERE uuid = \"" + e.getPlayer().getUniqueId().toString()+"\"");
                }else{
                    e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "You are banned for "+ShortMethods.getMessage(endOfBan) + " because "+reasonOfBan(e.getPlayer().getUniqueId().toString())+ " by: "+getByWho(e.getPlayer().getUniqueId().toString()));
                }
            }else{
                e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "You are banned permanently because "+reasonOfBan(e.getPlayer().getUniqueId().toString())+" by: "+getByWho(e.getPlayer().getUniqueId().toString()));
            }
            for (Player p : Bukkit.getOnlinePlayers()){
                if (p.hasPermission(Permission.ALL.getPermission()) || p.hasPermission(Permission.STAFF.getPermission())){
                    p.sendMessage(Color.cc("&cPlayer " + e.getPlayer().getName() + " has tried to join, but he's banned.", true));
                }
            }
        }
    }

    public static String getByWho(String uuid){
        ResultSet rs = Database.onQuery("SELECT * FROM BANS WHERE uuid = '" + uuid+"'");
        String name = "";
        if (rs != null){
            try{
                if (rs.next())
                    name = rs.getString("by_who");
            }catch (SQLException e){
                System.out.println(e);
            }
        }
        return name;
    }

    public static String reasonOfBan(String uuid){
        ResultSet rs = Database.onQuery("SELECT * FROM BANS WHERE uuid = \"" + uuid+"\"");
        String reason = "";
        if (rs != null){
            try{
                if (rs.next()) {
                    reason = rs.getString("reason");
                }
            }catch (SQLException e){
                System.out.println(e);
            }
        }
        return reason;
    }

}
