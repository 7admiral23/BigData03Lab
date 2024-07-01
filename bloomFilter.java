
public class bloomFilter {
    public static void main(String[] args) {
        int m = 200_000_000; // size of bit array
        int n = 40_000_000; // number of elements
        int k = 2; // number of hash functions

        // a. Error probability
        double p = Math.pow(1 - Math.exp(-k * (double) n / m), k);
        System.out.println("Expected error probability: " + p);

        // b. Optimal number of hash functions
        int kOptimal = (int) Math.round((double) m / n * Math.log(2));
        System.out.println("Optimal number of hash functions: " + kOptimal);

        // c. Fraction of B filled
        double fractionFilled = 1 - Math.exp(-(double) k * n / m);
        System.out.println("Expected fraction filled: " + fractionFilled);
    }
}
