package me.xsenny.msbox.commands;

import me.xsenny.msbox.MsBox;
import me.xsenny.msbox.methods.PunishmentMethods;
import me.xsenny.msbox.utils.Color;
import me.xsenny.msbox.utils.Permission;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class permBanCommand implements CommandExecutor {

    public static MsBox plugin = MsBox.plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player p = (Player) sender;
            if (p.isOp() || p.hasPermission(Permission.BAN.getPermission()) || p.hasPermission(Permission.STAFF.getPermission()) || p.hasPermission(Permission.ALL.getPermission())){
                if (args.length == 0){
                    p.sendMessage(Color.cc("&c/ban <player> {reason}", true));
                    return true;
                }
                if (args.length == 1){
                    String nameOfPlayer = args[0];
                    PunishmentMethods.permBan(nameOfPlayer, p, "No reason", false);
                    return true;
                }
                Boolean silent = false;
                StringBuffer sb = new StringBuffer();
                int i = 0;
                for (String strings : args){
                    if (i == 0){
                        i = 1;
                    }
                    else if (strings.equals("-s")){
                        silent = true;
                    }else{
                        sb.append(strings).append(" ");
                    }
                }PunishmentMethods.permBan(args[0], p, sb.toString(), silent);
            }else{
                p.sendMessage(Color.cc("&cYou do not have permission to ban someone permanently", true));
            }
        }else{
            if (args.length == 0){
                System.out.println("/ban <name> {reason}");
                return true;
            }
            if (args.length == 1){
                String nameOfPlayer = args[0];
                PunishmentMethods.permBan(nameOfPlayer, null, "No reason", false);
                return true;
            }
            Boolean silent = false;
            StringBuffer sb = new StringBuffer();
            int i = 0;
            for (String strings : args){
                if (i == 0){
                    i = 1;
                }
                else if (strings.equals("-s")){
                    silent = true;
                }else{
                    sb.append(strings).append(" ");
                }
            }PunishmentMethods.permBan(args[0], null, sb.toString(), silent);
        }

        return true;
    }
}
