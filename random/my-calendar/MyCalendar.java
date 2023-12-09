import java.util.TreeSet;


class MyCalendar {

    private class Range implements Comparable<Range> {

        int start;

        int end;

        Range(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Range other) {
            if (this.end <= other.start) {
                return -1;
            }
            if (this.start >= other.end) {
                return 1;
            }
            // Overlapping ranges will be considered equal.
            return 0;
        }
    }

    TreeSet<Range> rbTree;

    public MyCalendar() {
        this.rbTree = new TreeSet<Range>();
    }

    public boolean book(int start, int end) {
        Range range = new Range(start, end);
        if (rbTree.contains(range)) {
            return false;
        }
        rbTree.add(range);
        return true;
    }
}
