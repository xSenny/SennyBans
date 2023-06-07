package me.xsenny.msbox.history;

import me.kodysimpson.simpapi.exceptions.MenuManagerException;
import me.kodysimpson.simpapi.exceptions.MenuManagerNotSetupException;
import me.kodysimpson.simpapi.menu.MenuManager;
import me.kodysimpson.simpapi.menu.PlayerMenuUtility;
import me.xsenny.msbox.MsBox;
import me.xsenny.msbox.reports.ReportsMenu;
import me.xsenny.msbox.reports.ReportsMethods;
import me.xsenny.msbox.staffUtilities.Staff;
import me.xsenny.msbox.utils.PaginatedMenus;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class HistoryMenu extends PaginatedMenus {


    public HistoryMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "History";
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public boolean cancelAllClicks() {
        return true;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) throws MenuManagerNotSetupException, MenuManagerException {
        switch(e.getSlot()){
            case 21:
                if (page == 0){
                    p.sendMessage(ChatColor.GRAY + "You are already on the first page.");
                }else{
                    page = page - 1;
                    super.open();
                }
                break;
            case 23:
                if (!((index + 1) >= Staff.getStaff(p).getHistorySize())){
                    page = page + 1;
                    super.open();
                }else{
                    p.sendMessage(ChatColor.GRAY + "You are on the last page.");
                }
                break;
            case 22:
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
        addMenuBorder1();
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Staff.getStaff(p).getHistoryName() + "'s history:");
        item.setItemMeta(meta);
        inventory.setItem(4, item);
        ArrayList<History> list = HistoryMethods.getHistoryOfPlayer(Staff.getStaff(p).getHistoryUUID());
        Staff.getStaff(p).setHistorySize(list.size());
        if (!list.isEmpty()){
            for (int i = 0; i < getMaxItemsPerPage1(); i++) {
                index = (getMaxItemsPerPage1()) * page + i;
                if (index >= list.size()) break;
                if (list.get(index) != null){
                    ItemStack item1 = new ItemStack(Material.PAPER);
                    ItemMeta itemMeta = item1.getItemMeta();
                    itemMeta.setDisplayName(ChatColor.GREEN+list.get(index).getWhen());
                    ArrayList<String> lore = new ArrayList<>();
                    lore.add(ChatColor.GRAY + "-----------------------");
                    lore.add(ChatColor.GRAY + "Time: " + ChatColor.GREEN + list.get(index).getHowTime());
                    lore.add(ChatColor.GRAY + "Type: " + ChatColor.GREEN + list.get(index).getType());
                    lore.add(ChatColor.GRAY + "Reason: " + ChatColor.GREEN + list.get(index).getReason());
                    lore.add(ChatColor.GRAY + "-----------------------");
                    itemMeta.setLore(lore);
                    item1.setItemMeta(itemMeta);
                    inventory.addItem(item1);
                }
            }
        }
        for (int i = 0; i < getMaxItemsPerPage1(); i++){
            if (inventory.getItem(i) == null){
                inventory.setItem(i, makeItem(Material.BLACK_STAINED_GLASS_PANE, " "));
            }
        }
    }
}
