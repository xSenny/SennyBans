package me.xsenny.msbox.staffUtilities;

import me.xsenny.msbox.utils.Color;
import me.xsenny.msbox.utils.Permission;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Teleport implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)){
            if (args.length == 2){
                    Player who = Bukkit.getPlayer(args[0]);
                    Player to = Bukkit.getPlayer(args[1]);
                    if (who == null || !who.isOnline()){
                        System.out.println(args[0] + " isnt online");
                        return true;
                    }
                    if (to == null || !to.isOnline()){
                        System.out.println(args[1] + " isnt online");
                        return true;
                    }
                    who.teleport(to.getLocation());
                System.out.println(args[0] + "was teleported to "+args[1] + "'s location");
                    return true;
                }
            return true;
        }

        Player p = (Player) sender;

        if (args.length == 1){
            if (p.hasPermission(Permission.ALL.getPermission())
            ||p.hasPermission(Permission.TELEPORT.getPermission()) || p.hasPermission(Permission.STAFF.getPermission())
            || p.isOp()){
                Player to = Bukkit.getPlayer(args[0]);
                if (to == null || !to.isOnline()){
                    p.sendMessage(Color.cc("&c" + args[0] + " isnt online", true));
                    return true;
                }
                p.teleport(to.getLocation());
                p.sendMessage(Color.cc("&b" + "You were teleported to "+ args[0], true));
                return true;
            }
            p.sendMessage(Color.cc("&cYou do not have permission to use this command.", true));
            return true;
        }
        if (args.length == 2){
            if (p.hasPermission(Permission.ALL.getPermission())
                    ||p.hasPermission(Permission.TELEPORT_OTHER.getPermission()) || p.hasPermission(Permission.STAFF.getPermission())
                    || p.isOp()){
                Player who = Bukkit.getPlayer(args[0]);
                Player to = Bukkit.getPlayer(args[1]);
                if (who == null || !who.isOnline()){
                    p.sendMessage(Color.cc("&c" + args[0] + " isnt online", true));
                    return true;
                }
                if (to == null || !to.isOnline()){
                    p.sendMessage(Color.cc("&c" + args[1] + " isnt online", true));
                    return true;
                }
                who.teleport(to.getLocation());
                p.sendMessage(Color.cc("&b"+args[0] + "was teleported to "+args[1] + "'s location", true));
                return true;
            }
            p.sendMessage(Color.cc("&cYou do not have permission to use this command", true));
            return true;
        }else{
            p.sendMessage(Color.cc("&7" + "/teleport <who> <to> or /teleport <to>", true));
        }
        return true;
    }
}
