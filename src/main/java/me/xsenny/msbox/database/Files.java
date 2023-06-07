package me.xsenny.msbox.database;

import me.xsenny.msbox.MsBox;
import me.xsenny.msbox.reports.Report;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Files {

    public static String banPath = "plugins/MsBox"+File.separator + "BanList.dat";
    public static String mutePath = "plugins/MsBox"+File.separator+"MuteList.dat";
    public static final String reportsPath = "plugins/MsBox" + File.separator+"ReportsList.dat";

    public static void loadAll(){
        File file = new File(banPath);
        new File("plugins/MsBox").mkdir();
        if (file.exists()){
            MsBox.currentTempBans = loadTempBans();
        }
        if (MsBox.currentTempBans == null){
            MsBox.currentTempBans = new HashMap<>();
        }
        File file1 = new File(mutePath);
        if (file1.exists()){
            MsBox.currentTempMutes = loadTempMutes();
        }
        if (MsBox.currentTempMutes == null){
            MsBox.currentTempMutes = new HashMap<>();
        }
        File file2 = new File("plugins/MsBox"+File.separator + "PermBanList.dat");
        if (file2.exists()){
            MsBox.permBans = loadPermBans();
        }
        if (MsBox.permBans == null){
            MsBox.permBans = new ArrayList<>();
        }

        File file3 = new File("plugins/MsBox"+File.separator + "PermMuteList.dat");
        if (file3.exists()){
            MsBox.permMutes = loadPermMutes();
        }
        if (MsBox.permMutes == null){
            MsBox.permMutes = new ArrayList<>();
        }
        File file4 = new File(reportsPath);
        if (file4.exists()){
            MsBox.reports = loadReports();
        }
        if (MsBox.reports == null){
            MsBox.reports =new ArrayList<>();
        }
    }

    public static ArrayList<String> loadPermBans(){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("plugins/MsBox"+ File.separator + "PermBanList.dat"));
            Object result = ois.readObject();
            ois.close();
            return (ArrayList<String>)  result;
        }catch (Exception e){
            return null;
        }
    }

    public static ArrayList<String> loadPermMutes(){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("plugins/MsBox"+File.separator + "PermMuteList.dat"));
            Object result = ois.readObject();
            ois.close();
            return (ArrayList<String>)  result;
        }catch (Exception e){
            return null;
        }
    }

    public static void savePermMutes(){
        File file = new File("plugins/MsBox"+File.separator + "PermMuteList.dat");
        if (!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("plugins/MsBox"+File.separator + "PermMuteList.dat"));
            oos.writeObject(MsBox.permMutes);
            oos.flush();
            oos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void savePermBans(){
        File file = new File("plugins/MsBox"+File.separator + "PermBanList.dat");
        if (!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("plugins/MsBox"+File.separator + "PermBanList.dat"));
            oos.writeObject(MsBox.permBans);
            oos.flush();
            oos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void saveTempBans(){
        File file = new File(banPath);
        if (!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(banPath));
            oos.writeObject(MsBox.currentTempBans);
            oos.flush();
            oos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static HashMap<String, Long> loadTempBans(){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(banPath));
            Object result = ois.readObject();
            ois.close();
            return (HashMap<String, Long>) result;
        }catch (Exception e){
            return null;
        }
    }

    public static void saveTempMutes(){
        File file = new File(mutePath);
        if (!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(mutePath));
            oos.writeObject(MsBox.currentTempMutes);
            oos.flush();
            oos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static HashMap<String, Long> loadTempMutes(){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(mutePath));
            Object result = ois.readObject();
            ois.close();
            return (HashMap<String, Long>) result;
        }catch (Exception e){
            return null;
        }
    }

    public static ArrayList<Report> loadReports(){
        try{
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(reportsPath));
            Object result = ois.readObject();
            ois.close();
            return (ArrayList<Report>)  result;
        }catch (Exception e){
            return null;
        }
    }

    public static void saveReports(){
        File file = new File(reportsPath);
        if (!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(reportsPath));
            oos.writeObject(MsBox.reports);
            oos.flush();
            oos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
