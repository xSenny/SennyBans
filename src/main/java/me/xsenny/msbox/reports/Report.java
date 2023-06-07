package me.xsenny.msbox.reports;

import java.io.Serializable;

public class Report implements Serializable {

    private String when;
    private String from;
    private String who_was_reported;
    private String reason;
    private String id;

    public Report(String when, String from, String who_was_reported, String reason, String id) {
        this.when = when;
        this.from = from;
        this.who_was_reported = who_was_reported;
        this.reason = reason;
        this.id = id;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getWho_was_reported() {
        return who_was_reported;
    }

    public void setWho_was_reported(String who_was_reported) {
        this.who_was_reported = who_was_reported;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
