class EasySolution {

    public int search(int[] nums, int target) {

        // Index of the largest value in the array.
        int partitionIndex = findPartitionIndex(nums);

        // This section is a heuristic.
        // nums[partitionIndex] is the largest number.
        // nums[incr(partitionIndex)] is the smallest number.
        if (target > nums[partitionIndex]) {
            return -1;
        } else if (target < nums[incr(partitionIndex, nums.length)]) {
            return -1;
        }

        // Now perform binary search in the correct partition.
        if (target >= nums[0]) {
            // Search in the left half.
            return binarySearch(nums, target, 0, partitionIndex);
        } else {
            // Search in the right half.
            return binarySearch(nums, target, incr(partitionIndex, nums.length), nums.length - 1);
        }
    }

    private int findPartitionIndex(int[] nums) {

        int lowerIdx = 0;
        int upperIdx = nums.length - 1;

        int currIdx = -1;
        while (lowerIdx <= upperIdx) {
            currIdx = (lowerIdx + upperIdx) / 2;
            if (nums[currIdx] > nums[incr(currIdx, nums.length)]) {
                break;
            }
            if (nums[currIdx] >= nums[0]) {
                // We're in the left half and need to move right.
                lowerIdx = currIdx + 1;
            } else {
                // We're in the right half and need to move left.
                upperIdx = currIdx - 1;
            }
        }

        return currIdx;
    }

    private int binarySearch(int[] nums, int target, int lowerIdx, int upperIdx) {

        while (lowerIdx <= upperIdx) {
            int currIdx = (lowerIdx + upperIdx) / 2;
            if (nums[currIdx] == target) {
                return currIdx;
            }
            if (nums[currIdx] < target) {
                lowerIdx = currIdx + 1;
            } else {
                upperIdx = currIdx - 1;
            }
        }

        return -1;
    }

    private int incr(int i, int n) {
        if (i == n - 1) {
            return 0;
        }
        return i + 1;
    }

    private int decr(int i, int n) {
        if (i == 0) {
            return n - 1;
        }
        return i - 1;
    }
}