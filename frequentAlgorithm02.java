import java.util.*;

public class frequentAlgorithm02 {
    public static void main(String[] args) {
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
}
