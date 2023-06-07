package me.xsenny.msbox;

import me.kodysimpson.simpapi.menu.MenuManager;
import me.xsenny.msbox.commands.*;
import me.xsenny.msbox.commands.tab.Players;
import me.xsenny.msbox.database.Database;
import me.xsenny.msbox.database.Files;
import me.xsenny.msbox.history.HistoryCommand;
import me.xsenny.msbox.listeners.BanListener;
import me.xsenny.msbox.listeners.FirstJoin;
import me.xsenny.msbox.listeners.MuteListener;
import me.xsenny.msbox.reports.Report;
import me.xsenny.msbox.reports.ReportAPlayerCommand;
import me.xsenny.msbox.reports.ReportsCommand;
import me.xsenny.msbox.staffUtilities.Teleport;
import me.xsenny.msbox.staffUtilities.freeze.FreezeCommand;
import me.xsenny.msbox.staffUtilities.freeze.MoveEvent;
import me.xsenny.msbox.staffUtilities.invsee.InvseeCommand;
import me.xsenny.msbox.staffUtilities.invsee.InvseeMenu;
import me.xsenny.msbox.staffUtilities.vanishMode.Listeners;
import me.xsenny.msbox.staffUtilities.vanishMode.VanishCommand;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public final class MsBox extends JavaPlugin {

    public static HashMap<String, Long> currentTempBans = new HashMap<>();
    public static HashMap<String, Long> currentTempMutes = new HashMap<>();
    public static String banPath = "plugins/MsBox"+File.separator + "BanList.dat";
    public static String mutePath = "plugins/MsBox"+File.separator+"MuteList.dat";
    public static final String reportsPath = "plugins/MsBox" + File.separator+"ReportsList.dat";
    public static ArrayList<String> permBans = new ArrayList<>();
    public static ArrayList<String> permMutes = new ArrayList<>();
    public static ArrayList<Report> reports = new ArrayList<>();
    public static MsBox plugin;
    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        Files.loadAll();
        Database.connect();
        Database.onUpdate("CREATE TABLE IF NOT EXISTS BANS (uuid varchar(40), is_perm integer, time integer, reason string, unbanned integer, by_who varchar(40), cand string)");
        Database.onUpdate("CREATE TABLE IF NOT EXISTS MUTES (uuid varchar(40), is_perm integer, time integer, reason string, unmuted integer, by_who varchar(40), cand string)");
        Database.onUpdate("CREATE TABLE IF NOT EXISTS PLAYERS(al INTEGER PRIMARY KEY AUTOINCREMENT, uuid varchar(40), name string)");
        Database.onUpdate("CREATE TABLE IF NOT EXISTS PLAYERLOG (uuid varchar(40), type string, cand string, by_who string, time string, reason string)"); //playeruniqueid, PunishmentType val, cand, cine l-a dat, cat timp a durat, motiv

        MenuManager.setup(getServer(), this);

        addCommands();

        registerCommands();
    }

    @Override
    public void onDisable() {
        Files.saveTempBans();
        Files.saveTempMutes();
        Files.savePermBans();
        Files.savePermMutes();
        Files.saveReports();
        Database.disconnect();
    }

    public void registerCommands(){
        getServer().getPluginManager().registerEvents(new InvseeMenu(), this);
        getServer().getPluginManager().registerEvents(new Listeners(), this);
        getServer().getPluginManager().registerEvents(new MuteListener(), this);
        getServer().getPluginManager().registerEvents(new BanListener(), this);
        getServer().getPluginManager().registerEvents(new FirstJoin(), this);
        getServer().getPluginManager().registerEvents(new MoveEvent(), this);
    }

    public void addCommands(){
        getCommand("tempban").setExecutor(new tempBanCommands());
        getCommand("ban").setExecutor(new permBanCommand());
        getCommand("mute").setExecutor(new permMuteCommand());
        getCommand("tempmute").setExecutor(new tempMuteCommand());
        getCommand("kick").setExecutor(new kickPlayer());
        getCommand("report").setExecutor(new ReportAPlayerCommand());
        getCommand("reports").setExecutor(new ReportsCommand());
        getCommand("vanish").setExecutor(new VanishCommand());
        getCommand("teleport").setExecutor(new Teleport());
        getCommand("invsee").setExecutor(new InvseeCommand());
        getCommand("unban").setExecutor(new UnbanCommand());
        getCommand("unmute").setExecutor(new UnmuteUnbanCommand());
        getCommand("history").setExecutor(new HistoryCommand());
        getCommand("freeze").setExecutor(new FreezeCommand());
        getCommand("clearchat").setExecutor(new ClearChatCommand());

        getCommand("tempban").setTabCompleter(new Players());
        getCommand("ban").setTabCompleter(new Players());
        getCommand("mute").setTabCompleter(new Players());
        getCommand("tempmute").setTabCompleter(new Players());
        getCommand("kick").setTabCompleter(new Players());
        getCommand("reports").setTabCompleter(new Players());
        getCommand("teleport").setTabCompleter(new Players());
        getCommand("unmute").setTabCompleter(new Players());
        getCommand("history").setTabCompleter(new Players());
        getCommand("freeze").setTabCompleter(new Players());
    }

}
