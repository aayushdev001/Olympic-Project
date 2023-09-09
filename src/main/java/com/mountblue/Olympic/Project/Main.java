//package io.mountblue;
//
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//public class Main {
//
//    public static final int ID  = 0;
//    public static final int NAME = 1;
//    public static final int SEX = 2;
//    public static final int AGE = 3;
//    public static final int HEIGHT = 4;
//    public static final int WEIGHT = 5;
//    public static final int TEAM = 6;
//    public static final int NOC = 7;
//    public static final int GAMES = 8;
//    public static final int YEAR = 9;
//    public static final int SEASON = 10;
//    public static final int CITY = 11;
//    public static final int SPORT = 12;
//    public static final int EVENT = 13;
//    public static final int MEDAL = 14;
//
//    public static void main(String[] args) {
//        getTheList();
////        getYearWiseGoldMedals();
//    }
//    public static List<Athlete> getAtleteList(){
//
//        String athleteCsvPath = "/home/suresh/Downloads/athlete_events.csv";
//
//        List<Athlete> athletes = new ArrayList<>();
//
//        try (BufferedReader br = new BufferedReader(new FileReader(athleteCsvPath))){
//            String line;
//
//            br.readLine();
//
//            while((line = br.readLine()) != null){
//
//                String[] data = line.split(",");
//
//                Athlete athlete = new Athlete();
//
//                athlete.setId(data[ID]);
//                athlete.setName(data[NAME]);
//                athlete.setSex(data[SEX]);
//                athlete.setAge(data[AGE]);
//                athlete.setHeight(data[HEIGHT]);
//                athlete.setWeight(data[WEIGHT]);
//                athlete.setTeam(data[TEAM]);
//                athlete.setNoc(data[NOC]);
//                athlete.setGames(data[GAMES]);
//                athlete.setYear(data[YEAR]);
//                athlete.setSeason(data[SEASON]);
//                athlete.setCity(data[CITY]);
//                athlete.setSport(data[SPORT]);
//                athlete.setEvent(data[EVENT]);
//                athlete.setMedal(data[MEDAL]);
//
//                athletes.add(athlete);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return athletes;
//    }
//
//    ////display Year Wise NumberOf GoldMedals WonBy EachPlayer
//    public static void getTheList(){
//        List<Athlete> list = getAtleteList();
//
//        System.out.println(list.size());
//
//        for (Athlete athlete : list){
//            int year = Integer.parseInt((athlete.getYear()));
//
//            System.out.println(year);
//        }
//    }
//
//    public static void getYearWiseGoldMedals(){
//        List<Athlete> list = getAtleteList();
//
//        HashMap<String, Integer> hm = new HashMap<>();
//
//
//
//        for (Athlete athlete : list){
//            if (athlete.getYear().equals("2002")) {
//                hm.put(athlete.getName(), hm.getOrDefault(athlete.getName(), 0) + 1);
//            }
//        }
//
//        System.out.println(hm.size());
//    }
//}