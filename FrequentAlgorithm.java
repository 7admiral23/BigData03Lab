import java.util.HashMap;
import java.util.Map;

public class FrequentAlgorithm {

    static final int k = 4;
    static final int monitorCount = k - 1;

    public static void main(String[] args) {
        String[] sequence = {"L", "K", "M", "P", "L", "K", "K", "M", "P", "K", "K", "P", "M", "L", "P"};
        Map<String, Integer> monitors = new HashMap<>();

        for (String item : sequence) {
            if (monitors.containsKey(item)) {
                monitors.put(item, monitors.get(item) + 1);
            } else if (monitors.size() < monitorCount) {
                monitors.put(item, 1);
            } else {
                decrementMonitors(monitors);
            }
            printMonitors(monitors);
        }
    }

    private static void decrementMonitors(Map<String, Integer> monitors) {
        monitors.entrySet().removeIf(entry -> entry.getValue() == 1);
        monitors.replaceAll((key, value) -> value - 1);
    }

    private static void printMonitors(Map<String, Integer> monitors) {
        System.out.println("Monitors: " + monitors);
    }
}
