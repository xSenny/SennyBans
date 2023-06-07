package me.xsenny.msbox.history;

import me.xsenny.msbox.database.Database;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class HistoryMethods {

    public static ArrayList<History> getHistoryOfPlayer(String uuid){
        ArrayList<History> histories = new ArrayList<>();

        ResultSet rs = Database.onQuery("SELECT * FROM PLAYERLOG WHERE uuid = \"" + uuid + "\"");
        while(true) {
            try {
                if (rs == null) break;
                if (!rs.next()) break;
                History history = new History(rs.getString("type"), rs.getString("cand"), rs.getString("by_who"), rs.getString("time"), rs.getString("reason"));
                histories.add(history);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return histories;
    }

}
