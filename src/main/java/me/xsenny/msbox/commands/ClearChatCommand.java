package me.xsenny.msbox.commands;

import me.xsenny.msbox.utils.Color;
import me.xsenny.msbox.utils.Permission;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ClearChatCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)){

            for (int i = 0; i < 100; i++){
                for (Player ps : Bukkit.getOnlinePlayers()){
                    if (!ps.hasPermission(Permission.ALL.getPermission())){
                        ps.sendMessage(" ");
                    }
                }
            }

        }

        else{
            Player p = (Player) sender;
            if (!(p.hasPermission(Permission.CLEAR_CHAT.getPermission()) || p.hasPermission(Permission.STAFF.getPermission()) || p.hasPermission(Permission.ALL.getPermission()))){
                p.sendMessage("Nu ai permisiune");
                return true;
            }
            for (int i = 0; i < 100; i++)
                for (Player ps : Bukkit.getOnlinePlayers()){
                    if (!ps.hasPermission(Permission.ALL.getPermission())){
                        ps.sendMessage(" ");
                    }
                }
            p.sendMessage(Color.cc("&7Chat was cleared", true));
        }

        return true;
    }
}
