package me.xsenny.msbox.methods;

import me.xsenny.msbox.MsBox;
import me.xsenny.msbox.database.Database;
import me.xsenny.msbox.utils.PunishmentType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ShortMethods {

    public static void setTempBanned(String uuid, Long endofban, String reason, Player who, String where){
        MsBox.currentTempBans.put(uuid, endofban);
        if (who != null){
            Database.onUpdate("INSERT INTO BANS VALUES(\""+uuid+"\", \"0\", "+endofban+", \""+reason+"\", 0, \""+who
                    .getName()+"\", \""+where +"\")");
            Database.onUpdate("INSERT INTO PLAYERLOG VALUES(\""+uuid+"\", \""+ PunishmentType.TEMPBAN.getType()+"\", \""+getWhen(System.currentTimeMillis())+"\", \""+who.getName()+"\", \"" + getMessage(endofban) +"\", \"" + reason+"\")");
        }else{
            Database.onUpdate("INSERT INTO BANS VALUES(\""+uuid+"\", \"0\", "+endofban+", \""+reason+"\", 0, \""+"Console"+"\", \""+where +"\")");
            Database.onUpdate("INSERT INTO PLAYERLOG VALUES(\""+uuid+"\", \""+ PunishmentType.TEMPBAN.getType()+"\", \""+getWhen(System.currentTimeMillis())+"\", \""+"Console"+"\", \"" + getMessage(endofban) +"\", \"" + reason+"\")");
        }
    }

    public static void unbanAPlayer(String uuid, Player who){
        if (MsBox.permBans.contains(uuid)) MsBox.permBans.remove(uuid);
        else if (MsBox.currentTempBans.containsKey(uuid) && MsBox.currentTempBans.get(uuid) != null) MsBox.currentTempBans.remove(uuid);
        if (who != null){
            Database.onUpdate("INSERT INTO PLAYERLOG VALUES(\""+uuid+"\", \""+ PunishmentType.UNBAN.getType()+"\", \""+getWhen(System.currentTimeMillis())+"\", \""+who.getName()+"\", \"" + "nothing" +"\", \"" +"unbanned"+"\")");
        }else{
            Database.onUpdate("INSERT INTO PLAYERLOG VALUES(\""+uuid+"\", \""+ PunishmentType.UNBAN.getType()+"\", \""+getWhen(System.currentTimeMillis())+"\", \""+"Console"+"\", \"" + "nothing" +"\", \"" +"unbanned"+"\")");
        }
        Database.onUpdate("DELETE FROM BANS WHERE uuid = \"" + uuid+"\"");
    }

    public static void unmuteAPlayer(String uuid, Player who){
        if (MsBox.permMutes.contains(uuid)) MsBox.permMutes.remove(uuid);
        else if (MsBox.currentTempMutes.containsKey(uuid) && MsBox.currentTempMutes.get(uuid) != null) MsBox.currentTempMutes.remove(uuid);
        if (who != null) {
            Database.onUpdate("INSERT INTO PLAYERLOG VALUES(\""+uuid+"\", \""+ PunishmentType.UNMUTE.getType()+"\", \""+getWhen(System.currentTimeMillis())+"\", \""+who.getName()+"\", \"" + "nothing" +"\", \"" +"unbanned"+"\")");
        }else{
            Database.onUpdate("INSERT INTO PLAYERLOG VALUES(\""+uuid+"\", \""+ PunishmentType.UNMUTE.getType()+"\", \""+getWhen(System.currentTimeMillis())+"\", \""+"Console"+"\", \"" + "nothing" +"\", \"" +"unbanned"+"\")");
        }
        Database.onUpdate("DELETE FROM MUTES WHERE uuid = \"" + uuid+"\"");
    }

    public static void sendSilentlyMessage(String message){
        for (Player p : Bukkit.getOnlinePlayers()){
            if (p.isOp()){
                p.sendMessage(message);
            }
        }
    }

    public static void setPermBanned(String uuid, String reason, Player who, String where){
        MsBox.permBans.add(uuid);
        if (who != null){
            Database.onUpdate("INSERT INTO BANS VALUES(\""+uuid+"\", 1, NULL, \""+reason+"\", 0, \""+who.getName()+"\", \""+where+"\")");
            Database.onUpdate("INSERT INTO PLAYERLOG VALUES(\""+uuid+"\", \""+ PunishmentType.BAN.getType()+"\", \""+getWhen(System.currentTimeMillis())+"\", \""+who.getName()+"\", \"" + "Permanent" +"\", \"" + reason+"\")");
        }else{
            Database.onUpdate("INSERT INTO BANS VALUES(\""+uuid+"\", 1, NULL, \""+reason+"\", 0, \""+"Console"+"\", \""+where+"\")");
            Database.onUpdate("INSERT INTO PLAYERLOG VALUES(\""+uuid+"\", \""+ PunishmentType.BAN.getType()+"\", \""+getWhen(System.currentTimeMillis())+"\", \""+"Console"+"\", \"" + "Permanent" +"\", \"" + reason+"\")");
        }
    }

    public static void setPermMute(String uuid, String reason, Player who, String where){
        MsBox.permMutes.add(uuid);
        if (who != null){
            Database.onUpdate("INSERT INTO MUTES VALUES(\""+uuid+"\", 1, NULL, \""+reason+"\", 0, \""+who.getName()+"\", \"" + where +"\")");
            Database.onUpdate("INSERT INTO PLAYERLOG VALUES(\""+uuid+"\", \""+ PunishmentType.MUTE.getType()+"\", \""+getWhen(System.currentTimeMillis())+"\", \""+who.getName()+"\", \"" + "Permanent" +"\", \"" + reason+"\")");
        }else{
            Database.onUpdate("INSERT INTO MUTES VALUES(\""+uuid+"\", 1, NULL, \""+reason+"\", 0, \""+"Console"+"\", \"" + where +"\")");
            Database.onUpdate("INSERT INTO PLAYERLOG VALUES(\""+uuid+"\", \""+ PunishmentType.MUTE.getType()+"\", \""+getWhen(System.currentTimeMillis())+"\", \""+"Console"+"\", \"" + "Permanent" +"\", \"" + reason+"\")");
        }
    }

    public static void setTempMute(String uuid, String reason, Player who, Long endOfMute, String where){
        MsBox.currentTempMutes.put(uuid, endOfMute);
        if (who != null){
            Database.onUpdate("INSERT INTO MUTES VALUES(\""+uuid+"\", \"0\", "+endOfMute+", \""+reason+"\", 0, \""+who
                    .getName()+"\", \""+where+"\")");
            Database.onUpdate("INSERT INTO PLAYERLOG VALUES(\""+uuid+"\", \""+ PunishmentType.TEMPMUTE.getType()+"\", \""+getWhen(System.currentTimeMillis())+"\", \""+who.getName()+"\", \"" + getMessage(endOfMute) +"\", \"" + reason+"\")");
        }else{
            Database.onUpdate("INSERT INTO MUTES VALUES(\""+uuid+"\", \"0\", "+endOfMute+", \""+reason+"\", 0, \""+"Console"+"\", \""+where+"\")");
            Database.onUpdate("INSERT INTO PLAYERLOG VALUES(\""+uuid+"\", \""+ PunishmentType.TEMPMUTE.getType()+"\", \""+getWhen(System.currentTimeMillis())+"\", \""+"Console"+"\", \"" + getMessage(endOfMute) +"\", \"" + reason+"\")");
        }
    }

    public static void setKick(String uuid, String reason, Player who, String where){
        if (who != null){
            Database.onUpdate("INSERT INTO PLAYERLOG VALUES(\""+uuid+"\", \""+ PunishmentType.KICK.getType()+"\", \""+getWhen(System.currentTimeMillis())+"\", \""+who.getName()+"\", \"" + "0" +"\", \"" + reason+"\")");
        }else{
            Database.onUpdate("INSERT INTO PLAYERLOG VALUES(\""+uuid+"\", \""+ PunishmentType.KICK.getType()+"\", \""+getWhen(System.currentTimeMillis())+"\", \""+"Console"+"\", \"" + "0" +"\", \"" + reason+"\")");
        }
    }

    public static boolean isPlayerMuted(String uuid){
        if (MsBox.currentTempMutes.containsKey(uuid)){
            if (MsBox.currentTempMutes.get(uuid) != null){
                return true;
            }
        }if (MsBox.permMutes.contains(uuid)){
            return true;
        }
        return false;
    }

    public static boolean isPlayerBanned(String uuid){
        if (MsBox.currentTempBans.containsKey(uuid)){
            if (MsBox.currentTempBans.get(uuid) != null){
                return true;
            }
        }
        if (MsBox.permBans.contains(uuid)){
            return true;
        }
        return false;
    }

    public static String getMessage(long endOfBan){
        String message = "";
        endOfBan += 1000;
        long now = System.currentTimeMillis();
        long diff = endOfBan - now;
        int seconds = (int) (diff / 1000);

        if(seconds >= 60*60*24){
            int days = seconds / (60*60*24);
            seconds = seconds % (60*60*24);

            message += days + " Day(s) ";
        }
        if(seconds >= 60*60){
            int hours = seconds / (60*60);
            seconds = seconds % (60*60);

            message += hours + " Hour(s) ";
        }
        if(seconds >= 60){
            int min = seconds / 60;
            seconds = seconds % 60;

            message += min + " Minute(s) ";
        }
        if(seconds > 0){
            message += seconds + " Second(s) ";
        }

        return message;
    }


    public static String getWhen(long millis){
        Date date = new Date(millis);
        DateFormat df = new SimpleDateFormat("yy:MM:dd HH:mm");
        return df.format(date);
    }

    public static String getUUIDFromName(String name){
        ResultSet rs = Database.onQuery("SELECT * FROM PLAYERS WHERE name = \"" + name + "\"");
        if (rs != null){
            try{
                if (rs.next())
                    return rs.getString("uuid");
            }catch (SQLException e){
                System.out.println(e);
            }
        }
        return null;
    }

}
