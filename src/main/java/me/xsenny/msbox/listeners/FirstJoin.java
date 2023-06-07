package me.xsenny.msbox.listeners;

import me.xsenny.msbox.database.Database;
import me.xsenny.msbox.utils.Color;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FirstJoin implements Listener {

    @EventHandler
    public static void onFirstJoin(PlayerJoinEvent e){
        ResultSet rs = Database.onQuery("SELECT * FROM PLAYERS WHERE uuid = \"" + e.getPlayer().getUniqueId().toString()+"\"");
        boolean is = false;
        if (rs != null){
            while(true){
                try {
                    if (rs.next()){
                        is = true;
                        String name = rs.getString("name");
                        if (!name.equals(e.getPlayer().getName()))
                            Database.onUpdate("UPDATE PLAYERS SET name = " + e.getPlayer().getName() + " WHERE uuid = \"" + e.getPlayer().getUniqueId()+"\"");
                        break;
                    }else{
                        break;
                    }
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }
        }
        if (!is){
            Database.onUpdate("INSERT INTO PLAYERS(uuid, name) VALUES(\""+e.getPlayer().getUniqueId().toString()+"\", \""+e.getPlayer().getName()+"\")");
            ResultSet rs1 = Database.onQuery("SELECT * FROM PLAYERS WHERE uuid = \"" + e.getPlayer().getUniqueId().toString()+"\"");
            while(true){
                try {
                    if (rs1.next()){
                        int id = rs1.getInt("al");
                        Bukkit.broadcastMessage(Color.cc("&6Player " + e.getPlayer().getName() + " has joined for his first time #" + id, true));
                        break;
                    }else{
                        break;
                    }
                } catch (SQLException ex) {
                    System.out.println(ex);
                }
            }

            Bukkit.dispatchCommand(e.getPlayer(), "spawn");

        }
    }
}
