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