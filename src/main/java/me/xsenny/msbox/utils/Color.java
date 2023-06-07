package me.xsenny.msbox.utils;

import org.bukkit.ChatColor;

public class Color {

    public static String cc(String s, Boolean prefix){
        if (prefix){
            return ChatColor.translateAlternateColorCodes('&',"&6[SennyMines] "+s);
        }else{
            return ChatColor.translateAlternateColorCodes('&', s);
        }
    }

}
