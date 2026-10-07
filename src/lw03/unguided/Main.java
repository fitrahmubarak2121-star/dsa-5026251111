package lw03.unguided;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        
        Set<String> registeredStudents = new LinkedHashSet<>();
        Scanner scReg = new Scanner(Main.class.getResourceAsStream("registrations.txt"));
        while (scReg.hasNextLine()) {
            String id = scReg.nextLine().trim();
            if (!id.isEmpty()) {
                registeredStudents.add(id);
            }
        }
        scReg.close();

        List<String> checkInResults = new LinkedList<>();
        Set<String> checkedInStudents = new LinkedHashSet<>();
        int rejectedAttempts = 0;

        Scanner scCheck = new Scanner(Main.class.getResourceAsStream("checkins.txt"));
        while (scCheck.hasNextLine()) {
            String id = scCheck.nextLine().trim();
            if (id.isEmpty()) continue;

            if (registeredStudents.contains(id)) {
                if (checkedInStudents.contains(id)) {
                    checkInResults.add(id + ": Rejected (already checked in)");
                    rejectedAttempts++;
                } else {
                    checkedInStudents.add(id);
                    checkInResults.add(id + ": Checked in");
                }
            } else {
                checkInResults.add(id + ": Rejected (not registered)");
                rejectedAttempts++;
            }
        }
        scCheck.close();

        System.out.println("===== Event Check-In Results =====");
        for (String result : checkInResults) {
            System.out.println(result);
        }

        System.out.println("\n===== Final Event Summary =====");
        System.out.println("Registered students: " + registeredStudents.size());
        System.out.println("Successful check-ins: " + checkedInStudents.size());
        System.out.println("Absent students: " + (registeredStudents.size() - checkedInStudents.size()));
        System.out.println("Rejected attempts: " + rejectedAttempts);
    }
}