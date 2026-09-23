package lw01.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<PrintJob> printJobs = new ArrayList<>();

        try {
            File file = new File("src/lw01/prelab/jobs.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String type = scanner.next();
                String id = scanner.next();
                int pages = scanner.nextInt();

                if (type.equalsIgnoreCase("MONO")) {
                    printJobs.add(new MonoPrint(id, pages));
                } else if (type.equalsIgnoreCase("COLOUR")) {
                    printJobs.add(new ColourPrint(id, pages));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File jobs.txt tidak ditemukan!");
            return;
        }

        // Output ringkas sesuai format
        for (PrintJob job : printJobs) {
            String label = (job instanceof MonoPrint) ? "Mono" : "Colour";
            System.out.println(job.getJobId() + " | " + label + " | " + (int) job.calculateCost());
        }
    }
}