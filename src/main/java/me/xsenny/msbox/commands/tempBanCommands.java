package me.xsenny.msbox.commands;

import me.xsenny.msbox.methods.PunishmentMethods;
import me.xsenny.msbox.methods.ShortMethods;
import me.xsenny.msbox.utils.Color;
import me.xsenny.msbox.utils.Permission;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class tempBanCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player p = (Player) sender;
            if (p.isOp() || p.hasPermission(Permission.TEMPBAN.getPermission()) || p.hasPermission(Permission.ALL.getPermission()) || p.hasPermission(Permission.STAFF.getPermission())){
                if (args.length == 3){
                    String playerName = args[0];
                    String amount = args[1];
                    String unit = args[2];
                    PunishmentMethods.tempBan(playerName, amount, unit, (Player) sender, "No reason", false);
                }else if (args.length == 4){
                    String playerName = args[0];
                    String amount = args[1];
                    String unit = args[2];
                    if (args[3].equals("-s")){
                        Boolean silently = true;
                        PunishmentMethods.tempBan(playerName, amount, unit, (Player) sender, "No reason", silently);
                    }else{
                        String reason = args[3];
                        PunishmentMethods.tempBan(playerName, amount, unit, (Player) sender, reason, false);
                    }
                }else if (args.length > 4){
                    String playerName = args[0];
                    String amount = args[1];
                    String unit = args[2];
                    Boolean silently = false;
                    StringBuilder sb = new StringBuilder();
                    for (int i = 3; i < args.length; i++){
                        if (!args[i].equals("-s")){
                            sb.append(args[i]).append(" ");
                        }else{
                            silently = true;
                        }
                    }
                    PunishmentMethods.tempBan(playerName, amount, unit, (Player) sender, sb.toString(), silently);
                }else{
                    sender.sendMessage(Color.cc("&7/tempban <player> <amount> <s/m/h/d/w/month/year> {reason}", true));
                }
            }else{
                p.sendMessage(Color.cc("&cOnly a staff can use this command.", true));
            }
        }else{
            if (args.length == 3){
                String playerName = args[0];
                String amount = args[1];
                String unit = args[2];
                PunishmentMethods.tempBan(playerName, amount, unit, null, "No reason", false);
            }else if (args.length == 4){
                String playerName = args[0];
                String amount = args[1];
                String unit = args[2];
                if (args[3].equals("-s")){
                    Boolean silently = true;
                    PunishmentMethods.tempBan(playerName, amount, unit, null, "No reason", silently);
                }else{
                    String reason = args[3];
                    PunishmentMethods.tempBan(playerName, amount, unit, null, reason, false);
                }
            }else if (args.length > 4){
                String playerName = args[0];
                String amount = args[1];
                String unit = args[2];
                Boolean silently = false;
                StringBuilder sb = new StringBuilder();
                for (int i = 3; i < args.length; i++){
                    if (!args[i].equals("-s")){
                        sb.append(args[i]).append(" ");
                    }else{
                        silently = true;
                    }
                }
                PunishmentMethods.tempBan(playerName, amount, unit, null, sb.toString(), silently);
            }else{
                sender.sendMessage("/tempban <player> <amount> <s/m/h/d/w/month/year> {reason}");
            }
        }

        return true;
    }
}
