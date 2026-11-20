import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);

        Map<Integer, Integer> dp = new HashMap<>();
        int ans = 0;

        for (int num : nums) {
            int keep = dp.getOrDefault(num - 1, 0) + 1;
            int change = dp.getOrDefault(num, 0) + 1;

            dp.put(num, Math.max(dp.getOrDefault(num, 0), keep));
            dp.put(num + 1, Math.max(dp.getOrDefault(num + 1, 0), change));

            ans = Math.max(ans, Math.max(dp.get(num), dp.get(num + 1)));
        }

        return ans;
    }
}