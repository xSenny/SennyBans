package me.xsenny.msbox.listeners;

import me.xsenny.msbox.MsBox;
import me.xsenny.msbox.database.Database;
import me.xsenny.msbox.methods.ShortMethods;
import me.xsenny.msbox.utils.Color;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import sun.jvm.hotspot.debugger.win32.coff.SectionHeader;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MuteListener implements Listener {

    public static void onChat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();
        if (ShortMethods.isPlayerMuted(p.getUniqueId().toString())){
            if (!MsBox.permMutes.contains(p.getUniqueId().toString())){
                long endOfBan = MsBox.currentTempMutes.get(e.getPlayer().getUniqueId().toString());
                long now = System.currentTimeMillis();
                long diff = endOfBan - now;
                if (diff <= 0){
                    MsBox.currentTempMutes.remove(e.getPlayer().getUniqueId());
                    Database.onUpdate("DELETE FROM MUTES WHERE uuid = \"" + e.getPlayer().getUniqueId().toString()+"\"");
                }else{
                    p.sendMessage(Color.cc("&cYou are muted for "+ShortMethods.getMessage(diff) + " because "+reasonOfMute(e.getPlayer().getUniqueId().toString())+ "by: "+getByWho(e.getPlayer().getUniqueId().toString()), true));
                    e.setCancelled(true);
                }
            }else{
                p.sendMessage(Color.cc("&c" + "You are permanently muted by "+getByWho(p.getUniqueId().toString()) + " because "+reasonOfMute(p.getUniqueId().toString()), true));
                e.setCancelled(true);
            }
        }
    }

    public static String getByWho(String uuid){
        ResultSet rs = Database.onQuery("SELECT * FROM MUTES WHERE uuid = '" + uuid+"'");
        String name = "";
        if (rs != null){
            while(true){
                try {
                    if (!rs.next()) break;
                    name = rs.getString("by_who");
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        return name;
    }

    public static String reasonOfMute(String uuid){
        ResultSet rs = Database.onQuery("SELECT * FROM MUTES WHERE uuid = '" + uuid+"'");
        String reason = "";
        if (rs != null){
            while(true){
                try {
                    if (!rs.next()) break;
                    reason = rs.getString("reason");
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        return reason;
    }

}
