package lw02.unguided;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> orders = new LinkedList<>();
        LinkedList<String[]> foodStock = new LinkedList<>();
        LinkedList<String[]> drinkStock = new LinkedList<>();
        LinkedList<String[]> successOrders = new LinkedList<>();

        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failed = new Stack<>();

        foodStock.add(new String[]{"Bakso", "2"});
        foodStock.add(new String[]{"Sate", "1"});
        foodStock.add(new String[]{"Soto", "2"});

        drinkStock.add(new String[]{"EsTeh", "4"});
        drinkStock.add(new String[]{"EsJeruk", "2"});

        Scanner scanner = new Scanner(
            Main.class.getResourceAsStream("/lw02/unguided/orders.txt")
        );

        while(scanner.hasNext()){
            String[] order = new String[4];
            order[0] = scanner.next();
            order[1] = scanner.next();
            order[2] = scanner.next();
            order[3] = scanner.next();
            orders.add(order);
        }

        scanner.close();

        queue.addAll(orders);

        while (!queue.isEmpty()) {
            
            String[] order = queue.poll();
            
            String name = order[0];
            String food = order[1];
            String drink = order[2];
            String table = order[3];

            String[] fData = null;
            for (String[] data : foodStock) {
                if (data[0].equals(food)) {
                    fData = data;
                    break;
                }
            }

            String[] dData = null;
            for (String[] data : drinkStock) {
                if (data[0].equals(drink)) {
                    dData = data;
                    break;
                }
            }

            boolean foodAvailable = true;
            if (!food.equals("-")) {
                if (fData == null || Integer.parseInt(fData[1]) <= 0) {
                    foodAvailable = false;
                }
            }

            boolean drinkAvailable = true;
            if (!drink.equals("-")) {
                if (dData == null || Integer.parseInt(dData[1]) <= 0) {
                    drinkAvailable = false;
                }
            }

            if (foodAvailable && drinkAvailable) {
                
                if (!food.equals("-")) {
                    int fStock = Integer.parseInt(fData[1]);
                    fStock -= 1;
                    fData[1] = String.valueOf(fStock);
                }

                if (!drink.equals("-")) {
                    int dStock = Integer.parseInt(dData[1]);
                    dStock -= 1;
                    dData[1] = String.valueOf(dStock);
                }

                successOrders.add(order);

            } else {
                // Failed order -> Stack
                failed.push(order);
            }
        }

        System.out.println("=== Successfully Processed Orders ===");
        for (String[] order : successOrders) {
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }

        System.out.println("\n=== Remaining Food Stock ===");
        for (String[] data : foodStock) {
            System.out.println(data[0] + ": " + data[1]);
        }

        System.out.println("\n=== Remaining Drink Stock ===");
        for (String[] data : drinkStock) {
            System.out.println(data[0] + ": " + data[1]);
        }

        System.out.println("\n=== Failed Orders ===");
        
        while (!failed.isEmpty()) {
            
            String[] order = failed.pop();
            
            System.out.println(
                order[0] + " " + 
                order[1] + " " + 
                order[2] + " " + 
                order[3]
            );
        }
    }
}