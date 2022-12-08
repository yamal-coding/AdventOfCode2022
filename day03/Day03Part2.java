import java.io.*;
import java.util.*;

/*
 * Part 2 of day 3: https://adventofcode.com/2022/day/3
 */

public class Day03Part2 {

    public static final Map<Character, Integer> PRIORITIES = populatePriorities();

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scanner = new Scanner(new FileInputStream(file));

        int totalPriority = 0;
        while (scanner.hasNext()) {
            String firstRucksack = scanner.nextLine();
            String secondRucksack = scanner.nextLine();
            String thirdRucksack = scanner.nextLine();

            Set<Character> typesOfFirstRucksack = new HashSet<>();
            for (int i = 0; i < firstRucksack.length(); i++) {
                typesOfFirstRucksack.add(firstRucksack.charAt(i));
            }

            Set<Character> commonTypesOfFirstAndSecondRucksacks = new HashSet<>();
            for (int i = 0; i < secondRucksack.length(); i++) {
                Character secondType = secondRucksack.charAt(i);
                if (typesOfFirstRucksack.contains(secondType)) {
                    commonTypesOfFirstAndSecondRucksacks.add(secondType);
                }
            }

            int i = 0;
            Character repeatedType = null;
            while (i < thirdRucksack.length() && repeatedType == null) {
                Character thirdType = thirdRucksack.charAt(i);
                if (commonTypesOfFirstAndSecondRucksacks.contains(thirdType)) {
                    repeatedType = thirdType;
                }
                i++;
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
