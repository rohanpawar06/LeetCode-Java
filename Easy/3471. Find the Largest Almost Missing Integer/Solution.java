class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> freq = new HashMap<>();

        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        if (k == 1) {
            int ans = -1;

            for (int num : nums) {
                if (freq.get(num) == 1) {
                    ans = Math.max(ans, num);
                }
            }

            return ans;
        }

        if (k == n) {
            int ans = Integer.MIN_VALUE;

            for (int num : nums) {
                ans = Math.max(ans, num);
            }

            return ans;
        }

        int ans = -1;

        if (freq.get(nums[0]) == 1) {
            ans = Math.max(ans, nums[0]);
        }

        if (freq.get(nums[n - 1]) == 1) {
            ans = Math.max(ans, nums[n - 1]);
        }

        return ans;
    }
}