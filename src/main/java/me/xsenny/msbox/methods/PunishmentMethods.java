package me.xsenny.msbox.methods;

import me.xsenny.msbox.MsBox;
import me.xsenny.msbox.utils.Color;
import me.xsenny.msbox.utils.PunishUnit;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


public class PunishmentMethods {

    public static MsBox plugin = MsBox.plugin;

    public static void tempBan(String name, String amount, String unit, Player player, String reason, Boolean silently){
        Player target = plugin.getServer().getPlayer(name);
        if (target == null || !target.isOnline()){
            String uuid = ShortMethods.getUUIDFromName(name);
            if (uuid == null){
                if (player != null){
                    player.sendMessage(Color.cc("&c " + name + " is never been online", true));
                    return;
                }else{
                    System.out.println( name + " is never been online");
                    return;
                }
            }
            if (MsBox.currentTempBans.containsKey(uuid)){
                if (player != null){
                    player.sendMessage(Color.cc("&7He is already tempbanned", true));
                    return;
                }else{
                    System.out.println("He is already tempbanned");
                    return;
                }
            }
            Integer integer;
            try{
                integer = Integer.parseInt(amount);
            }catch (NumberFormatException e){
                if (player != null){
                    player.sendMessage(Color.cc("&cUse a number", true));
                    return;
                }else{
                    System.out.println("Use a number");
                    return;
                }
            }
            long endOfban = System.currentTimeMillis() + PunishUnit.getTicks(unit, integer);
            long now = System.currentTimeMillis();
            long diff = endOfban - now;
            if (diff > 0){
                ShortMethods.setTempBanned(uuid, endOfban, reason, player, ShortMethods.getWhen(System.currentTimeMillis()));
                String message = ShortMethods.getMessage(endOfban);
                TextComponent message1= new TextComponent(Color.cc("Player " + name + " was banned", false));
                message1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GOLD + ShortMethods.getWhen(System.currentTimeMillis()))
                        .append(ChatColor.GRAY + "\n━━━━━━━━━━━━━━━━━━━━━━━\n").append(ChatColor.GRAY + "Reason: " + ChatColor.GOLD + reason)
                        .append(ChatColor.GRAY + "Time: " + ChatColor.GOLD + message)
                        .append(ChatColor.GRAY + "\n━━━━━━━━━━━━━━━━━━━━━━━")
                        .create()));
                Bukkit.getServer().spigot().broadcast(message1);
                target.kickPlayer("You are banned because of " + reason + " for " + message);
            }else{
                if (player != null){
                    player.sendMessage(Color.cc("&cYou can ban a player for minimum 1 m", true));
                    return;
                }else{
                    System.out.println("You can ban a player for minimum 1 m");
                    return;
                }
            }
            return;
        }
        if (target.isOp()){
            if (player != null){
                player.sendMessage(Color.cc("&7He is op", true));
                return;
            }else{
                System.out.println("He is op");
                return;
            }
        }
        if (MsBox.currentTempBans.containsKey(target.getUniqueId().toString())){
            if (player != null){
                player.sendMessage(Color.cc("&cHe is already tempbanned", true));
                return;
            }else{
                System.out.println("He is already tempbanned");
                return;
            }
        }
        Integer integer;
        try{
            integer = Integer.parseInt(amount);
        }catch (NumberFormatException e){
            if (player != null){
                player.sendMessage(Color.cc("Please use a number", true));
                return;
            }else{
                System.out.println("Please use a number");
                return;
            }
        }
        long endOfban = System.currentTimeMillis() + PunishUnit.getTicks(unit, integer);
        long now = System.currentTimeMillis();
        long diff = endOfban - now;
        if (diff > 0){
            ShortMethods.setTempBanned(target.getUniqueId().toString(), endOfban, reason, player, ShortMethods.getWhen(System.currentTimeMillis()));
            String message = ShortMethods.getMessage(endOfban);
            TextComponent message1= new TextComponent("Player " + name + " was banned");
            message1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GOLD + ShortMethods.getWhen(System.currentTimeMillis()))
                    .append(ChatColor.GRAY + "\n━━━━━━━━━━━━━━━━━━━━━━━\n").append(ChatColor.GRAY + "Reason: " + ChatColor.GOLD + reason)
                    .append(ChatColor.GRAY + "Time: " + ChatColor.GOLD + message)
                    .append(ChatColor.GRAY + "\n━━━━━━━━━━━━━━━━━━━━━━━")
                    .create()));
            Bukkit.getServer().spigot().broadcast(message1);
            target.kickPlayer("You are banned for "+message + " because of "+reason);
        }else{
            if (player != null){
                player.sendMessage(Color.cc("&cYou can ban a player for minimum 1 m", true));
            }else{
                System.out.println("You can ban a player for minimum 1 m");
            }
        }
    }

    public static void permBan(String name, Player player, String reason, Boolean silently){
        Player target = plugin.getServer().getPlayer(name);
        if (target == null || !target.isOnline()){
            String uuid = ShortMethods.getUUIDFromName(name);
            if (MsBox.permBans.contains(uuid)){
                if (player != null){
                    player.sendMessage(Color.cc("&c" + name + " is already banned" , true));
                    return;
                }else{
                    System.out.println("He is already banned");
                    return;
                }
            }
            if (uuid == null){
                if (player != null){
                    player.sendMessage(Color.cc("&c " + name + " is never been online", true));
                    return;
                }else{
                    System.out.println(name + " is never been online");
                    return;
                }
            }
            ShortMethods.setPermBanned(uuid, reason, player, ShortMethods.getWhen(System.currentTimeMillis()));
            TextComponent message1= new TextComponent("Player " + name + " was banned");
            message1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GOLD + ShortMethods.getWhen(System.currentTimeMillis()))
                    .append(ChatColor.GRAY + "\n━━━━━━━━━━━━━━━━━━━━━━━\n").append(ChatColor.GRAY + "Reason: " + ChatColor.GOLD + reason)
                    .append(ChatColor.GRAY + "Time: " + ChatColor.GOLD + "Permanent")
                    .append(ChatColor.GRAY + "\n━━━━━━━━━━━━━━━━━━━━━━━")
                    .create()));
            Bukkit.getServer().spigot().broadcast(message1);
            return;
        }
        if (MsBox.permBans.contains(target.getUniqueId().toString())){
            if (player != null){
                player.sendMessage(Color.cc("&cHe s already banned", true));
                return;
            }else{
                System.out.println("He is already banned");
                return;
            }
        }
        if (target.isOp()){
            if (player != null){
                player.sendMessage(Color.cc("&cHe is a server operator", true));
                return;
            }else{
                System.out.println("He is op");
                return;
            }
        }
        ShortMethods.setPermBanned(target.getUniqueId().toString(), reason, player, ShortMethods.getWhen(System.currentTimeMillis()));
        TextComponent message1= new TextComponent("Player " + name + " was banned");
        message1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GOLD + ShortMethods.getWhen(System.currentTimeMillis()))
                .append(ChatColor.GRAY + "\n━━━━━━━━━━━━━━━━━━━━━━━\n").append(ChatColor.GRAY + "Reason: " + ChatColor.GOLD + reason)
                .append(ChatColor.GRAY + "Time: " + ChatColor.GOLD + "Permanent")
                .append(ChatColor.GRAY + "\n━━━━━━━━━━━━━━━━━━━━━━━")
                .create()));
        Bukkit.getServer().spigot().broadcast(message1);
        target.kickPlayer("You are banned permanent " + " because of "+reason);
    }

    public static void permMute(String name, Player player, String reason, Boolean silently){
        Player target = plugin.getServer().getPlayer(name);
        if (target == null || !target.isOnline()){
            String uuid = ShortMethods.getUUIDFromName(name);
            if (uuid == null){
                if (player != null){
                    player.sendMessage(Color.cc("&c " + name + " is never been online", true));
                    return;
                }else{
                    System.out.println(name + " is never been online");
                    return;
                }
            }
            if (MsBox.permMutes.contains(uuid)){
                if (player != null){
                    player.sendMessage(Color.cc("He is already muted", true));
                    return;
                }else{
                    System.out.println("He is already muted.");
                    return;
                }
            }
            ShortMethods.setPermMute(uuid, reason, player, ShortMethods.getWhen(System.currentTimeMillis()));
            TextComponent message1= new TextComponent("Player " + name + " was muted");
            message1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GOLD + ShortMethods.getWhen(System.currentTimeMillis()))
                    .append(ChatColor.GRAY + "\n━━━━━━━━━━━━━━━━━━━━━━━\n").append(ChatColor.GRAY + "Reason: " + ChatColor.GOLD + reason)
                    .append(ChatColor.GRAY + "Time: " + ChatColor.GOLD + "Permanent")
                    .append(ChatColor.GRAY + "\n━━━━━━━━━━━━━━━━━━━━━━━")
                    .create()));
            Bukkit.getServer().spigot().broadcast(message1);
            return;
        }
        if (target.isOp()){
            if (player != null){
                player.sendMessage(Color.cc("He is op, you can t mute him", true));
                return;
            }else{
                System.out.println("He is op.");
                return;
            }
        }
        if (MsBox.permMutes.contains(target.getUniqueId().toString())){
            if (player != null){
                player.sendMessage(Color.cc("He is already muted", true));
                return;
            }else{
                System.out.println("He is already muted.");
                return;
            }
        }
        ShortMethods.setPermMute(target.getUniqueId().toString(), reason, player, ShortMethods.getWhen(System.currentTimeMillis()));
        TextComponent message1= new TextComponent("Player " + name + " was muted");
        message1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GOLD + ShortMethods.getWhen(System.currentTimeMillis()))
                .append(ChatColor.GRAY + "\n━━━━━━━━━━━━━━━━━━━━━━━\n").append(ChatColor.GRAY + "Reason: " + ChatColor.GOLD + reason)
                .append(ChatColor.GRAY + "Time: " + ChatColor.GOLD + "Permanent")
                .append(ChatColor.GRAY + "\n━━━━━━━━━━━━━━━━━━━━━━━")
                .create()));
        Bukkit.getServer().spigot().broadcast(message1);
        target.sendMessage("You were muted permanently because of "+reason);
    }

    public static void tempMute(String name, String amount, String unit, Player player, String reason, Boolean silently){
        Player target = plugin.getServer().getPlayer(name);
        if (target == null || !target.isOnline()){
            String uuid = ShortMethods.getUUIDFromName(name);
            if (uuid == null){
                if (player != null){
                    player.sendMessage(Color.cc("&c " + name + " is never been online", true));
                    return;
                }else{
                    System.out.println(name + " is never been online");
                    return;
                }
            }
            if (MsBox.currentTempMutes.containsKey(uuid)){
                if (player != null){
                    player.sendMessage(Color.cc("He is already tempmuted", true));
                    return;
                }else{
                    System.out.println("He is already tempmuted.");
                    return;
                }
            }
            Integer integer;
            try{
                integer = Integer.parseInt(amount);
            }catch (NumberFormatException e){
                if (player != null){
                    player.sendMessage(Color.cc("&7Please use a valid number.", true));
                    return;
                }else{
                    System.out.println("Please use a valid number");
                    return;
                }
            }
            long endOfMute = System.currentTimeMillis() + PunishUnit.getTicks(unit, integer);
            long now = System.currentTimeMillis();
            long diff = endOfMute - now;
            if (diff > 0){
                ShortMethods.setTempMute(uuid, reason, player, endOfMute, ShortMethods.getWhen(System.currentTimeMillis()));
                String message = ShortMethods.getMessage(endOfMute);
                TextComponent message1= new TextComponent("Player " + name + " was muted");
                message1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GOLD + ShortMethods.getWhen(System.currentTimeMillis()))
                        .append(ChatColor.GRAY + "\n━━━━━━━━━━━━━━━━━━━━━━━\n").append(ChatColor.GRAY + "Reason: " + ChatColor.GOLD + reason)
                        .append(ChatColor.GRAY + "Time: " + ChatColor.GOLD + message)
                        .append(ChatColor.GRAY + "\n━━━━━━━━━━━━━━━━━━━━━━━")
                        .create()));
                Bukkit.getServer().spigot().broadcast(message1);
            }else{
                if (player != null){
                    player.sendMessage(Color.cc("&cYou can ban a player for minimum 1 m", true));
                    return;
                }else{
                    System.out.println("You can ban a player for minimum 1 m");
                    return;
                }
            }
            return;
        }
        if (target.isOp()){
            if (player != null){
                player.sendMessage(Color.cc("He is op", true));
                return;
            }else{
                System.out.println("He is op");
                return;
            }
        }
        if (MsBox.currentTempMutes.containsKey(target.getUniqueId().toString())){
            if (player != null){
                player.sendMessage(Color.cc("He is already tempmuted.", true));
                return;
            }else{
                System.out.println("He is already tempmuted");
                return;
            }
        }
        Integer integer;
        try{
            integer = Integer.parseInt(amount);
        }catch (NumberFormatException e){
            if (player != null){
                player.sendMessage(Color.cc("Please use a valid number", true));
                return;
            }else{
                System.out.println("Please use a valid number.");
                return;
            }
        }
        long endOfMute = System.currentTimeMillis() + PunishUnit.getTicks(unit, integer);
        long now = System.currentTimeMillis();
        long diff = endOfMute - now;
        if (diff > 0){
            ShortMethods.setTempMute(target.getUniqueId().toString(), reason, player, endOfMute, ShortMethods.getWhen(System.currentTimeMillis()));
            String message = ShortMethods.getMessage(endOfMute);
            TextComponent message1= new TextComponent("Player " + name + " was muted");
            message1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GOLD + ShortMethods.getWhen(System.currentTimeMillis()))
                    .append(ChatColor.GRAY + "\n━━━━━━━━━━━━━━━━━━━━━━━\n").append(ChatColor.GRAY + "Reason: " + ChatColor.GOLD + reason)
                    .append(ChatColor.GRAY + "Time: " + ChatColor.GOLD + message)
                    .append(ChatColor.GRAY + "\n━━━━━━━━━━━━━━━━━━━━━━━")
                    .create()));
            Bukkit.getServer().spigot().broadcast(message1);
            target.sendMessage("You were muted by "+player.getName()+" for "+message+", because "+reason);
        }else{
            if (player != null){
                player.sendMessage(Color.cc("&cYou can ban a player for minimum 1 m", true));
            }else{
                System.out.println("You can ban a player for minimum 1 m");
            }
        }
    }

    public static void kickAPlayer(String name, Player who, String reason, Boolean silently){
        Player target = plugin.getServer().getPlayer(name);
        if (target == null || !target.isOnline()){
            if (who != null){
                who.sendMessage(Color.cc("The player is not online", true));
                return;
            }else{
                System.out.println("The player is not online");
            }
        }
        if (target.isOp()){
            if (who != null){
                who.sendMessage(Color.cc("&cHe is a server operator.", true));
                return;
            }else{
                System.out.println("He is a server operator.");
                return;
            }
        }
        target.kickPlayer("Kicked by "+ who.getName()+" because: "+reason);
        ShortMethods.setKick(target.getUniqueId().toString(), reason, who, ShortMethods.getWhen(System.currentTimeMillis()));
        TextComponent message1= new TextComponent("Player " + name + " was kicked");
        message1.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.GOLD + ShortMethods.getWhen(System.currentTimeMillis()))
                .append(ChatColor.GRAY + "\n━━━━━━━━━━━━━━━━━━━━━━━\n").append(ChatColor.GRAY + "Reason: " + ChatColor.GOLD + reason)
                .append(ChatColor.GRAY + "\n━━━━━━━━━━━━━━━━━━━━━━━")
                .create()));
        Bukkit.getServer().spigot().broadcast(message1);
    }

}
