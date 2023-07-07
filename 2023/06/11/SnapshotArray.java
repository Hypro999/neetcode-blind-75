import java.util.ArrayList;
import java.util.List;

public class SnapshotArray {

    class Entry {
        int value;
        int lastValidSnapId;
    }

    private int next_snap_id;

    private List<List<Entry>> dataStore;

    public SnapshotArray(int length) {
        next_snap_id = 0;
        dataStore = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            List<Integer> timeLine = new ArrayList<>();
            timeLine.add(0);
        }
    }

    public void set(int index, int val) {
    }
    
    // O(N)
    public int snap() {
    }
    
    // O(1)
    public int get(int index, int snap_id) {
    }
}