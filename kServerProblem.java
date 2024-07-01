import java.util.*;

public class kServerProblem  {
    public static void main(String[] args) {
        int[][] distances = {
            {0, 400, 300, 1900, 2600},
            {400, 0, 500, 2400, 2900},
            {300, 500, 0, 2300, 2700},
            {1900, 2400, 2300, 0, 800},
            {2600, 2900, 2700, 800, 0}
        };
        String[] cities = {"L", "H", "B", "I", "A"};
        String[] servers = {"B", "L"};
        String[] requests = {"H", "A", "L", "A", "L", "I"};

        int cost = 0;
        for (String request : requests) {
            int requestIndex = Arrays.asList(cities).indexOf(request);
            int minDistance = Integer.MAX_VALUE;
            int minServerIndex = -1;

            for (int i = 0; i < servers.length; i++) {
                int serverIndex = Arrays.asList(cities).indexOf(servers[i]);
                if (distances[serverIndex][requestIndex] < minDistance) {
                    minDistance = distances[serverIndex][requestIndex];
                    minServerIndex = i;
                }
            }

            cost += minDistance;
            servers[minServerIndex] = request;
        }

        System.out.println("k-Server Problem - Greedy Algorithm Cost: " + cost);
    }
}
