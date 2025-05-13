import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;


public class Midterm{
    public static void main(String[] args) {
        HashMap<String, Baby[]> map1 = new HashMap<>();
        HashMap<String, Baby[]> map2 = new HashMap<>();
        
    
    try (PrintWriter writer = new PrintWriter(new File("TotalBabyBoys.txt"));
        PrintWriter writer2 = new PrintWriter(new File("TotalBabyGirls.txt"));
        PrintWriter writer1990b = new PrintWriter(new File("BabyBoys1990.txt"));
        PrintWriter writer1990g = new PrintWriter(new File("BabyGirls1990.txt"));
        ) {
        for (int i = 1990; i <= 2017; i++){
            
            String var = Integer.toString(i); // converts the year to the sting
            String label = "BabyBoy" + var; // Makes label for HasMap
            String label2 = "BabyGirl" + var;
            String fileName = "names"+i+".csv"; 
            

            // Initialize array for the year
            Baby[] babyBoys = new Baby[1000];
            Baby[] babyGirls = new Baby[1000];

            map1.put(label, babyBoys);
            map2.put(label2, babyGirls);

            // Reads file 
            File input = new File(fileName);
            // error handaling 
            try{
                Scanner in = new Scanner(input);
                int index = 0;
                while (in.hasNext()&& index < 1000){

                    // Assigns info in the file to its variable.
                    String line = in.nextLine();
                    String [] lineInfo = line.split(",");
                    int rank = Integer.parseInt(lineInfo[0]);
                    String boyName = lineInfo[1];
                    int boyNumber = Integer.parseInt(lineInfo[2]);
                    String girlName = lineInfo[3];
                    int girlNumber = Integer.parseInt(lineInfo[4]);

                   // Assigns the babies information at the index
                    babyBoys[index] = new Baby(boyName, rank, boyNumber);
                    babyGirls[index] = new Baby(girlName, rank, girlNumber);

                    index++;
                    



                } 
                in.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + fileName);
                
            
        }
        

        


    }
    Baby[] Babyboy1990 = map1.get("BabyBoy1990");
    Baby[] Babygirl1990 = map2.get("BabyGirl1990");
    Arrays.sort(Babyboy1990);
    //for (Baby baby : Babyboy1990) {
        //System.out.println(baby);  
            
        //}
    for (Baby baby : Babyboy1990){
        writer1990b.println(baby);
    }
    Arrays.sort(Babygirl1990);    
    //for (Baby baby : Babygirl1990) {
        //System.out.println(baby);  
            
        //}
        for (Baby baby : Babygirl1990){
            writer1990g.println(baby);
        }
    // HashMaps to store total number of babies
        HashMap<String, Integer> boyTotalMap = new HashMap<>();
        HashMap<String, Integer> boyRankMap = new HashMap<>();
        
        
        for (Baby[] babies : map1.values()) {
            for (Baby baby : babies) {
                if (baby != null) {
                    String boyName = baby.getName();
                    int boyNumber = baby.getNumber();
                    int boyRank = baby.getRank();

                    // Sum up the total number of times a name appeared
                    boyTotalMap.put(boyName, boyTotalMap.getOrDefault(boyName, 0) + boyNumber);

                    
                    if (!boyRankMap.containsKey(boyName)) {
                        boyRankMap.put(boyName, boyRank);
                    }
                }
            }
        }

        // sorts  the boy names
        ArrayList<String> sortedBoyNames = new ArrayList<>(boyTotalMap.keySet());
        sortedBoyNames.sort(String::compareToIgnoreCase);

        //for (String boyName : sortedBoyNames) {
            //int totalNumber = boyTotalMap.get(boyName);
            //int rank = boyRankMap.get(boyName);
            //System.out.println(boyName + ": " + rank + ",:  " + totalNumber);
        //}
        for (String boyName : sortedBoyNames) {
                int boyTotalNumber = boyTotalMap.get(boyName);
                int boyRank = boyRankMap.get(boyName);
                writer.println(boyName + ": " + boyRank + ": " + boyTotalNumber);
            }
        
        HashMap<String, Integer> girlTotalMap = new HashMap<>();
        HashMap<String, Integer> girlRankMap = new HashMap<>();
        
        
        for (Baby[] babies : map2.values()) {
            for (Baby baby : babies) {
                if (baby != null) {
                    String girlName = baby.getName();
                    int girlNumber = baby.getNumber();
                    int girlRank = baby.getRank();

                    // Sum up the total number of times a name appeared
                    girlTotalMap.put(girlName, girlTotalMap.getOrDefault(girlName, 0) + girlNumber);

                    
                    if (!girlRankMap.containsKey(girlName)) {
                        girlRankMap.put(girlName, girlRank);
                    }
                }
            }
        }

        // Sort Girl names
        ArrayList<String> sortedGirlNames = new ArrayList<>(girlTotalMap.keySet());
        sortedGirlNames.sort(String::compareToIgnoreCase);

        //for (String girlName : sortedGirlNames) {
            //int totalNumber = girlTotalMap.get(girlName);
            //int rank = girlRankMap.get(girlName);
            //System.out.println(girlName + ": " + girlRank + ",:  " + girlTotalNumber);
        //}
        for (String girlName : sortedGirlNames) {
                int girlTotalNumber = girlTotalMap.get(girlName);
                int girlRank = girlRankMap.get(girlName);
                writer2.println(girlName + ": " + girlRank + ": " + girlTotalNumber);
            }
        


        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}