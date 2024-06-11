class Solution {

    enum Direction {
        FORWARD,
        BACKWARD,
        STOP
    }

    public int findMin(int[] nums) {

        // This is a special case. The array was never rotated and we can just return nums[0].
        if (nums[nums.length - 1] >= nums[0]) {
            return nums[0];
        }

        int lowerIdx = 0;
        int upperIdx = nums.length - 2;
        int currentIdx = -1;
        while (lowerIdx <= upperIdx) {
            currentIdx = (upperIdx + lowerIdx) / 2;
            Direction direction = findDirection(nums, currentIdx);
            switch (direction) {
                case FORWARD:
                    lowerIdx = currentIdx + 1;
                    break;
                case BACKWARD:
                    upperIdx = currentIdx - 1;
                    break;
                case STOP:
                    return nums[currentIdx + 1];
            }
        }

        // This line will never be reached.
        return -1;
    }

    private Direction findDirection(int[] nums, int idx) {
        int diff = nums[idx + 1] - nums[idx];
        if (diff < 0) {
            return Direction.STOP;
        }
        if (nums[idx] >= nums[0]) {
            return Direction.FORWARD;
        }
        return Direction.BACKWARD;
    }
}
