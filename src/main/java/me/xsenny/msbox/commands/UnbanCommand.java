package me.xsenny.msbox.commands;

import me.xsenny.msbox.methods.ShortMethods;
import me.xsenny.msbox.utils.Color;
import me.xsenny.msbox.utils.Permission;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class UnbanCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player)){
            if (args.length != 1){
                System.out.println("/unban <player>");
                return true;
            }
            String uuid = ShortMethods.getUUIDFromName(args[0]);
            if (!ShortMethods.isPlayerBanned(uuid)){
                System.out.println(args[0] + " is not banned");
                return true;
            }
            ShortMethods.unbanAPlayer(uuid, null);
            Bukkit.broadcastMessage(Color.cc("&6The player " + args[0] + " was unbanned by Console", false));
            return true;
        }
        Player p = (Player) sender;
        if (args.length != 1){
            p.sendMessage(Color.cc("&7/unban <player>", true));
            return true;
        }
        String uuid = ShortMethods.getUUIDFromName(args[0]);
        if (!ShortMethods.isPlayerBanned(uuid)){
            p.sendMessage(Color.cc("&7" + args[0] + " is not banned", true));
            return true;
        }
        if (!(p.hasPermission(Permission.ALL.getPermission()) || p.hasPermission(Permission.UNBAN.getPermission()) || p.hasPermission(Permission.STAFF.getPermission()))){
            p.sendMessage(Color.cc("Only a staff member can do this.", true));
            return true;
        }
        ShortMethods.unbanAPlayer(uuid, p);
        Bukkit.broadcastMessage(Color.cc("&6" + "The player " + args[0] + " was unbanned by "+p.getName(), false));
        return true;
    }
}
