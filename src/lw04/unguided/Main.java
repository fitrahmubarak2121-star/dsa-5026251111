package lw04.unguided;

import java.io.InputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InputStream is = Main.class.getResourceAsStream("/parcels.txt");
        if (is == null) {
            is = Main.class.getResourceAsStream("parcels.txt");
        }

        if (is != null) {
            Scanner sc = new Scanner(is);

            int n = sc.nextInt();
            int[] A = new int[n];
            for (int i = 0; i < n; i++) {
                A[i] = sc.nextInt();
            }

            int m = sc.nextInt();
            int[] targets = new int[m];
            for (int i = 0; i < m; i++) {
                targets[i] = sc.nextInt();
            }
            sc.close();

            int[] copiedA = new int[n];

            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - 1 - i; j++) {
                    if (A[j] < A[j + 1]) {
                        int temp = A[j];
                        A[j] = A[j + 1];
                        A[j + 1] = temp;
                    }
                }
                if (i == 1) {
                    for (int k = 0; k < n; k++) {
                        copiedA[k] = A[k];
                    }
                }
            }

            System.out.println("==== After 2 Passes ====");
            for (int i = 0; i < n; i++) {
                System.out.print(copiedA[i]);
                if (i < n - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();

            System.out.println("==== Fully Sorted ====");
            for (int i = 0; i < n; i++) {
                System.out.print(A[i]);
                if (i < n - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();

            System.out.println("==== Search Results ====");
            for (int t = 0; t < m; t++) {
                int target = targets[t];
                int low = 0;
                int high = n - 1;
                int steps = 0;
                int foundIdx = -1;

                while (low <= high && foundIdx == -1) {
                    steps = steps + 1;
                    int mid = (low + high) / 2;

                    if (A[mid] == target) {
                        foundIdx = mid;
                    } else if (target > A[mid]) {
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                }

                if (foundIdx != -1) {
                    System.out.println(target + ": FOUND at index " + foundIdx + " (steps: " + steps + ")");
                } else {
                    System.out.println(target + ": NOT FOUND (steps: " + steps + ")");
                }
            }
        }
    }
}