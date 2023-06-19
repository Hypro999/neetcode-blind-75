import java.util.ArrayList;
import java.util.List;

public class SnapshotArray {

    static class Entry {
        int val;

        int sinceSnapId;

        Entry(int val, int snapId) {
            this.val = val;
            this.sinceSnapId = snapId;
        }
    }

    private List<List<Entry>> entries;

    private int nextSnapId;

    public SnapshotArray(int length) {
        this.entries = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            ArrayList<Entry> indexHistory = new ArrayList<>();
            indexHistory.add(new Entry(0, 0));
            this.entries.add(indexHistory);
        }
        this.nextSnapId = 0;
    }

    public void set(int index, int val) {
        List<Entry> indexHistory = entries.get(index);
        Entry latestEntry = indexHistory.get(indexHistory.size() - 1);
        if (latestEntry.sinceSnapId != nextSnapId) {
            latestEntry = new Entry(val, nextSnapId);
            indexHistory.add(latestEntry);
        } else {
            latestEntry.val = val;
        }
    }

    public int snap() {
        int currentSnapId = nextSnapId;
        nextSnapId++;
        return currentSnapId;
    }

    public int get(int index, int snapId) {
        List<Entry> indexHistory = entries.get(index);
        int lower = 0;
        int upper = indexHistory.size() - 1;
        while (lower <= upper) {
            int currentIndex = (lower + upper) / 2;
            Entry currentEntry = indexHistory.get(currentIndex);
            if (currentEntry.sinceSnapId > snapId) {
                upper = currentIndex - 1;
            } else if (currentEntry.sinceSnapId < snapId) {
                lower = currentIndex + 1;
            } else {
                return currentEntry.val;
            }
        }
        int currentIndex = (lower + upper) / 2;
        Entry currentEntry = indexHistory.get(currentIndex);
        if (currentEntry.sinceSnapId > snapId) {
            currentEntry = indexHistory.get(currentIndex - 1);
            // ^ Should always be greater than 0. For any valid input (non-negative).
        }
        return currentEntry.val;
    }
}