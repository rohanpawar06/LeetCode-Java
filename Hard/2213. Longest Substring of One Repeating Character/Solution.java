class Solution {
    class Node {
        char leftChar;
        char rightChar;
        int prefix;
        int suffix;
        int best;
        int len;

        Node() {
        }

        Node(char ch) {
            leftChar = ch;
            rightChar = ch;
            prefix = 1;
            suffix = 1;
            best = 1;
            len = 1;
        }
    }

    Node[] tree;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        tree = new Node[4 * n];

        build(1, 0, n - 1, s);

        int[] ans = new int[queryIndices.length];

        for (int i = 0; i < queryIndices.length; i++) {
            update(1, 0, n - 1, queryIndices[i], queryCharacters.charAt(i));
            ans[i] = tree[1].best;
        }

        return ans;
    }

    private void build(int node, int left, int right, String s) {
        if (left == right) {
            tree[node] = new Node(s.charAt(left));
            return;
        }

        int mid = left + (right - left) / 2;

        build(node * 2, left, mid, s);
        build(node * 2 + 1, mid + 1, right, s);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private void update(int node, int left, int right, int index, char ch) {
        if (left == right) {
            tree[node] = new Node(ch);
            return;
        }

        int mid = left + (right - left) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, ch);
        } else {
            update(node * 2 + 1, mid + 1, right, index, ch);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private Node merge(Node a, Node b) {
        Node res = new Node();

        res.len = a.len + b.len;
        res.leftChar = a.leftChar;
        res.rightChar = b.rightChar;

        res.prefix = a.prefix;

        if (a.prefix == a.len && a.rightChar == b.leftChar) {
            res.prefix = a.len + b.prefix;
        }

        res.suffix = b.suffix;

        if (b.suffix == b.len && a.rightChar == b.leftChar) {
            res.suffix = b.len + a.suffix;
        }

        res.best = Math.max(a.best, b.best);

        if (a.rightChar == b.leftChar) {
            res.best = Math.max(res.best, a.suffix + b.prefix);
        }

        return res;
    }
}