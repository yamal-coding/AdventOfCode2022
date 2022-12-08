import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Day05Part1 {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("input.txt");
        Scanner scanner = new Scanner(new FileInputStream(file));

        Map<Integer, LinkedList<Character>> stacks = new HashMap<>();

        boolean allCratesHasBeenParsed = false;
        while (!allCratesHasBeenParsed) {
            String row = scanner.nextLine();
            int currentColumn = 0;
            int i = 0;
            while (i < row.length() && !allCratesHasBeenParsed) {
                i++; // skip possible '[' or space

                char crate = row.charAt(i);
                if (Character.isDigit(crate)) {
                    allCratesHasBeenParsed = true;
                } else {
                    if (crate != ' ') {
                        if (stacks.get(currentColumn) == null) {
                            stacks.put(currentColumn, new LinkedList<>());
                        }
                        stacks.get(currentColumn).addLast(crate);
                    }
                    i++; // skip stack element
                    i++; // skip possible ']' or space
                    i++; // skip stack separator space
                    currentColumn++;
                }
            }
        }

        scanner.nextLine(); // skip empty seprator line

        while (scanner.hasNext()) {
            scanner.next(); // Skip "move" token
            int numOfCrates = scanner.nextInt();
            scanner.next(); // Skip "from" token
            int origin = scanner.nextInt() - 1;
            scanner.next(); // Skip "destination" token
            int destination = scanner.nextInt() - 1;

            for (int i = 0; i < numOfCrates; i++) {
                char crateToBeMoved = stacks.get(origin).getFirst();
                stacks.get(origin).removeFirst();
                stacks.get(destination).addFirst(crateToBeMoved);
            }
        }

        String stackTops = stacks.values()
            .stream()
            .map((stack) -> stack.getFirst().toString())
            .collect(Collectors.joining());

        System.out.println("Stack tops are: " + stackTops);
    }
}
