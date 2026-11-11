package lw04.prelab;

import java.io.InputStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InputStream is = Main.class.getResourceAsStream("/branches.txt");
        if (is == null) {
            is = Main.class.getResourceAsStream("branches.txt");
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

            for (int i = 1; i <= 3; i++) {
                int key = A[i];
                int j = i - 1;

                while (j >= 0 && A[j] < key) {
                    A[j + 1] = A[j];
                    j = j - 1;
                }
                A[j + 1] = key;
            }

            for (int i = 0; i < n; i++) {
                System.out.print(A[i]);
                if (i < n - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();

            for (int t = 0; t < m; t++) {
                int target = targets[t];
                int indeksKetemu = -1;

                for (int i = 0; i < n; i++) {
                    if (A[i] == target && indeksKetemu == -1) {
                        indeksKetemu = i;
                    }       
                }

                if (indeksKetemu != -1) {
                    System.out.println(target + ": FOUND at index " + indeksKetemu);
                } else {
                    System.out.println(target + ": NOT FOUND");
                }
            }
        }
    }
}