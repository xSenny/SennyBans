package me.xsenny.msbox.utils;

public enum PunishmentType {
    KICK("kick"), BAN("ban"), TEMPBAN("tempban"), MUTE("mute"), TEMPMUTE("tempmute"), UNBAN("unban"), UNMUTE("unmute");
    private String type;
    PunishmentType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
