import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

class MySolution {
    public List<String> summaryRanges(int[] nums) {
        List<String> ranges = new ArrayList<>();

        if (nums.length == 0) {
            return ranges;
        }

        if (nums.length == 1) {
            ranges.add(String.valueOf(nums[0]));
            return ranges;
        }


        BiConsumer<Integer, Integer> updateRange = (start, cursor) -> {
            if (cursor - 1 == start) {
                ranges.add(String.valueOf(nums[start]));
            } else {
                ranges.add(String.format("%d->%d", nums[start], nums[cursor - 1]));
            }
        };

        int start = 0;
        int cursor = 1;
        while (cursor < nums.length) {
            if (nums[cursor] - nums[cursor - 1] == 1) {
                cursor++;
                continue;
            }
            updateRange.accept(start, cursor);
            start = cursor;
            cursor++;
        }
        updateRange.accept(start, cursor);

        return ranges;
    }
}