import java.io.*;

/*
 * Part 1 of day 6: https://adventofcode.com/2022/day/6
 */

public class Day06Part1 {

    public static final int MARKER_LENGTH = 4;

    public static void main(String[] args) throws FileNotFoundException {
        Day06 problem = new Day06(MARKER_LENGTH);

        problem.resolve();
    }
}
