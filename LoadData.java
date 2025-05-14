import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class LoadData {
    public static void loadData(HashMap<String, Baby[]> boyMap, HashMap<String, Baby[]> girlMap) {
        for (int year = 1990; year <= 2017; year++) {
            String fileName = "data/names" + year + ".csv";
            try (Scanner in = new Scanner(new File(fileName))) {
                Baby[] boys = new Baby[1000];
                Baby[] girls = new Baby[1000];
                int index = 0;

                while (in.hasNextLine() && index < 1000) {
                    String[] parts = in.nextLine().split(",");
                    int rank = Integer.parseInt(parts[0]);
                    boys[index] = new Baby(parts[1], rank, Integer.parseInt(parts[2]));
                    girls[index] = new Baby(parts[3], rank, Integer.parseInt(parts[4]));
                    index++;
                }

                boyMap.put("BabyBoy" + year, boys);
                girlMap.put("BabyGirl" + year, girls);
            } catch (FileNotFoundException e) {
                System.out.println("Missing file: " + fileName);
            }
        }
    }
}
