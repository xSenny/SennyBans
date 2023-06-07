package me.xsenny.msbox.utils;

public enum PunishUnit {
    SECOND("s", 1/60), MINUTE("m", 1), HOUR("h", 60), DAY("d", 60*24), WEEK("w", 60*24*7),
    MONTH("month", 30*60*24), YEAR("year", 60*24*365);
    public String name;
    public int multi;
    PunishUnit(String n, int mult){
        name = n;
        multi = mult;
    }

    public static long getTicks(String un, int time){
        long sec;
        try{
            sec = time * 60;
        }catch (NumberFormatException ex){
            return 0;
        }
        for (PunishUnit unit : PunishUnit.values()){
            if (un.startsWith(unit.name)){
                return (sec *= unit.multi) * 1000;
            }
        }
        return 0;
    }
}
