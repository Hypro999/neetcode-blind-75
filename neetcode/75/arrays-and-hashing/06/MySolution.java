class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] solution = new int[n];

        solution[0] = 1;
        for (int i = 1; i < n; i++) {
            // Compute product of prefixes.
            solution[i] = solution[i - 1] * nums[i - 1];
        }

        int acc = 1;
        for (int i = n - 2; i >= 0; i--) {
            // Compute product of suffixes and simultaneously
            // use that to update the solution.
            acc = acc * nums[i + 1];
            solution[i] = solution[i] * acc;
        }

        return solution;
    }
}