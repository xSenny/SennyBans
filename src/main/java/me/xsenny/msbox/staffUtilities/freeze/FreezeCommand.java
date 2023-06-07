package me.xsenny.msbox.staffUtilities.freeze;

import me.xsenny.msbox.MsBox;
import me.xsenny.msbox.utils.Color;
import me.xsenny.msbox.utils.Permission;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

public class FreezeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)){
            sender.sendMessage("Error, at the moment, this command is supported only for players.");
            return true;
        }
        Player p = (Player) sender;
        if (args.length != 1){
            p.sendMessage("/freeze <player>");
            return true;
        }

        if (!(p.hasPermission(Permission.ALL.getPermission()) || p.hasPermission(Permission.STAFF.getPermission()) || p.hasPermission(Permission.FREEZE.getPermission()))){
            p.sendMessage(Color.cc("&cYou do not have permission to do it.", true));
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (!target.isOnline() || target == null){
            p.sendMessage(Color.cc("&c" + args[0] + " isn't online right now.", true));
            return true;
        }

        if (freezeAPlayer(target)){
            target.sendMessage(Color.cc("&b" + "You were freezed by " + p.getName(), true));
            p.sendMessage(Color.cc("&b" + "You freeed " + target.getName(), true));
            return true;
        }
        if (!freezeAPlayer(target)){
            target.sendMessage(Color.cc("&6" + "You were unfreezed by " + p.getName(), true));
            p.sendMessage(Color.cc("&6" + "You freezed " + target.getName(), true));
            return true;
        }

        return true;
    }

    public static boolean freezeAPlayer(Player p){
        NamespacedKey namespacedKey = new NamespacedKey(MsBox.plugin, "freeze");
        if (!p.getPersistentDataContainer().has(namespacedKey, PersistentDataType.INTEGER) || p.getPersistentDataContainer().get(namespacedKey, PersistentDataType.INTEGER) != null
        || p.getPersistentDataContainer().get(namespacedKey, PersistentDataType.INTEGER) == 0){
            p.getPersistentDataContainer().set(namespacedKey, PersistentDataType.INTEGER, 1);
            return true;
        }else{
            p.getPersistentDataContainer().set(namespacedKey, PersistentDataType.INTEGER, 0);
            return false;
        }
    }

}
