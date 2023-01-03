import java.io.*;
import java.util.*;

/*
 * Part 2 of day 7: https://adventofcode.com/2022/day/7
 */

public class Day07Part2 {

    public static void main(String[] args) throws FileNotFoundException {
        Problem problem = new Problem();
        problem.resolve();
    }

    private static class Problem {

        private static final int MAX_DISK_SIZE = 70000000;
        private static final int MIN_REQUIRED_SIZE_FOR_UPDATE = 30000000;

        private final Scanner scanner;

        public Problem() throws FileNotFoundException {
            File file = new File("input.txt");
            this.scanner = new Scanner(new FileInputStream(file));
        }

        public void resolve() {
            scanner.nextLine().split(" "); // skip cd /

            List<Integer> dirSizes = new ArrayList<>();
            int rootDirSize = indexDirAndReturnSize(true, dirSizes);

            int actualUnusedSpace = MAX_DISK_SIZE - rootDirSize;
            int sizeToDelete = Integer.MAX_VALUE;
            for (int i = 0; i < dirSizes.size(); i++) {
                int candidateSizeToDelete = dirSizes.get(i);
                if (actualUnusedSpace + candidateSizeToDelete >= MIN_REQUIRED_SIZE_FOR_UPDATE && candidateSizeToDelete < sizeToDelete) {
                    sizeToDelete = candidateSizeToDelete;
                }
            }

            System.out.println("Size of dir to delete is: " + sizeToDelete);
        }

        private int indexDirAndReturnSize(boolean isLastDir, List<Integer> dirSizes) {
            // Skip ls command
            scanner.nextLine();

            int dirSize = 0;

            List<String> subDirs = new ArrayList<>();
            String[] newCdCommand = null;
            while (newCdCommand == null && scanner.hasNext()) {
                String[] lsEntry = scanner.nextLine().split(" ");

                String lsEntryType = lsEntry[0];
                switch (lsEntryType) {
                    case "$": {
                        newCdCommand = lsEntry;
                        break;
                    }
                    case "dir": {
                        String subDirName = lsEntry[1];
                        subDirs.add(subDirName);
                        break;
                    }
                    default: { // Entry is a file
                        int fileSize = Integer.parseInt(lsEntryType);
                        dirSize += fileSize;
                        break;
                    }
                }
            }

            int numOfSubDirs = subDirs.size();
            for (int i = 0; i < numOfSubDirs; i++) {
                if (newCdCommand == null) {
                    scanner.nextLine(); // skip cd <dir>
                } else {
                    newCdCommand = null;
                }
                int subDirSize = indexDirAndReturnSize(i == numOfSubDirs - 1, dirSizes);
                dirSize += subDirSize;
            }

            if (scanner.hasNext() && isLastDir) {
                // skip cd ..
                scanner.nextLine();
            }

            dirSizes.add(dirSize);

            return dirSize;
        }
    }
}
