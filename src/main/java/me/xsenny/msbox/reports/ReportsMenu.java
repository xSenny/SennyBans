package me.xsenny.msbox.reports;

import me.kodysimpson.simpapi.exceptions.MenuManagerException;
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException;
import me.kodysimpson.simpapi.menu.MenuManager;
import me.kodysimpson.simpapi.menu.PlayerMenuUtility;
import me.xsenny.msbox.MsBox;
import me.xsenny.msbox.utils.PaginatedMenus;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class ReportsMenu extends PaginatedMenus {


    public ReportsMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Reports List";
    }

    @Override
    public int getSlots() {
        return 45;
    }

    @Override
    public boolean cancelAllClicks() {
        return true;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) throws MenuManagerNotSetupException, MenuManagerException {
        switch(e.getSlot()){
            case 39:
                if (page == 0){
                    p.sendMessage(ChatColor.GRAY + "You are already on the first page.");
                }else{
                    page = page - 1;
                    super.open();
                }
            break;
            case 41:
                if (!((index + 1) >= MsBox.reports.size())){
                    page = page + 1;
                    super.open();
                }else{
                    p.sendMessage(ChatColor.GRAY + "You are on the last page.");
                }
                break;
            case 40:
                p.closeInventory();
                break;
        }
        if (e.getCurrentItem().getType().equals(Material.PLAYER_HEAD)){
            String id = e.getCurrentItem().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(MsBox.plugin, "id"), PersistentDataType.STRING);
            ReportsMethods.removeReport(id);
            MenuManager.openMenu(ReportsMenu.class, (Player) e.getWhoClicked());
        }
    }

    @Override
    public void setMenuItems() {
        addMenuBorder();
        ArrayList<Report> allReports = MsBox.reports;
        if (allReports != null && !allReports.isEmpty()){
            for (int i = 0; i < getMaxItemsPerPage(); i++){
                index = getMaxItemsPerPage() * page + i;
                if (index >= allReports.size()) break;
                if (allReports.get(index) != null){
                    ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
                    ItemMeta playerMeta = playerHead.getItemMeta();
                    playerMeta.setDisplayName(ChatColor.GREEN + allReports.get(index).getWhen());
                    ArrayList<String> playerLore = new ArrayList<>();
                    playerLore.add(ChatColor.GRAY + "-----------------------");
                    playerLore.add(ChatColor.GRAY + "From: " + ChatColor.GREEN+allReports.get(index).getFrom());
                    playerLore.add(ChatColor.GRAY+"Reported: "+ChatColor.GREEN+allReports.get(index).getWho_was_reported());
                    playerLore.add(ChatColor.GRAY+"Reason: "+ChatColor.GREEN+allReports.get(index).getReason());
                    playerLore.add(ChatColor.GRAY + "-----------------------");
                    playerLore.add(ChatColor.RED+"Click to remove report.");
                    playerMeta.setLore(playerLore);
                    playerMeta.getPersistentDataContainer().set(new NamespacedKey(MsBox.plugin, "id"), PersistentDataType.STRING, allReports.get(index).getId());
                    playerHead.setItemMeta(playerMeta);
                    inventory.addItem(playerHead);
                }
            }
        }
        for (int i = 0; i < getMaxItemsPerPage(); i++){
            if (inventory.getItem(i) == null){
                inventory.setItem(i, makeItem(Material.BLACK_STAINED_GLASS_PANE, " "));
            }
        }
    }
}
