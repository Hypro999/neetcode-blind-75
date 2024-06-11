import java.util.Stack;

public class Solution {

    public static void main(String[] args) {
        char[][] board = new char[][]{
            new char[]{'A', 'B', 'C', 'E'},
            new char[]{'F', 'S', 'C', 'S'},
            new char[]{'A', 'D', 'E', 'E'}
        };
        String word = "ABCCED";
        System.out.println(new Solution().exist(board, word));
    }

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        char ch = word.charAt(0);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == ch) {
                    if (dfs(board, word, i, j)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private record StackFrame(
            int row,
            int col,
            int checkToPerform,
            int length) {
    }

    private boolean dfs(char[][] board, String word, int startRow, int startCol) {
        int m = board.length;
        int n = board[0].length;
        int sz = word.length();
        boolean[][] seen = new boolean[m][n];
        Stack<StackFrame> stack = new Stack<>();

        stack.push(new StackFrame(startRow, startCol, 1, 1));
        seen[startRow][startCol] = true;

        while (!stack.isEmpty()) {
            StackFrame top = stack.pop();
            if (top.length == sz) {
                return true;
            }
            switch (top.checkToPerform) {
                case 1:
                    if (inBounds(top.row - 1, m)
                            && !seen[top.row - 1][top.col]
                            && board[top.row - 1][top.col] == word.charAt(top.length)) {
                        stack.push(
                                new StackFrame(
                                        top.row,
                                        top.col,
                                        2,
                                        top.length));
                        stack.push(
                                new StackFrame(
                                        top.row - 1,
                                        top.col,
                                        1,
                                        top.length + 1));
                        seen[top.row - 1][top.col] = true;
                        break;
                    }
                    // fallthrough
                case 2:
                    if (inBounds(top.col - 1, n)
                            && !seen[top.row][top.col - 1]
                            && board[top.row][top.col - 1] == word.charAt(top.length)) {
                        stack.push(
                                new StackFrame(
                                        top.row,
                                        top.col,
                                        3,
                                        top.length));
                        stack.push(
                                new StackFrame(
                                        top.row,
                                        top.col - 1,
                                        1,
                                        top.length + 1));
                        seen[top.row][top.col - 1] = true;
                        break;
                    }
                    // fallthrough
                case 3:
                    if (inBounds(top.col + 1, n)
                            && !seen[top.row][top.col + 1]
                            && board[top.row][top.col + 1] == word.charAt(top.length)) {
                        stack.push(
                                new StackFrame(
                                        top.row,
                                        top.col,
                                        4,
                                        top.length));
                        stack.push(
                                new StackFrame(
                                        top.row,
                                        top.col + 1,
                                        1,
                                        top.length + 1));
                        seen[top.row][top.col + 1] = true;
                        break;
                    }
                    // fallthrough
                case 4:
                    if (inBounds(top.row + 1, m)
                            && !seen[top.row + 1][top.col]
                            && board[top.row + 1][top.col] == word.charAt(top.length)) {
                        stack.push(
                                new StackFrame(
                                        top.row,
                                        top.col,
                                        5,
                                        top.length));
                        stack.push(
                                new StackFrame(
                                        top.row + 1,
                                        top.col,
                                        1,
                                        top.length + 1));
                        seen[top.row + 1][top.col] = true;
                        break;
                    }
                    // fallthrough
                case 5:
                    seen[top.row][top.col] = false;
                    break;
            }
        }
        return false;
    }

    // This function should get inlined by the compiler.
    private boolean inBounds(int value, int upperBound) {
        return value >= 0 && value < upperBound;
    }
}
