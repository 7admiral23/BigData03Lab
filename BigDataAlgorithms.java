import java.util.*;

public class BigDataAlgorithms {

    public static void main(String[] args) {
        // 1. k-Server Problem
        kServerProblem();

        // 2. Bloom Filter
        bloomFilter();

        // 3. Frequent Algorithm
        frequentAlgorithm();

        // 4. Cuckoo Hashing
        cuckooHashing();
    }

    // 1. k-Server Problem
    static void kServerProblem() {
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

    // 2. Bloom Filter
    static void bloomFilter() {
        int m = 200_000_000; // size of bit array
        int n = 40_000_000; // number of elements
        int k = 2; // number of hash functions

        // a. Error probability
        double p = Math.pow(1 - Math.exp(-k * (double) n / m), k);
        System.out.println("Bloom Filter - Expected error probability: " + p);

        // b. Optimal number of hash functions
        int kOptimal = (int) Math.round((double) m / n * Math.log(2));
        System.out.println("Bloom Filter - Optimal number of hash functions: " + kOptimal);

        // c. Fraction of B filled
        double fractionFilled = 1 - Math.exp(-(double) k * n / m);
        System.out.println("Bloom Filter - Expected fraction filled: " + fractionFilled);
    }

    // 3. Frequent Algorithm
    static void frequentAlgorithm() {
        String[] input = {"L", "K", "M", "P", "L", "K", "K", "M", "P", "K", "K", "P", "M", "L", "P"};
        int k = 4;
        Map<String, Integer> counters = new HashMap<>();

        for (String item : input) {
            if (counters.containsKey(item)) {
                counters.put(item, counters.get(item) + 1);
            } else if (counters.size() < k - 1) {
                counters.put(item, 1);
            } else {
                if (!counters.isEmpty()) {
                    for (Map.Entry<String, Integer> entry : new HashMap<>(counters).entrySet()) {
                        counters.put(entry.getKey(), entry.getValue() - 1);
                        if (counters.get(entry.getKey()) == 0) {
                            counters.remove(entry.getKey());
                        }
                    }
                }
            }

            System.out.println("Item: " + item + ", Counters: " + counters);
        }
    }

    // 4. Cuckoo Hashing
    static void cuckooHashing() {
        String[] table = new String[11];
        table[2] = "Y"; table[3] = "D"; table[4] = "Z"; table[5] = "A";
        table[6] = "X"; table[8] = "C"; table[9] = "B"; table[10] = "E";

        Map<String, Integer> h1 = new HashMap<>();
        Map<String, Integer> h2 = new HashMap<>();
        h1.put("P", 3); h2.put("P", 9);

        String elementToInsert = "P";
        int maxIterations = 20;
        for (int i = 0; i < maxIterations; i++) {
            int pos = h1.get(elementToInsert);
            if (table[pos] == null) {
                table[pos] = elementToInsert;
                break;
            }
            String temp = table[pos];
            table[pos] = elementToInsert;
            elementToInsert = temp;

            pos = h2.get(elementToInsert);
            if (table[pos] == null) {
                table[pos] = elementToInsert;
                break;
            }
            temp = table[pos];
            table[pos] = elementToInsert;
            elementToInsert = temp;
        }

        System.out.println("Cuckoo Hashing - Final table state: " + Arrays.toString(table));
    }
}