import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class MySolution {
    private static final int UNVISITED = -1;

    private List<Position> directions = Arrays.asList(
            new Position(-1, 0),
            new Position(-1, 1),
            new Position(0, 1),
            new Position(1, 1),
            new Position(1, 0),
            new Position(1, -1),
            new Position(0, -1),
            new Position(-1, -1));

    class Position {
        final int y;

        final int x;

        Position(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + y;
            result = prime * result + x;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Position other = (Position) obj;
            if (y != other.y)
                return false;
            if (x != other.x)
                return false;
            return true;
        }
    }

    class VisitEntry {
        final Position currentPosition;

        final Set<Position> visited;

        VisitEntry(Position nextPosition, VisitEntry currentEntry) {
            this.currentPosition = nextPosition;
            this.visited = new HashSet<>();
            if (currentEntry != null) {
                this.visited.addAll(currentEntry.visited);
                this.visited.add(currentEntry.currentPosition);
            }
        }
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;

        int[][] hops = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                hops[i][j] = UNVISITED;
            }
        }

        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }
        hops[0][0] = 1;

        Queue<VisitEntry> q;
        q = new LinkedList<>();
        q.add(new VisitEntry(new Position(0, 0), null));

        while (!q.isEmpty()) {
            VisitEntry currentEntry = q.remove();
            for (Position direction : directions) {
                checkDirection(direction, currentEntry, grid, hops, q);
            }
        }

        return hops[n - 1][n - 1] == UNVISITED ? -1 : hops[n - 1][n - 1];
    }

    private void checkDirection(
            Position direction,
            VisitEntry currentEntry,
            int[][] grid,
            int[][] hops,
            Queue<VisitEntry> q) {
        int n = grid.length;
        int nextY = currentEntry.currentPosition.y + direction.y;
        int nextX = currentEntry.currentPosition.x + direction.x;
        Position nextPosition = new Position(nextY, nextX);
        if (!inBounds(nextPosition, n)
                || isOne(nextPosition, grid)
                || currentEntry.visited.contains(nextPosition)) {
            return;
        }
        int reqHops = hops[currentEntry.currentPosition.y][currentEntry.currentPosition.x]
                + 1;
        // See if it would be more beneficial to persue the current path.
        if (isVisited(nextPosition, hops)
                && reqHops >= hops[nextPosition.y][nextPosition.x]) {
            return;
        }
        hops[nextPosition.y][nextPosition.x] = reqHops;
        q.add(new VisitEntry(nextPosition, currentEntry));
    }

    private boolean inBounds(Position p, int n) {
        return !(p.x < 0 || p.y < 0 || p.x >= n || p.y >= n);
    }

    private boolean isOne(Position p, int[][] grid) {
        return grid[p.y][p.x] == 1;
    }

    private boolean isVisited(Position p, int[][] hops) {
        return hops[p.y][p.x] != UNVISITED;
    }
}
