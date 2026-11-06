import java.util.Arrays;

class Solution {
    public int[] countServers(int n, int[][] logs, int x, int[] queries) {
        Arrays.sort(logs, (a, b) -> Integer.compare(a[1], b[1]));
        int m = queries.length;
        int[][] sortedQueries = new int[m][2];
        for (int i = 0; i < m; i++) {
            sortedQueries[i][0] = queries[i];
            sortedQueries[i][1] = i;
        }
        Arrays.sort(sortedQueries, (a, b) -> Integer.compare(a[0], b[0]));
        int[] result = new int[m];
        int[] frequency = new int[n + 1];
        int left = 0, right = 0, active = 0;
        for (int[] query : sortedQueries) {
            int time = query[0], index = query[1];
            while (right < logs.length && logs[right][1] <= time) {
                int server = logs[right][0];
                if (frequency[server] == 0) active++;
                frequency[server]++;
                right++;
            }
            int startTime = time - x;
            while (left < right && logs[left][1] < startTime) {
                int server = logs[left][0];
                frequency[server]--;
                if (frequency[server] == 0) active--;
                left++;
            }
            result[index] = n - active;
        }
        return result;
    }
}
