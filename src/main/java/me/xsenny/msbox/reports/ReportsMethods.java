package me.xsenny.msbox.reports;

import me.xsenny.msbox.MsBox;
import me.xsenny.msbox.methods.ShortMethods;
import org.bukkit.entity.Player;

import java.util.UUID;

public class ReportsMethods {


    public static void createReport(Player who, Player peCine, String reason){
        String id = UUID.randomUUID().toString();
        Report report = new Report(ShortMethods.getWhen(System.currentTimeMillis()),who.getName(), peCine.getName(), reason, id);
        MsBox.reports.add(report);
    }

    public static Report getReportById(String id){
        for (Report report : MsBox.reports) {
            if (report.getId().equals(id)){
                return report;
            }
        }
        return null;
    }

    public static void removeReport(String id){
        Report report = getReportById(id);
        MsBox.reports.remove(report);
    }

}
