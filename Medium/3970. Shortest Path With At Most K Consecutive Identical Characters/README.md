# LeetCode 3970 - Shortest Path With At Most K Consecutive Identical Characters

**Difficulty:** Medium

**Problem Link:** https://leetcode.com/problems/shortest-path-with-at-most-k-consecutive-identical-characters/

**Tags:** Graph, Dijkstra, Shortest Path, Priority Queue, State Space Search

## Problem Statement

Given a directed weighted graph and a lowercase label for each node, find the minimum total edge weight of a path from node 0 to node n - 1. The sequence of labels along the path must not contain more than k identical characters in a row. The starting node's label is included. Return -1 if no valid path exists.

## Intuition

The cheapest way to reach a node is not enough information by itself. Two paths can reach the same node at different costs and with different lengths for the current run of identical labels. The run length affects which next nodes are allowed.

So, treat each pair (node, runLength) as a separate state. Since all edge weights are positive, Dijkstra's algorithm finds the cheapest valid route through these states.

## Approach

1. Build an adjacency list for the directed graph.
2. Start at node 0 with a run length of 1, because its label counts.
3. For each edge, increase the run length if the next node has the same label; otherwise reset it to 1.
4. Skip a transition if its run length would exceed k.
5. Use Dijkstra's algorithm to track the minimum cost for each (node, runLength) state.
6. Return the first destination state removed from the priority queue, or -1 if none is reachable.

## Example

**Input:** n = 3, edges = [[0,1,1],[1,2,1],[0,2,3]], labels = "aab", k = 1

The route 0 -> 1 -> 2 has labels "aab", so it is invalid when k = 1. The direct route 0 -> 2 has labels "ab" and costs 3.

**Output:** 3

## Key Learning

When a path constraint depends on recent history, include the relevant history in the shortest-path state. Here, the node and the current consecutive-label run together determine which transitions are valid.

## Java Solution

```java
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
```

## Complexity

- **Time Complexity:** O((n * k + m * k) log(n * k))
- **Space Complexity:** O(n * k + m)

Here, m is the number of directed edges.

**Language:** Java
