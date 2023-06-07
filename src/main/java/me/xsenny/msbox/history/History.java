package me.xsenny.msbox.history;

public class History {

    private String type;
    private String when;
    private String byWho;
    private String howTime;
    private String reason;

    public History(String type, String when, String byWho, String howTime, String reason) {
        this.type = type;
        this.when = when;
        this.byWho = byWho;
        this.howTime = howTime;
        this.reason = reason;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public String getByWho() {
        return byWho;
    }

    public void setByWho(String byWho) {
        this.byWho = byWho;
    }

    public String getHowTime() {
        return howTime;
    }

    public void setHowTime(String howTime) {
        this.howTime = howTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
