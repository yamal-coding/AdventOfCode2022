import java.io.*;
import java.util.*;

/*
 * Part 1 of day 3: https://adventofcode.com/2022/day/3
 */

public class Day03Part1 {

    public static final Map<Character, Integer> PRIORITIES = populatePriorities();

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scanner = new Scanner(new FileInputStream(file));

        int totalPriority = 0;
        while (scanner.hasNext()) {
            String rucksack = scanner.nextLine();
            String firstCompartment = rucksack.substring(0, rucksack.length() / 2);
            String secondCompartment = rucksack.substring(rucksack.length() / 2, rucksack.length());

            Set<Character> typesIncludedInFirstCompartment = new HashSet<>();

            for (int i = 0; i < firstCompartment.length(); i++) {
                typesIncludedInFirstCompartment.add(firstCompartment.charAt(i));
            }

            Character repeatedType = null;
            int i = 0;
            while (i < secondCompartment.length() && repeatedType == null) {
                Character type = secondCompartment.charAt(i);
                if (typesIncludedInFirstCompartment.contains(type)) {
                    repeatedType = type;
                } else {
                    i++;
                }
            }

            totalPriority += PRIORITIES.get(repeatedType);
        }

        System.out.println("Total priority is: " + totalPriority);
    }

    private static Map<Character, Integer> populatePriorities() {
        Map<Character, Integer> priorities = new HashMap<>();
        String lowerCaseTypes = "abcdefghijklmnopqrstuvwxyz";

        for (int i = 0; i < lowerCaseTypes.length(); i++) {
            Character lowerCaseType = lowerCaseTypes.charAt(i);
            Character upperCaseType = Character.toUpperCase(lowerCaseType);

            priorities.put(lowerCaseType, i + 1);
            priorities.put(upperCaseType, i + 26 + 1);
        }
        
        return priorities;
    }
}
