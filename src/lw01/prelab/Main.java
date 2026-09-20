package lw01.prelab;
import java.io.File;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<PrintJob> printJobs = new ArrayList<>();
        try {
            // Path disesuaikan dengan struktur folder VS Code
            Scanner scanner = new Scanner(new File("src/lw01/prelab/jobs.txt")); 
            while (scanner.hasNext()) {
                String type = scanner.next();
                String id = scanner.next();
                int pages = scanner.nextInt();
                if (type.equals("MONO")) printJobs.add(new MonoPrint(id, pages));
                else if (type.equals("COLOUR")) printJobs.add(new ColourPrint(id, pages));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File jobs.txt tidak ditemukan.");
        }

        for (PrintJob job : printJobs) {
            System.out.println(job.summary());
        }
    }
}