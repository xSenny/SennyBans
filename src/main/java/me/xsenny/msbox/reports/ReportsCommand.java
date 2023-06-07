package me.xsenny.msbox.reports;

import me.kodysimpson.simpapi.exceptions.MenuManagerException;
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException;
import me.kodysimpson.simpapi.menu.MenuManager;
import me.xsenny.msbox.utils.Color;
import me.xsenny.msbox.utils.Permission;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ReportsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)){
            System.out.println("You need to be a player to use this command");
            return true;
        }
        Player p = (Player) sender;
        if (p.hasPermission(Permission.VIEW_REPORTS.getPermission()) || p.hasPermission(Permission.ALL.getPermission()) || p.isOp()){
            try {
                MenuManager.openMenu(ReportsMenu.class, p);
            } catch (MenuManagerException e) {
                e.printStackTrace();
            } catch (MenuManagerNotSetupException e) {
                e.printStackTrace();
            }
            return true;
        }
        p.sendMessage(Color.cc("Only a staff member can view reports.", true));

        return true;
    }
}
