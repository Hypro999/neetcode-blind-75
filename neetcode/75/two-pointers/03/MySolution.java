class MySolution {

    public int maxArea(int[] height) {
        int i = 0;
        int j = height.length - 1; // assumption: this will be > i to begin with.
        int bestArea = 0;
        int area;

        do {
            if (height[i] > height[j]) {
                area = (j - i) * height[j];
                j--;
            } else {
                area = (j - i) * height[i];
                i++;
            }
            if (area > bestArea) {
                bestArea = area;
            }
        } while (i < j);

        return bestArea;
    }
}