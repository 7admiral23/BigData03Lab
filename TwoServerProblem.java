public class TwoServerProblem {

    // Distances between the cities
    static int[][] distances = {
        {0, 400, 300, 1900, 2600},
        {400, 0, 500, 2400, 2900},
        {300, 500, 0, 2300, 2700},
        {1900, 2400, 2300, 0, 800},
        {2600, 2900, 2700, 800, 0}
    };

    // City names for easier understanding
    static int L = 0, H = 1, B = 2, I = 3, A = 4;

    // Initial server positions
    static int server1 = B;
    static int server2 = L;

    public static void main(String[] args) {
        String sequence = "HALALI";
        int totalCost = 0;

        for (char city : sequence.toCharArray()) {
            int cityIndex = getCityIndex(city);
            int cost1 = distances[server1][cityIndex];
            int cost2 = distances[server2][cityIndex];

            if (cost1 <= cost2) {
                totalCost += cost1;
                server1 = cityIndex;
            } else {
                totalCost += cost2;
                server2 = cityIndex;
            }
        }

        System.out.println("Total cost: " + totalCost);
    }

    private static int getCityIndex(char city) {
        switch (city) {
            case 'L': return L;
            case 'H': return H;
            case 'B': return B;
            case 'I': return I;
            case 'A': return A;
            default: throw new IllegalArgumentException("Invalid city: " + city);
        }
    }
}
