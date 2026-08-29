import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        Integer[] indices = new Integer[n];

        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, (a, b) -> Integer.compare(nums[a], nums[b]));

        int[] ans = new int[n];

        int i = 0;

        while (i < n) {
            int j = i;

            while (j + 1 < n &&
                   nums[indices[j + 1]] - nums[indices[j]] <= limit) {
                j++;
            }

            Integer[] group = Arrays.copyOfRange(indices, i, j + 1);

            Arrays.sort(group);

            for (int k = 0; k < group.length; k++) {
                ans[group[k]] = nums[indices[i + k]];
            }

            i = j + 1;
        }

        return ans;
    }
}