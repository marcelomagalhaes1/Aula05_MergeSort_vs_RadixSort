import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    private static final String FILE_NAME = "dados100_mil.txt";

    public static void main(String[] args) {
        try {
            int[] data = readDataFromFile(FILE_NAME);

            int[] mergeSortData = data.clone();
            long mergeSortStart = System.nanoTime();
            MergeSort.mergeSort(mergeSortData);
            long mergeSortEnd = System.nanoTime();
            String mergeSortTime = formatTime(mergeSortEnd - mergeSortStart);

            int[] radixSortData = data.clone();
            long radixSortStart = System.nanoTime();
            RadixSortMSD.radixSort(radixSortData);
            long radixSortEnd = System.nanoTime();
            String radixSortTime = formatTime(radixSortEnd - radixSortStart);

            System.out.println("Merge Sort Time: " + mergeSortTime);
            System.out.println("Radix Sort Time: " + radixSortTime);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int[] readDataFromFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        int[] data = new int[100000];
        String line;
        int index = 0;
        while ((line = reader.readLine()) != null && index < data.length) {
            try {
                data[index++] = Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.err.println("Warning: Skipping invalid data line: " + line);
            }
        }
        reader.close();
        return data;
    }

    private static String formatTime(long nanoseconds) {
        long milliseconds = nanoseconds / 1_000_000;
        long seconds = milliseconds / 1_000;
        milliseconds %= 1_000;
        long minutes = seconds / 60;
        seconds %= 60;
        long hours = minutes / 60;
        minutes %= 60;

        return String.format("%02d:%02d:%02d:%03d", hours, minutes, seconds, milliseconds);
    }
}