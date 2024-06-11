class Solution {

    public int search(int[] nums, int target) {

        int lowerIdx = 0;
        int upperIdx = nums.length - 1;

        int currentIdx;
        while (lowerIdx <= upperIdx) {
            currentIdx = (lowerIdx + upperIdx) / 2;
            int currentNum = nums[currentIdx];

            if (target == currentNum) {
                return currentIdx;
            }

            if (currentNum >= nums[0]) {
                // In the left half.
                if (target > currentNum) {
                    // Go right.
                    lowerIdx = currentIdx + 1;
                } else {
                    if (target >= nums[lowerIdx]) {
                        // Go left.
                        upperIdx = currentIdx - 1;
                    } else {
                        // Go right.
                        lowerIdx = currentIdx + 1;
                    }
                }
            } else {
                // In the right half.
                if (target < currentNum) {
                    // Go left.
                    upperIdx = currentIdx - 1;
                } else {
                    if (target <= nums[upperIdx]) {
                        // Go right.
                        lowerIdx = currentIdx + 1;
                    } else {
                        upperIdx = currentIdx - 1;
                    }
                }
            }
        }

        return -1;
    }
}