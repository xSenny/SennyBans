package me.xsenny.msbox.staffUtilities.vanishMode;

import me.xsenny.msbox.utils.Color;
import me.xsenny.msbox.utils.Permission;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class VanishCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)){
            System.out.println("At the moment, the command isnt available for console");
            return true;
        }

        Player p = (Player) sender;
        if (args.length == 0){
            if (p.hasPermission(Permission.ALL.getPermission()) || p.isOp() || p.hasPermission(Permission.STAFF.getPermission()) || p.hasPermission(Permission.VANISH.getPermission())){
                Methods.toggleVanishMode(p);
                return true;
            }
            p.sendMessage(Color.cc("&cYou do not have permission to use this command.", true));
            return true;
        }
        if (args.length == 1){
            if (p.hasPermission(Permission.ALL.getPermission()) || p.isOp() || p.hasPermission(Permission.STAFF.getPermission()) || p.hasPermission(Permission.VANISH_OTHER.getPermission())){
                Player who = Bukkit.getPlayer(args[0]);
                if (who == null || !who.isOnline()){
                    p.sendMessage(Color.cc("&cHe is not online.", true));
                    return true;
                }
                if (Methods.isPlayerVanished(who)){
                    Methods.toggleVanishMode(who);
                    p.sendMessage(Color.cc("&b" + args[0]+" is now unvanished", true));
                    return true;
                }
                if (!Methods.isPlayerVanished(who)){
                    Methods.toggleVanishMode(who);
                    p.sendMessage(Color.cc("&b" + args[0] +" is now vanished", true));
                    return true;
                }
            }
            p.sendMessage(Color.cc("&cYou do not have permission to use this command", true));
            return true;
        }
        p.sendMessage(Color.cc("&7" + "/v or /v <player>", true));
        return true;
    }
}
