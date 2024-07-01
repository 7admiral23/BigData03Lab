import java.util.*;

public class cuckooHashing {
    public static void main(String[] args) {
        String[] table = new String[11];
        table[2] = "Y"; table[3] = "D"; table[4] = "Z"; table[5] = "A";
        table[6] = "X"; table[8] = "C"; table[9] = "B"; table[10] = "E";

        Map<String, Integer> h1 = new HashMap<>();
        Map<String, Integer> h2 = new HashMap<>();
        
        // Define hash functions
        h1.put("A", 2); h1.put("B", 10); h1.put("C", 6); h1.put("D", 5); h1.put("E", 8);
        h1.put("X", 6); h1.put("Y", 9); h1.put("Z", 6); h1.put("P", 3);
        
        h2.put("A", 5); h2.put("B", 9); h2.put("C", 8); h2.put("D", 3); h2.put("E", 10);
        h2.put("X", 3); h2.put("Y", 2); h2.put("Z", 4); h2.put("P", 9);

        String elementToInsert = "P";
        int maxIterations = 20;
        
        System.out.println("Initial table state: " + Arrays.toString(table));
        
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
            
            System.out.println("After iteration " + (i+1) + ": " + Arrays.toString(table));
        }

        System.out.println("Final table state: " + Arrays.toString(table));
    }
}