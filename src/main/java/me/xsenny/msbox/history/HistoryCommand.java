package me.xsenny.msbox.history;

import me.kodysimpson.simpapi.exceptions.MenuManagerException;
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException;
import me.kodysimpson.simpapi.menu.MenuManager;
import me.xsenny.msbox.methods.ShortMethods;
import me.xsenny.msbox.staffUtilities.Staff;
import me.xsenny.msbox.utils.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class HistoryCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player p = (Player) sender;

        String uuid = ShortMethods.getUUIDFromName(args[0]);
        if (uuid == null){
            p.sendMessage(Color.cc("&c " + args[0] + " is never been online", true));
            return true;
        }
        Staff.getStaff(p).setHistoryName(args[0]);
        Staff.getStaff(p).setHistoryUUID(uuid);
        try {
            MenuManager.openMenu(HistoryMenu.class, p);
        } catch (MenuManagerException e) {
            e.printStackTrace();
        } catch (MenuManagerNotSetupException e) {
            e.printStackTrace();
        }

        return true;
    }
}
