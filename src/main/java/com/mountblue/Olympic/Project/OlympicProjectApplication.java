package com.mountblue.Olympic.Project;

import com.moutblue.Olympic.Project.Athlete;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.channels.GatheringByteChannel;
import java.util.*;

@SpringBootApplication
public class OlympicProjectApplication
{
	public static final int ID  = 0;
	public static final int NAME = 1;
	public static final int SEX = 2;
	public static final int AGE = 3;
	public static final int HEIGHT = 4;
	public static final int WEIGHT = 5;
	public static final int TEAM = 6;
	public static final int NOC = 7;
	public static final int GAMES = 8;
	public static final int YEAR = 9;
	public static final int SEASON = 10;
	public static final int CITY = 11;
	public static final int SPORT = 12;
	public static final int EVENT = 13;
	public static final int MEDAL = 14;

	public static void main(String[] args)
	{
		SpringApplication.run(OlympicProjectApplication.class, args);
        List<com.moutblue.Olympic.Project.Athlete> athletes = getAthleteList();
        noOfGoldMedalsWonByEachPlayerInEveryYear(athletes);
        athletesWhoWonGoldMedalIn1980LessThan30(athletes);
        eventWiseWinners(athletes);
        goldWinnerInFootballInEveryOlympic(athletes);
        femaleAthleteWhoWonMaximumGoldAllOlympics(athletes);
        nameOfAthletesWhoParticipatedInMoreThanThreeOlympics(athletes);
        checkNoOfYears(athletes);
//        test();
	}

    private static void nameOfAthletesWhoParticipatedInMoreThanThreeOlympics(List<Athlete> athletes)
    {
        Map<String, Set<String>> athletesParticipation = new HashMap<>();
        for (Athlete a : athletes)
        {
            if(athletesParticipation.containsKey(a.getName()))
            {
                athletesParticipation.get(a.getName()).add(a.getYear());
            }
            else
            {
                athletesParticipation.put(a.getName(), new HashSet<>());
                athletesParticipation.get(a.getName()).add(a.getYear());
            }
        }
        System.out.println("Name of athletes who participated in more than three Olympics");
        for (String name : athletesParticipation.keySet())
        {
            if(athletesParticipation.get(name).size()>3)
                System.out.println(name+", "+athletesParticipation.get(name).size());
        }
    }

    private static void checkNoOfYears(List<Athlete> athletes)
    {
        Map<String, Integer> map = new HashMap<>();
        for (Athlete a : athletes)
        {
            map.put(a.getYear(), 1);
        }
        System.out.println(map.size());
    }

    private static void femaleAthleteWhoWonMaximumGoldAllOlympics(List<Athlete> athletes)
    {
        Map<String, Integer> femaleGoldCounts = new HashMap<>();

        for (Athlete a : athletes)
        {
            if(a.getSex().equals("F") && a.getMedal().equals("Gold"))
            {
                String key = a.getYear()+"~"+a.getName();
                femaleGoldCounts.put(key, femaleGoldCounts.getOrDefault(key, 0)+1);
            }
        }
//        System.out.println(femaleGoldCounts.size());
        Map<String, String> maxGoldWinners = new HashMap<>();
        for (String f : femaleGoldCounts.keySet())
        {
            String part[] = f.split("~");
            String year = part[0];
            String name = part[1];
            int goldMedals = femaleGoldCounts.get(f);
            if(!maxGoldWinners.containsKey(year) || goldMedals > femaleGoldCounts.get(year+"~"+maxGoldWinners.get(year)))
            {
//                System.out.println("Hello");
                maxGoldWinners.put(year, name);
            }
        }
        System.out.println(maxGoldWinners.size());
        for (String year : maxGoldWinners.keySet())
        {
            System.out.println(year+", "+maxGoldWinners.get(year)+", "+femaleGoldCounts.get(year+"~"+maxGoldWinners.get(year)));
        }
    }

    private static void goldWinnerInFootballInEveryOlympic(List<Athlete> athletes)
    {
        Map<String, List<String>> footballWinner = new HashMap<>();

        for(Athlete a : athletes)
        {
            if(a.getSport().equals("Football") && a.getMedal().equals("Gold"))
            {
                if(footballWinner.containsKey(a.getYear()))
                {
                    footballWinner.get(a.getYear()).add(a.getName());
                }
                else
                {
                    footballWinner.put(a.getYear(), new ArrayList<>());
                    footballWinner.get(a.getYear()).add(a.getName());
                }
            }
        }
//        System.out.println(footballWinner);
        for (String y : footballWinner.keySet())
        {
            System.out.println(y+"->"+footballWinner.get(y));
            System.out.println(footballWinner.get(y).size());
        }
    }

    private static void eventWiseWinners(List<Athlete> athletes)
    {
        Map<String, List<String>> map = new HashMap<>();

        for (Athlete a : athletes)
        {
            if(a.getYear().equals("1980"))
            {

                if(map.containsKey(a.getEvent()))
                {
                    if(!a.getMedal().equals("NA")) map.get(a.getEvent()).add(a.getName());
//                    if(a.getMedal().equals("Gold"))
//                    {
//                        map.get(a.getEvent()).add(0, a.getName());
//                    }
//                    else if(a.getMedal().equals("Silver"))
//                    {
//                        map.get(a.getEvent()).add(1, a.getName());
//                    }
//                    else if (a.getMedal().equals("Bronze"))
//                    {
//                        map.get(a.getEvent()).add(2, a.getName());
//                    }
                }
                else
                {
                    List<String> athleteName = new ArrayList<>(3);
                    if(!a.getMedal().equals("NA")) athleteName.add(a.getName());
//                    if(a.getMedal().equals("Gold"))
//                    {
//                        athleteName.add(0, a.getName());
//                    }
//                    else if(a.getMedal().equals("Silver"))
//                    {
//                        athleteName.add(1, a.getName());
//                    }
//                    else if(a.getMedal().equals("Bronze"))
//                    {
////                        athleteName.add(2, a.getName());
//                    }
                    map.put(a.getEvent(), athleteName);
                }
            }
        }
        System.out.println();
        System.out.println();
        System.out.println("\n \n Event wise winners in 1980");
//        System.out.println(map);
        for(String event : map.keySet())
        {
            System.out.println(event+" -> "+map.get(event));
        }
    }

    private static void athletesWhoWonGoldMedalIn1980LessThan30(List<Athlete> athletes)
    {
        List<String> athletesList = new ArrayList<>();
        for (Athlete athlete : athletes)
        {
            if (!athlete.getAge().equals("NA") && athlete.getYear().equals("1980") && Integer.parseInt(athlete.getAge())<30)
            {
                athletesList.add(athlete.getName());
            }
        }
        System.out.println("Athletes who won gold medal in 1980 and age is less than 30 years");
        System.out.println(athletesList);
    }

    private static void noOfGoldMedalsWonByEachPlayerInEveryYear(List<Athlete> athletes)
    {
        Map<String, Integer> goldPerYearMapByPlayer = new HashMap<>();
        for (Athlete athlete : athletes)
        {
            String year = athlete.getYear();
            String name = athlete.getName();
            String medal = athlete.getMedal();

            if(medal.equals("Gold"))
            {
                String key = name +", "+ year;
                goldPerYearMapByPlayer.put(key, goldPerYearMapByPlayer.getOrDefault(key,0)+1);
            }
        }
        System.out.println("Year wise number of gold medals won by each player");
        for(String key : goldPerYearMapByPlayer.keySet())
        {
            System.out.println(key+", "+goldPerYearMapByPlayer.get(key));
        }
    }

    public static List<com.moutblue.Olympic.Project.Athlete> getAthleteList()
	{

        String athleteCsvPath = "src/main/java/com/mountblue/Olympic/Project/athlete_events.csv";

        List<Athlete> athletes = new ArrayList<>();
        int count = 0;

        try
        {
            FileReader reader = new FileReader(athleteCsvPath);
            CSVParser csvRecords = new CSVParser(reader, CSVFormat.DEFAULT);

            boolean flag = false;
            for (CSVRecord csvRecord : csvRecords)
            {
                if (flag)
                {
                    Athlete athlete = new Athlete();
                    athlete.setId(csvRecord.get(ID));
                    athlete.setName(csvRecord.get(NAME));
                    athlete.setSex(csvRecord.get(SEX));
                    athlete.setAge(csvRecord.get(AGE));
                    athlete.setHeight(csvRecord.get(HEIGHT));
                    athlete.setWeight(csvRecord.get(WEIGHT));
                    athlete.setTeam(csvRecord.get(TEAM));
                    athlete.setNoc(csvRecord.get(NOC));
                    athlete.setGames(csvRecord.get(GAMES));
                    athlete.setYear(csvRecord.get(YEAR));
                    athlete.setSeason(csvRecord.get(SEASON));
                    athlete.setCity(csvRecord.get(CITY));
                    athlete.setSport(csvRecord.get(SPORT));
                    athlete.setEvent(csvRecord.get(EVENT));
                    athlete.setMedal(csvRecord.get(MEDAL));

                    athletes.add(athlete);
                    count++;
                }
                flag = true;
            }
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
//        System.out.println(count);
        return athletes;
    }
    public static void test()
    {
        List<Athlete> athletes = getAthleteList();
        int count = 0;
        for (Athlete athlete : athletes)
        {
            int year = Integer.parseInt(athlete.getYear());
//            System.out.println(year);
            count++;
        }
        System.out.println(count);
    }

}
