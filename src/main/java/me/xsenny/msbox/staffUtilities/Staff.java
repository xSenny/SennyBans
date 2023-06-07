package me.xsenny.msbox.staffUtilities;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class Staff {

    private Player staffPlayer;
    private Player invseePlayer;
    private Integer historySize;

    public Integer getHistorySize() {
        return historySize;
    }

    public void setHistorySize(Integer historySize) {
        this.historySize = historySize;
    }

    public String getHistoryUUID() {
        return historyUUID;
    }

    public void setHistoryUUID(String historyUUID) {
        this.historyUUID = historyUUID;
    }

    public String getHistoryName() {
        return historyName;
    }

    public void setHistoryName(String historyName) {
        this.historyName = historyName;
    }

    private String historyUUID;
    private String historyName;
    public static HashMap<Player, Staff> playerStaffHashMap = new HashMap<>();
    public Staff(Player staffPlayer) {
        this.staffPlayer = staffPlayer;
    }

    public Player getStaffPlayer() {
        return staffPlayer;
    }

    public void setStaffPlayer(Player staffPlayer) {
        this.staffPlayer = staffPlayer;
    }

    public Player getInvseePlayer() {
        return invseePlayer;
    }

    public void setInvseePlayer(Player invseePlayer) {
        this.invseePlayer = invseePlayer;
    }

    public static Staff getStaff(Player p){
        if (playerStaffHashMap.containsKey(p)){
            return playerStaffHashMap.get(p);
        }else{
            Staff staff = new Staff(p);
            playerStaffHashMap.put(p, staff);
            return staff;
        }
    }
}
