package me.xsenny.msbox.utils;

import me.kodysimpson.simpapi.menu.Menu;
import me.kodysimpson.simpapi.menu.PlayerMenuUtility;
import me.xsenny.msbox.staffUtilities.Staff;
import org.bukkit.ChatColor;
import org.bukkit.Material;

public abstract class PaginatedMenus extends Menu {

    protected int page = 0;
    protected int maxItemsPerPage = 21;
    protected int maxItemsPerPage1 = 7;
    protected int index = 0;

    public PaginatedMenus(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    //Set the border and menu buttons for the menu
    public void addMenuBorder(){
        inventory.setItem(39, makeItem(Material.ARROW, ChatColor.GREEN + "Left"));

        inventory.setItem(40, makeItem(Material.BARRIER, ChatColor.DARK_RED + "Close"));

        inventory.setItem(41, makeItem(Material.ARROW, ChatColor.GREEN + "Right"));

        for (int i = 0; i < 10; i++) {
            if (i != 4){
                if (inventory.getItem(i) == null) {
                    inventory.setItem(i, super.FILLER_GLASS);
                }
            }else{
                inventory.setItem(i, makeItem(Material.SPRUCE_SIGN, ChatColor.GREEN+"Report List"));
            }
        }

        inventory.setItem(17, super.FILLER_GLASS);
        inventory.setItem(18, super.FILLER_GLASS);
        inventory.setItem(26, super.FILLER_GLASS);
        inventory.setItem(27, super.FILLER_GLASS);
        inventory.setItem(35, super.FILLER_GLASS);

        for (int i = 35; i < 45; i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, super.FILLER_GLASS);
            }
        }
    }

    public void addMenuBorder1(){
        inventory.setItem(21, makeItem(Material.ARROW, ChatColor.GREEN + "Left"));

        inventory.setItem(22, makeItem(Material.BARRIER, ChatColor.DARK_RED + "Close"));

        inventory.setItem(23, makeItem(Material.ARROW, ChatColor.GREEN + "Right"));
        for (int i = 0; i < 10; i++) {
            if (i != 4){
                if (inventory.getItem(i) == null) {
                    inventory.setItem(i, super.FILLER_GLASS);
                }
            }
        }


        for (int i = 17; i < 27; i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, super.FILLER_GLASS);
            }
        }
    }

    public int getMaxItemsPerPage() {
        return maxItemsPerPage;
    }
    public int getMaxItemsPerPage1(){
        return maxItemsPerPage1;
    }
}

