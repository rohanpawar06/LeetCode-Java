import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int closedIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] != 0) continue;

                boolean closed = true;
                Deque<int[]> stack = new ArrayDeque<>();
                stack.push(new int[]{r, c});
                grid[r][c] = 1;

                while (!stack.isEmpty()) {
                    int[] cell = stack.pop();
                    int row = cell[0], col = cell[1];
                    if (row == 0 || row == rows - 1 || col == 0 || col == cols - 1) {
                        closed = false;
                    }

                    for (int[] d : directions) {
                        int nr = row + d[0], nc = col + d[1];
                        if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == 0) {
                            grid[nr][nc] = 1;
                            stack.push(new int[]{nr, nc});
                        }
                    }
                }

                if (closed) count++;
            }
        }
        return count;
    }
}
