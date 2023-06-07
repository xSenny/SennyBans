package me.xsenny.msbox.utils;

public enum Permission {
    MUTE("msbox.mute"), TEMPMUTE("msbox.tempmute"), BAN("msbox.ban"), TEMPBAN("msbox.tempban"), KICK("msbox.kick"),
    VIEW_REPORTS("msbox.view_reports"), STAFF("msbox.staff"), TELEPORT("msbox.teleport"), TELEPORT_OTHER("msbox.teleport_other"),
    SEE_VANISH("msbox.seevanish"),VANISH("msbox.vanish"), VANISH_OTHER("msbox.vanish_other"), INVSEE("msbox.invsee"),
    INVSEE_EDIT("msbox.editinvsee"), INVSEE_ENDERCHEST("msbox.editenderchest"), CLEAR_INVENTORY("msbox.clearinv"),
    FREEZE("msbox.freeze"), UNMUTE("msbox.unmute"), UNBAN("msbox.unban"), ALL("msbox.*"), CLEAR_CHAT("msbox.clearchat");

    private String permission;
    Permission(String per){
        permission = per;
    }

    public String getPermission() {
        return permission;
    }
}
