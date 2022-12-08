import java.io.*;
import java.util.*;

/*
 * Part 1 of day 6: https://adventofcode.com/2022/day/6
 */

public class Day06Part1 {

    public static final int MARKER_LENGTH = 4;

    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scanner = new Scanner(new FileInputStream(file));

        String message = scanner.nextLine();

        Deque<Character> markerElements = new LinkedList<>();
        Map<Character, Integer> markerElementsOccurrences = new HashMap<>();
        int markerEndPosition = 0;
        boolean firstMakerFound = false;

        while (markerEndPosition < message.length() && !firstMakerFound) {
            char currentElement = message.charAt(markerEndPosition);

            markerElements.addLast(currentElement);
            Integer currentElmentOccurrences = markerElementsOccurrences.get(currentElement);
            if (currentElmentOccurrences != null) {
                markerElementsOccurrences.put(currentElement, currentElmentOccurrences + 1);
            } else {
                markerElementsOccurrences.put(currentElement, 1);
            }

            if (markerElements.size() == MARKER_LENGTH + 1) {
                char previousFirstMarkerElement = markerElements.getFirst();
                markerElements.removeFirst();
                Integer previousFirstMarkerElementOccurrences = markerElementsOccurrences.get(previousFirstMarkerElement);
                if (previousFirstMarkerElementOccurrences != null) {
                    markerElementsOccurrences.put(previousFirstMarkerElement, previousFirstMarkerElementOccurrences - 1);
                }
                if (markerElementsOccurrences.get(previousFirstMarkerElement) == 0) {
                    markerElementsOccurrences.remove(previousFirstMarkerElement);
                }
            }

            if (markerElementsOccurrences.size() == MARKER_LENGTH) {
                firstMakerFound = true;
            }

            markerEndPosition++;
        }

        System.out.println("Marker end position is: " + markerEndPosition);
    }
}
