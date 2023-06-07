package me.xsenny.msbox.staffUtilities.vanishMode;

import me.xsenny.msbox.MsBox;
import me.xsenny.msbox.utils.Permission;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

public class Methods {

    public static boolean isPlayerVanished(Player p){
        if (p.getPersistentDataContainer().has(new NamespacedKey(MsBox.plugin, "vanish"), PersistentDataType.INTEGER)){
            if (p.getPersistentDataContainer().get(new NamespacedKey(MsBox.plugin, "vanish"), PersistentDataType.INTEGER) == 1){
                return true;
            }
        }
        return false;
    }

    public static void toggleVanishMode(Player p){
        if (!isPlayerVanished(p)){
            for (Player people : Bukkit.getOnlinePlayers()){
                if (!(people.hasPermission(Permission.ALL.getPermission()) || people.hasPermission(Permission.STAFF.getPermission()) || people.hasPermission(Permission.SEE_VANISH.getPermission()) || people.isOp())){
                    people.hidePlayer(MsBox.plugin, p);
                }
            }
            p.getPersistentDataContainer().set(new NamespacedKey(MsBox.plugin, "vanish"), PersistentDataType.INTEGER, 1);
            p.sendMessage("You're now vanished");
            return;
        }
        for (Player people : Bukkit.getOnlinePlayers()){
            people.showPlayer(MsBox.plugin, p);
        }
        p.getPersistentDataContainer().set(new NamespacedKey(MsBox.plugin, "vanish"), PersistentDataType.INTEGER, 0);
        p.sendMessage("You are no longer vanished");
        p.setCustomNameVisible(false);
    }

}
