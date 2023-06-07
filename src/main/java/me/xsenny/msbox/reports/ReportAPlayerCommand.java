package me.xsenny.msbox.reports;

import me.xsenny.msbox.MsBox;
import me.xsenny.msbox.methods.ShortMethods;
import me.xsenny.msbox.utils.Color;
import me.xsenny.msbox.utils.Permission;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReportAPlayerCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)){
            System.out.println("This command can be used only by a player");
            return true;
        }
        Player player = (Player) sender;
        if (args.length < 2){
            player.sendMessage(Color.cc("/report <name> <reason>", true));
            return true;
        }
        Player who = MsBox.plugin.getServer().getPlayer(args[0]);
        if (who == null || !who.isOnline()){
            player.sendMessage(Color.cc("This player is not online, you can report it only when he will be online", true));
            return true;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < args.length; i++){
            sb.append(args[i]).append(" ");
        }
        ReportsMethods.createReport(player, who, sb.toString());
        for (Player p : Bukkit.getOnlinePlayers()){
            if (p.hasPermission(Permission.ALL.getPermission()) || p.hasPermission(Permission.STAFF.getPermission()) || p.hasPermission(Permission.VIEW_REPORTS.getPermission())){
                p.sendMessage(Color.cc("&7" + "Player "+ player.getName()+" reported " +who.getName()+" because of "+sb, true));
            }
        }
        return true;
    }
}
