package lw05.prelab;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class Main {
    int size;
    String[] names;
    int[][] graph;
    List<Integer> dfsorder = new ArrayList<>();

    public Main(int size) {
        this.size = size;
        this.names = new String[size];
        this.graph = new int[size][size];
    }

    public void addEdge(int v1, int v2, int weight) {
        graph[v1][v2] = weight;
        graph[v2][v1] = weight;
    }

    public void printPath(List<Integer> path) {
        for (int i = 0; i < path.size(); i++) {
            System.out.print(names[path.get(i)]);
            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }

    public List<Integer> bfs(int start) {
        List<Integer> order = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(start);
        queue.add(start);

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            order.add(curr);

            for (int i = 0; i < size; i++) {
                if (graph[curr][i] > 0 && !visited.contains(i)) {
                    visited.add(i);
                    queue.add(i);
                }
            }
        }
        return order;
    }

    public void dfs(int curr, Set<Integer> visited) {
        visited.add(curr);
        dfsorder.add(curr);

        for (int i = 0; i < size; i++) {
            if (graph[curr][i] > 0 && !visited.contains(i)) {
                dfs(i, visited);
            }
        }
    }

    public int findTheNextNode(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < size; i++) {
            if (!visited[i] && dist[i] < min) {
                min = dist[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public void route(int[] prev, int dest) {
        if (prev[dest] != -1) {
            route(prev, prev[dest]);
            System.out.print(" -> ");
        }
        System.out.print(names[dest]);
    }

    public void dijkstraAlg(int start, int dest) {
        int[] dist = new int[size];
        boolean[] visited = new boolean[size];
        int[] prev = new int[size];

        for (int i = 0; i < size; i++) {
            dist[i] = Integer.MAX_VALUE;
            prev[i] = -1;
        }
        dist[start] = 0;

        for (int count = 0; count < size; count++) {
            int u = findTheNextNode(dist, visited);
            if (u != -1) {
                visited[u] = true;
                for (int v = 0; v < size; v++) {
                    if (!visited[v] && graph[u][v] > 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                        dist[v] = dist[u] + graph[u][v];
                        prev[v] = u;
                    }
                }
            }
        }

        System.out.println("=== Shortest Time from " + names[start] + " ===");
        for (int i = 0; i < size; i++) {
            System.out.println(names[i] + ": " + dist[i] + " min");
        }

        System.out.println("=== Shortest Route from " + names[start] + " to " + names[dest] + " ===");
        route(prev, dest);
        System.out.println();
        System.out.println("Total time: " + dist[dest] + " min");
    }

    public static void main(String[] args) {
        InputStream is = Main.class.getResourceAsStream("/campus.txt");
        if (is == null) {
            is = Main.class.getResourceAsStream("campus.txt");
        }

        if (is != null) {
            Scanner sc = new Scanner(is);

            int n = sc.nextInt();
            Main g = new Main(n);

            for (int i = 0; i < n; i++) {
                g.names[i] = sc.next();
            }

            int e = sc.nextInt();
            for (int i = 0; i < e; i++) {
                int v1 = sc.nextInt();
                int v2 = sc.nextInt();
                int weight = sc.nextInt();
                g.addEdge(v1, v2, weight);
            }

            int start = sc.nextInt();
            int dest = sc.nextInt();
            sc.close();

            System.out.println("=== BFS from " + g.names[start] + " ===");
            g.printPath(g.bfs(start));

            System.out.println("=== DFS from " + g.names[start] + " ===");
            g.dfs(start, new HashSet<>());
            g.printPath(g.dfsorder);

            g.dijkstraAlg(start, dest);
        }
    }
}