# LeetCode 3841 - Palindromic Path Queries in a Tree

**Difficulty:** Hard

**Problem Link:** https://leetcode.com/problems/palindromic-path-queries-in-a-tree/

**Tags:** Tree, Bit Manipulation, Euler Tour, Fenwick Tree, Binary Lifting

## Problem Statement

Each node in a tree has a lowercase letter. Process two kinds of queries:

- `update u c`: Change node `u`'s letter to `c`.
- `query u v`: Check whether the letters on the path from `u` to `v`, including both endpoints, can be rearranged into a palindrome.

Return one boolean result for each path query.

## Intuition

A collection of letters can form a palindrome when at most one letter appears an odd number of times. A 26-bit mask records which letters have odd counts: each letter toggles its bit, so pairs cancel.

## Approach

1. Root the tree at node `0` and use an iterative DFS to assign entry and exit times to every node.
2. Each node's letter affects the root-to-node masks of all nodes in its subtree. A Fenwick tree applies XOR changes to a whole subtree range.
3. Use binary lifting to find the lowest common ancestor (LCA) for a path query.
4. Combine the root-to-node masks for both endpoints and add the LCA's letter back:
   `prefix(u) ^ prefix(v) ^ letterMask(lca)`.
5. The path can form a palindrome when its mask has at most one set bit.

## Example

**Input:** `n = 3, edges = [[0,1],[1,2]], s = "aac"`

**Queries:** `["query 0 2", "update 1 b", "query 0 2"]`

**Output:** `[true, false]`

The first path has letters `a, a, c`, which can be rearranged as `aca`. After node `1` changes to `b`, the path has `a, b, c`, which cannot form a palindrome.

## Java Solution

```java
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class Solution {
    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        int[] parent = new int[n];
        int[] depth = new int[n];
        int[] tin = new int[n];
        int[] tout = new int[n];

        int[] nodeStack = new int[n];
        int[] parentStack = new int[n];
        int[] nextChild = new int[n];

        int top = 0;
        int timer = 0;

        nodeStack[0] = 0;
        parentStack[0] = -1;
        tin[0] = timer++;

        while (top >= 0) {
            int u = nodeStack[top];

            if (nextChild[top] < graph.get(u).size()) {
                int v = graph.get(u).get(nextChild[top]++);

                if (v == parentStack[top]) {
                    continue;
                }

                parent[v] = u;
                depth[v] = depth[u] + 1;
                tin[v] = timer++;

                top++;
                nodeStack[top] = v;
                parentStack[top] = u;
                nextChild[top] = 0;
            } else {
                tout[u] = timer - 1;
                top--;
            }
        }

        int log = 1;
        while ((1 << log) <= n) {
            log++;
        }

        int[][] up = new int[log][n];
        for (int v = 0; v < n; v++) {
            up[0][v] = parent[v];
        }

        for (int k = 1; k < log; k++) {
            for (int v = 0; v < n; v++) {
                up[k][v] = up[k - 1][up[k - 1][v]];
            }
        }

        int[] letterMask = new int[n];
        Fenwick fenwick = new Fenwick(n);

        for (int v = 0; v < n; v++) {
            letterMask[v] = 1 << (s.charAt(v) - 'a');
            fenwick.rangeXor(tin[v], tout[v], letterMask[v]);
        }

        List<Boolean> answer = new ArrayList<>();

        for (String query : queries) {
            StringTokenizer tokens = new StringTokenizer(query);
            String type = tokens.nextToken();
            int u = Integer.parseInt(tokens.nextToken());

            if (type.equals("update")) {
                char newChar = tokens.nextToken().charAt(0);
                int newMask = 1 << (newChar - 'a');
                int change = letterMask[u] ^ newMask;

                if (change != 0) {
                    fenwick.rangeXor(tin[u], tout[u], change);
                    letterMask[u] = newMask;
                }
            } else {
                int v = Integer.parseInt(tokens.nextToken());
                int ancestor = lca(u, v, depth, up, log);

                int pathMask = fenwick.pointQuery(tin[u])
                        ^ fenwick.pointQuery(tin[v])
                        ^ letterMask[ancestor];

                answer.add((pathMask & (pathMask - 1)) == 0);
            }
        }

        return answer;
    }

    private int lca(int u, int v, int[] depth, int[][] up, int log) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int difference = depth[u] - depth[v];
        for (int k = log - 1; k >= 0; k--) {
            if ((difference & (1 << k)) != 0) {
                u = up[k][u];
            }
        }

        if (u == v) {
            return u;
        }

        for (int k = log - 1; k >= 0; k--) {
            if (up[k][u] != up[k][v]) {
                u = up[k][u];
                v = up[k][v];
            }
        }

        return up[0][u];
    }

    private static class Fenwick {
        private final int n;
        private final int[] tree;

        Fenwick(int n) {
            this.n = n;
            this.tree = new int[n + 1];
        }

        void rangeXor(int left, int right, int value) {
            add(left, value);
            add(right + 1, value);
        }

        private void add(int index, int value) {
            for (int i = index + 1; i <= n; i += i & -i) {
                tree[i] ^= value;
            }
        }

        int pointQuery(int index) {
            int result = 0;
            for (int i = index + 1; i > 0; i -= i & -i) {
                result ^= tree[i];
            }
            return result;
        }
    }
}
```

## Complexity

- **Time Complexity:** `O((n + q) log n)`
- **Space Complexity:** `O(n log n)`

Here, `q` is the number of queries.

**Language:** Java