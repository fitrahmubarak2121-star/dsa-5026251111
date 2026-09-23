package lw01.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = new File("src/lw01/unguided/washes.txt");

        try (Scanner scanner = new Scanner(file)) {
            if (!scanner.hasNextInt()) {
                return;
            }
            int recordCount = scanner.nextInt();
            WashService[] services = new WashService[recordCount];

            for (int i = 0; i < recordCount; i++) {
                String type = scanner.next();
                String id = scanner.next();
                int days = scanner.nextInt();
                int units = scanner.nextInt();

                if (type.equalsIgnoreCase("MOTORCYCLE")) {
                    services[i] = new MotorcycleWash(id, days, units);
                } else if (type.equalsIgnoreCase("CAR")) {
                    services[i] = new CarWash(id, days, units);
                }
            }

            // Polimorfisme: Memanggil summary() langsung dari tipe acuan WashService
            for (WashService service : services) {
                if (service != null) {
                    System.out.println(service.summary());
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File washes.txt tidak ditemukan!");
        }
    }
}