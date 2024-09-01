import java.util.Arrays;

public class RadixSortMSD {

    private static final int BASE = 10;

    public static void radixSort(int[] array) {
        if (array.length == 0) {
            return;
        }
        int max = Arrays.stream(array).max().orElse(0);
        radixSort(array, 0, array.length - 1, getMaxDigits(max));
    }

    private static void radixSort(int[] array, int left, int right, int digit) {
        if (digit == 0 || left >= right) {
            return;
        }

        int[] count = new int[BASE];
        int[] temp = new int[array.length];
        int exp = (int) Math.pow(BASE, digit - 1);

        for (int i = left; i <= right; i++) {
            int digitValue = (array[i] / exp) % BASE;
            count[digitValue]++;
        }

        for (int i = 1; i < BASE; i++) {
            count[i] += count[i - 1];
        }

        for (int i = right; i >= left; i--) {
            int digitValue = (array[i] / exp) % BASE;
            temp[--count[digitValue]] = array[i];
        }

        System.arraycopy(temp, left, array, left, right - left + 1);

        if (digit > 1) {
            for (int i = 0; i < BASE; i++) {
                int start = i == 0 ? left : count[i - 1];
                int end = count[i] - 1;
                if (start < end) {
                    radixSort(array, start, end, digit - 1);
                }
            }
        }
    }

    private static int getMaxDigits(int number) {
        int digits = 0;
        while (number > 0) {
            number /= BASE;
            digits++;
        }
        return digits;
    }
}