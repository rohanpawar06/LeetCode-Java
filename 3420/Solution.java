import java.util.*;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> deque = new ArrayDeque<>();
        long operations = 0;
        long answer = 0;
        int right = n - 1;

        for (int left = n - 1; left >= 0; left--) {
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[left]) {
                int index = deque.pollLast();
                int boundary = deque.isEmpty() ? right + 1 : deque.peekLast();
                operations += (long) (boundary - index) * (nums[left] - nums[index]);
            }

            deque.offerLast(left);

            while (operations > k) {
                operations -= nums[deque.peekFirst()] - nums[right];
                if (deque.peekFirst() == right) {
                    deque.pollFirst();
                }
                right--;
            }

            answer += right - left + 1;
        }

        return answer;
    }
}
