import java.util.*;

public class BabySearchUtil {

   
    public static void searchSingleNameSingleYear(String name, int year, boolean isBoy, HashMap<String, Baby[]> map) {
        String key = (isBoy ? "BabyBoy" : "BabyGirl") + year;
        Baby[] babies = map.get(key);

        if (babies == null) {
            System.out.println("No data for year: " + year);
            return;
        }

        int total = Arrays.stream(babies).filter(Objects::nonNull).mapToInt(Baby::getNumber).sum();
        recursiveSearchInYear(name, babies, 0, year, total);
    }

    private static void recursiveSearchInYear(String name, Baby[] babies, int index, int year, int total) {
        if (index >= babies.length || babies[index] == null) {
            System.out.println("Name not found in " + year);
            return;
        }

        if (babies[index].getName().equalsIgnoreCase(name)) {
            double fraction = (double) babies[index].getNumber() / total;
            System.out.printf("%d\n%s: %d, %d, %.6f\n", year, babies[index].getName(), babies[index].getRank(), babies[index].getNumber(), fraction);
            return;
        }

        recursiveSearchInYear(name, babies, index + 1, year, total);
    }

    
    public static void searchSingleNameAllYears(String name, boolean isBoy, HashMap<String, Baby[]> map) {
        recursiveSearch(name, isBoy, map, 1990, 2017, 0, 0);
    }

    private static void recursiveSearch(
        String name,
        boolean isBoy,
        HashMap<String, Baby[]> map,
        int currentYear,
        int endYear,
        int totalCount,
        int totalNumber
    ) {
        if (currentYear > endYear) {
            if (totalNumber > 0) {
                double totalFraction = (double) totalNumber / totalCount;
                System.out.printf("Total\n%s: --, %d, %.6f\n", name, totalNumber, totalFraction);
            } else {
                System.out.println("Name not found in any year.");
            }
            return;
        }

        String key = (isBoy ? "BabyBoy" : "BabyGirl") + currentYear;
        Baby[] babies = map.get(key);

        if (babies != null) {
            int yearTotal = Arrays.stream(babies).filter(Objects::nonNull).mapToInt(Baby::getNumber).sum();
            for (Baby baby : babies) {
                if (baby != null && baby.getName().equalsIgnoreCase(name)) {
                    double fraction = (double) baby.getNumber() / yearTotal;
                    System.out.printf("%d\n%s: %d, %d, %.6f\n", currentYear, baby.getName(), baby.getRank(), baby.getNumber(), fraction);
                    totalNumber += baby.getNumber();
                    totalCount += yearTotal;
                    break;
                }
            }
        }

        recursiveSearch(name, isBoy, map, currentYear + 1, endYear, totalCount, totalNumber);
    }

    
    public static void searchMultipleNamesAllYears(List<String> names, boolean isBoy, HashMap<String, Baby[]> map) {
        recursiveMultipleNameSearch(names, 0, isBoy, map);
    }

    private static void recursiveMultipleNameSearch(List<String> names, int index, boolean isBoy, HashMap<String, Baby[]> map) {
        if (index >= names.size()) return;

        String name = names.get(index).trim();
        searchSingleNameAllYears(name, isBoy, map);
        System.out.println();
        recursiveMultipleNameSearch(names, index + 1, isBoy, map);
    }
}
