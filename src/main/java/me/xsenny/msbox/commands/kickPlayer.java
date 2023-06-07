package me.xsenny.msbox.commands;

import me.xsenny.msbox.methods.PunishmentMethods;
import me.xsenny.msbox.utils.Color;
import me.xsenny.msbox.utils.Permission;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class kickPlayer implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player p = (Player) sender;
            if (p.isOp() || p.hasPermission(Permission.KICK.getPermission()) || p.hasPermission(Permission.STAFF.getPermission()) || p.hasPermission(Permission.ALL.getPermission())){
                if (args.length == 0){
                    p.sendMessage(Color.cc("&c/kick <player> {reason}", true));
                    return true;
                }
                if (args.length == 1){
                    PunishmentMethods.kickAPlayer(args[0], p, "no reason", false);
                    return true;
                }
                String playerName = args[0];
                boolean silently = false;
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i < args.length; i++){
                    if (!args[i].equals("-s")){
                        sb.append(args[i]).append(" ");
                    }else{
                        silently = true;
                    }
                }
                PunishmentMethods.kickAPlayer(playerName, p, sb.toString(), silently);
            }else{
                sender.sendMessage(Color.cc("&cYou do not have permission to kick someone", true));
            }
        }else{

            if (args.length == 0){
                System.out.println("/kick <player> {reason}");
                return true;
            }
            if (args.length == 1){
                PunishmentMethods.kickAPlayer(args[0], null, "no reason", false);
                return true;
            }
            String playerName = args[0];
            boolean silently = false;
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < args.length; i++){
                if (!args[i].equals("-s")){
                    sb.append(args[i]).append(" ");
                }else{
                    silently = true;
                }
            }
            PunishmentMethods.kickAPlayer(playerName, null, sb.toString(), silently);

        }

        return true;
    }
}
