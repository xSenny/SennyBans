package me.xsenny.msbox.staffUtilities.invsee;

import me.kodysimpson.simpapi.menu.MenuManager;
import me.xsenny.msbox.staffUtilities.Staff;
import me.xsenny.msbox.utils.Color;
import me.xsenny.msbox.utils.PaginatedMenus;
import me.xsenny.msbox.utils.Permission;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InvseeMenu implements Listener {



    public static Inventory getItems(Player p){
        Inventory inventory = Bukkit.createInventory(null, 54, p.getName()+"'s items");

        if (p.getInventory().getHelmet() != null){
            ItemStack item = p.getInventory().getHelmet();
            ItemMeta meta = p.getInventory().getHelmet().getItemMeta();
            item.setItemMeta(meta);
            inventory.setItem(0, item);
        }
        if (p.getInventory().getChestplate() != null){
            ItemStack item = p.getInventory().getChestplate();
            ItemMeta meta = p.getInventory().getChestplate().getItemMeta();
            item.setItemMeta(meta);
            inventory.setItem(1, item);
        }
        if (p.getInventory().getLeggings() != null){
            ItemStack item = p.getInventory().getLeggings();
            ItemMeta meta = p.getInventory().getLeggings().getItemMeta();
            item.setItemMeta(meta);
            inventory.setItem(2, item);
        }
        if (p.getInventory().getBoots() != null){
            ItemStack item = p.getInventory().getBoots();
            ItemMeta meta = p.getInventory().getBoots().getItemMeta();
            item.setItemMeta(meta);
            inventory.setItem(3, item);
        }
        if (p.getInventory().getItemInOffHand() != null){
            ItemStack item = p.getInventory().getItemInOffHand();
            ItemMeta meta = p.getInventory().getItemInOffHand().getItemMeta();
            item.setItemMeta(meta);
            inventory.setItem(5, item);
        }
        ItemStack item1 = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta1 = item1.getItemMeta();
        meta1.setDisplayName(" ");
        item1.setItemMeta(meta1);
        for (int i = 0; i < 9; i++){
            if (inventory.getItem(i) == null){
                inventory.setItem(i, item1);
            }
        }
        for (int i = 36; i <= 44; i++){
            inventory.setItem(i, item1);
        }
        for (int i = 45; i <= 53; i++){
            if (p.getInventory().getItem(i - 45) != null){
                ItemStack item = p.getInventory().getItem(i - 45);
                ItemMeta meta = p.getInventory().getItem(i - 45).getItemMeta();
                item.setItemMeta(meta);
                inventory.setItem(i, item);
            }
        }
        for (int i = 9; i <= 35; i++){
            if (p.getInventory().getItem(i) != null){
                ItemStack item = p.getInventory().getItem(i);
                ItemMeta meta = p.getInventory().getItem(i).getItemMeta();
                item.setItemMeta(meta);
                inventory.setItem(i, item);
            }
        }
        ItemStack end = new ItemStack(Material.ENDER_CHEST);
        ItemMeta end_meta = end.getItemMeta();
        end_meta.setDisplayName(ChatColor.RED+p.getName()+"'s Enderchest");
        end.setItemMeta(end_meta);
        inventory.setItem(7, end);
        ItemStack edit = new ItemStack(Material.BOOK);
        ItemMeta editmeta = edit.getItemMeta();
        editmeta.setDisplayName(ChatColor.RED+"Edit inventory");
        edit.setItemMeta(editmeta);
        inventory.setItem(8, edit);
        ItemStack clear = new ItemStack(Material.TNT, 1);
        ItemMeta meta = clear.getItemMeta();
        meta.setDisplayName(ChatColor.DARK_RED + "Clear player's inventory");
        clear.setItemMeta(meta);
        inventory.setItem(5, clear);
        inventory.setMaxStackSize(94);
        return inventory;
    }

    @EventHandler
    public static void onClick(InventoryClickEvent e){
        Player p = (Player) e.getWhoClicked();
        if (e.getInventory().getMaxStackSize() == 94){
            if (e.getSlot() == 8){
                if (p.isOp() || p.hasPermission(Permission.ALL.getPermission()) || p.hasPermission(Permission.STAFF.getPermission()) || p.hasPermission(Permission.INVSEE_EDIT.getPermission())){
                    Inventory inventory = Staff.getStaff(p).getInvseePlayer().getInventory();
                    p.openInventory(inventory);
                }else{
                    p.sendMessage(Color.cc("&cYou do not have permission to do it.", true));
                }
            }else if (e.getSlot() == 7){
                if (p.isOp() || p.hasPermission(Permission.ALL.getPermission()) || p.hasPermission(Permission.STAFF.getPermission()) || p.hasPermission(Permission.INVSEE_ENDERCHEST.getPermission())){
                    Inventory inventory = Staff.getStaff(p).getInvseePlayer().getEnderChest();
                    p.openInventory(inventory);
                }else{
                    p.sendMessage(Color.cc("&cYou do not have permission to do it.", true));
                }
            }else if (e.getSlot() == 5){
                if (p.isOp() || p.hasPermission(Permission.ALL.getPermission()) || p.hasPermission(Permission.STAFF.getPermission()) || p.hasPermission(Permission.CLEAR_INVENTORY.getPermission())){
                    p.sendMessage("Cleared " + Staff.getStaff(p).getInvseePlayer().getName() + "'s inventory");
                    Staff.getStaff(p).getInvseePlayer().getInventory().clear();
                    p.openInventory(Staff.getStaff(p).getInvseePlayer().getInventory());
                }else{
                    p.sendMessage(Color.cc("&cYou do not have permission to do it.", true));
                }
            }
            e.setCancelled(true);
        }
    }

}
