public class MySolution {
    public int minFlips(int a, int b, int c) {
        int singles = a ^ b ^ c & (~(a & b)); // Each bit set requires 1 flip.
        int doubles = a & b & ~c; // Each bit set requires 2 flips.

        System.out.println(singles);
        System.out.println(countOnes(singles));
        System.out.println(doubles);
        System.out.println(countOnes(doubles));

        // singles and doubles are mutually exclusive - we either need to flip 1 or both.
        // basic reduction is possible at the cost of readability.
        // advanced reduction is possible with k-maps and gate-level minimization and stuff.

        return countOnes(singles) + 2 * countOnes(doubles);
    }

    private int countOnes(int a) {
        int n = 0;
        int mask = 1;
        while (a != 0) {
            if ((a & mask) == 1) {
                n++;
            }
            a = a >> 1;
        }
        return n;
    }
}