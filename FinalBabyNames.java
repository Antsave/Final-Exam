import java.util.*;

public class FinalBabyNames {

    
    public static void main(String[] args) {
        HashMap<String, Baby[]> boyMap = new HashMap<>();
        HashMap<String, Baby[]> girlMap = new HashMap<>();
        LoadData.loadData(boyMap, girlMap);


        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("MENU:");
            System.out.println("1 - Search single name by year");
            System.out.println("2 - Search single name across all years");
            System.out.println("3 - Search multiple names across all years");
            System.out.println("4 - Exit");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            if (choice.equals("4")) break;

            System.out.print("Gender (boy/girl): ");
            String genderInput = scanner.nextLine().trim().toLowerCase();
            boolean isBoy = genderInput.equals("boy");
            HashMap<String, Baby[]> map = isBoy ? boyMap : girlMap;

            if (choice.equals("1")) {
                System.out.print("Enter name: ");
                String name = scanner.nextLine().trim();
                System.out.print("Enter year (1990–2017): ");
                try {
                    int year = Integer.parseInt(scanner.nextLine());
                    BabySearchUtil.searchSingleNameSingleYear(name, year, isBoy, map);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid year input.");
                }
            } else if (choice.equals("2")) {
                System.out.print("Enter name: ");
                String name = scanner.nextLine().trim();
                BabySearchUtil.searchSingleNameAllYears(name, isBoy, map);
            } else if (choice.equals("3")) {
                System.out.print("Enter names (comma-separated): ");
                String input = scanner.nextLine().trim();
                List<String> names = Arrays.asList(input.split("\\s*,\\s*"));
                BabySearchUtil.searchMultipleNamesAllYears(names, isBoy, map);
            } else {
                System.out.println("Invalid choice.");
            }

            System.out.println();
        }
        scanner.close();
        System.out.println("Program ended.");
    }
}
