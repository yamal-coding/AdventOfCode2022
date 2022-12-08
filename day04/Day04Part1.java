import java.io.*;
import java.util.*;

/*
 * Part 1 of day 4: https://adventofcode.com/2022/day/4
 */

public class Day04Part1 {

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scanner = new Scanner(new FileInputStream(file));

        int numOfFullyContainedPairs = 0;
        while (scanner.hasNext()) {
            String[] pairs = scanner.nextLine().split(",");

            String[] firstPair = pairs[0].split("-");
            Integer firstPairStart = Integer.parseInt(firstPair[0]);
            Integer firstPairEnd = Integer.parseInt(firstPair[1]);

            String[] secondPair = pairs[1].split("-");
            Integer secondPairStart = Integer.parseInt(secondPair[0]);
            Integer secondPairEnd = Integer.parseInt(secondPair[1]);

            boolean secondIsContainedInFirst = firstPairStart <= secondPairStart && secondPairEnd <= firstPairEnd;
            boolean firstIsContainedInSecond = secondPairStart <= firstPairStart && firstPairEnd <= secondPairEnd;

            if (secondIsContainedInFirst || firstIsContainedInSecond) {
                numOfFullyContainedPairs++;
            }
        }

        System.out.println("Num of fully contained pairs: " + numOfFullyContainedPairs);
    }
}
