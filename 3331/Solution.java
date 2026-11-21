import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        List<Integer>[] children = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }

        int[] newParent = parent.clone();

        int[] last = new int[26];
        Arrays.fill(last, -1);

        buildTree(0, children, newParent, last, s);

        List<Integer>[] updatedChildren = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            updatedChildren[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            updatedChildren[newParent[i]].add(i);
        }

        int[] answer = new int[n];
        calculateSize(0, updatedChildren, answer);

        return answer;
    }

    private void buildTree(int node, List<Integer>[] children,
                           int[] newParent, int[] last, String s) {
        int ch = s.charAt(node) - 'a';
        int previous = last[ch];

        if (previous != -1) {
            newParent[node] = previous;
        }

        last[ch] = node;

        for (int child : children[node]) {
            buildTree(child, children, newParent, last, s);
        }

        last[ch] = previous;
    }

    private int calculateSize(int node, List<Integer>[] children,
                              int[] answer) {
        int size = 1;

        for (int child : children[node]) {
            size += calculateSize(child, children, answer);
        }

        answer[node] = size;
        return size;
    }
}