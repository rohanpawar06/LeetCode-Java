import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] graph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(new int[] {edge[1], edge[2]});
        }

        // dist[node][run] = minimum cost to reach node
        // with a current run of 'run' identical labels.
        int[][] dist = new int[n][k + 1];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq =
                new PriorityQueue<>(Comparator.comparingInt(state -> state[0]));

        // State: {cost, node, current consecutive run}
        dist[0][1] = 0;
        pq.offer(new int[] {0, 0, 1});

        while (!pq.isEmpty()) {
            int[] state = pq.poll();
            int cost = state[0];
            int node = state[1];
            int run = state[2];

            if (cost != dist[node][run]) {
                continue;
            }

            if (node == n - 1) {
                return cost;
            }

            for (int[] edge : graph[node]) {
                int nextNode = edge[0];
                int weight = edge[1];

                int nextRun = labels.charAt(node) == labels.charAt(nextNode)
                        ? run + 1
                        : 1;

                if (nextRun > k) {
                    continue;
                }

                int nextCost = cost + weight;
                if (nextCost < dist[nextNode][nextRun]) {
                    dist[nextNode][nextRun] = nextCost;
                    pq.offer(new int[] {nextCost, nextNode, nextRun});
                }
            }
        }

        return -1;
    }
}
