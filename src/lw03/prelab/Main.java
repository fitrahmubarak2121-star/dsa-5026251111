package lw03.prelab;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        
        System.out.println("===== Problem 1 =====");
        List<String> playlist = new LinkedList<>();
        
        Scanner sc1 = new Scanner(Main.class.getResourceAsStream("playlist.txt"));
        while (sc1.hasNext()) {
            String cmd = sc1.next();
            
            if (cmd.equals("INSERT")) {
                int index = sc1.nextInt();
                String song = sc1.nextLine().trim();
                playlist.add(index, song);
            } else if (cmd.equals("ADD")) {
                String song = sc1.nextLine().trim();
                playlist.add(song);
            } else if (cmd.equals("REMOVE")) {
                String song = sc1.nextLine().trim();
                playlist.remove(song);
            }
        }
        sc1.close();
        
        System.out.println("Total songs: " + playlist.size());
        int i = 1;
        for (String song : playlist) {
            System.out.println(i + ": " + song);
            i++;
        }

        System.out.println("\n===== Problem 2 =====");
        Set<String> participants = new LinkedHashSet<>();
        int duplicateCount = 0;
        
        Scanner sc2 = new Scanner(Main.class.getResourceAsStream("participants.txt"));
        while (sc2.hasNextLine()) {
            String name = sc2.nextLine().trim();
            if (name.isEmpty()) continue;
            
            if (participants.contains(name)) {
                duplicateCount++;
            } else {
                participants.add(name);
            }
        }
        sc2.close();
        
        System.out.println("Unique participants: " + participants.size());
        int j = 1;
        for (String p : participants) {
            System.out.println(j + ". " + p);
            j++;
        }
        System.out.println("Duplicate registrations: " + duplicateCount);

        System.out.println("\n===== Problem 3 =====");
        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;
        
        Scanner sc3 = new Scanner(Main.class.getResourceAsStream("inventory.txt"));
        while (sc3.hasNext()) {
            String type = sc3.next();
            String product = sc3.next();
            int qty = sc3.nextInt();

            if (type.equals("ADD")) {
                if (inventory.containsKey(product)) {
                    int current = inventory.get(product);
                    inventory.put(product, current + qty);
                } else {
                    inventory.put(product, qty);
                }
            } else if (type.equals("SELL")) {
                if (inventory.containsKey(product)) {
                    int current = inventory.get(product);
                    if (current >= qty) {
                        inventory.put(product, current - qty);
                    } else {
                        failedSales++;
                    }
                } else {
                    failedSales++;
                }
            }
        }
        sc3.close();
        
        for (String key : inventory.keySet()) {
            System.out.println(key + ": " + inventory.get(key));
        }
        System.out.println("Failed sales: " + failedSales);
    }
}