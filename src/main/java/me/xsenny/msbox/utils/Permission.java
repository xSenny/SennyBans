package me.xsenny.msbox.utils;

public enum Permission {
    MUTE("sennybans.mute"), TEMPMUTE("sennybans.tempmute"), BAN("sennybans.ban"), TEMPBAN("sennybans.tempban"), KICK("sennybans.kick"),
    VIEW_REPORTS("sennybans.view_reports"), STAFF("sennybans.staff"), TELEPORT("sennybans.teleport"), TELEPORT_OTHER("sennybans.teleport_other"),
    SEE_VANISH("sennybans.seevanish"),VANISH("sennybans.vanish"), VANISH_OTHER("sennybans.vanish_other"), INVSEE("sennybans.invsee"),
    INVSEE_EDIT("sennybans.editinvsee"), INVSEE_ENDERCHEST("sennybans.editenderchest"), CLEAR_INVENTORY("sennybans.clearinv"),
    FREEZE("sennybans.freeze"), UNMUTE("sennybans.unmute"), UNBAN("sennybans.unban"), ALL("sennybans.*"), CLEAR_CHAT("sennybans.clearchat");

    private String permission;
    Permission(String per){
        permission = per;
    }

    public String getPermission() {
        return permission;
    }
}
