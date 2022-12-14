import java.io.*;
import java.util.*;

/*
 * Part 1 of day 7: https://adventofcode.com/2022/day/7
 */

public class Day07Part1 {

    public static void main(String[] args) throws FileNotFoundException {
        Problem problem = new Problem();
        problem.resolve();
    }

    private static class Problem {

        private static final int MAX_DIR_SIZE = 100_000;

        private final Scanner scanner;

        private int totalSize = 0;

        public Problem() throws FileNotFoundException {
            File file = new File("input.txt");
            this.scanner = new Scanner(new FileInputStream(file));
        }

        public void resolve() {
            scanner.nextLine(); // skip cd /
            calculateSizeDir(true);

            System.out.println("Total size is " + totalSize);
        }

        private int calculateSizeDir(boolean isLastDir) {
            // Skip ls command
            scanner.nextLine();

            int dirSize = 0;
            int numOfSubDirs = 0;

            String[] newCdCommand = null;
            while (newCdCommand == null && scanner.hasNext()) {
                String[] lsEntry = scanner.nextLine().split(" ");

                String lsEntryFirstItem = lsEntry[0];
                switch (lsEntryFirstItem) {
                    case "$": {
                        newCdCommand = lsEntry;
                        break;
                    }
                    case "dir": {
                        numOfSubDirs++;
                        break;
                    }
                    default: { // Entry is a file
                        int fileSize = Integer.parseInt(lsEntryFirstItem);
                        dirSize += fileSize;
                        break;
                    }
                }
            }

            for (int i = 0; i < numOfSubDirs; i++) {
                if (newCdCommand == null) {
                    scanner.nextLine(); // skip cd <dir>
                } else {
                    newCdCommand = null;
                }
                int subDirSize = calculateSizeDir(i == numOfSubDirs - 1);
                dirSize += subDirSize;
            }

            if (scanner.hasNext() && isLastDir) {
                // skip cd ..
                scanner.nextLine();
            }

            if (dirSize <= MAX_DIR_SIZE) {
                totalSize += dirSize;
            }

            return dirSize;
        }
    }

}
