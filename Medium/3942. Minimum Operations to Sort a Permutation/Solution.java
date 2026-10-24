class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int zero = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zero = i;
                break;
            }
        }

        int answer = Integer.MAX_VALUE;

        if (isIncreasingFromZero(nums, zero, 1)) {
            answer = Math.min(answer, zero);
            answer = Math.min(answer, n - zero + 2);
        }

        if (isIncreasingFromZero(nums, zero, -1)) {
            answer = Math.min(answer, zero + 2);
            answer = Math.min(answer, n - zero);
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    private boolean isIncreasingFromZero(int[] nums, int zero, int step) {
        int n = nums.length;

        for (int i = 1; i < n; i++) {
            int previous = (zero + (i - 1) * step + n) % n;
            int current = (zero + i * step + n) % n;

            if (nums[previous] > nums[current]) {
                return false;
            }
        }

        return true;
    }
}