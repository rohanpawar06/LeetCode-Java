class Solution {
    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int maxValue = 0;
        for (int[] row : grid) {
            for (int value : row) {
                maxValue = Math.max(maxValue, value);
            }
        }

        int xorStates = 1;
        while (xorStates <= maxValue) {
            xorStates <<= 1;
        }

        boolean[][][] reachable = new boolean[m][n][xorStates];
        reachable[0][0][grid[0][0]] = true;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                int value = grid[i][j];
                for (int xor = 0; xor < xorStates; xor++) {
                    int previousXor = xor ^ value;

                    if ((i > 0 && reachable[i - 1][j][previousXor])
                            || (j > 0 && reachable[i][j - 1][previousXor])) {
                        reachable[i][j][xor] = true;
                    }
                }
            }
        }

        for (int xor = 0; xor < xorStates; xor++) {
            if (reachable[m - 1][n - 1][xor]) {
                return xor;
            }
        }

        return -1;
    }
}