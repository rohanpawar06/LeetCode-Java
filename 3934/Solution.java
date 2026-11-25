import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final long MOD1 = 1_000_000_007L;
    private static final long MOD2 = 1_000_000_009L;
    private static final long BASE1 = 911_382_323L;
    private static final long BASE2 = 972_663_749L;

    public int smallestUniqueSubarray(int[] nums) {
        int left = 1;
        int right = nums.length;
        int answer = nums.length;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (hasUniqueSubarray(nums, mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }

    private boolean hasUniqueSubarray(int[] nums, int len) {
        int n = nums.length;

        long power1 = 1;
        long power2 = 1;

        for (int i = 0; i < len; i++) {
            power1 = power1 * BASE1 % MOD1;
            power2 = power2 * BASE2 % MOD2;
        }

        long hash1 = 0;
        long hash2 = 0;

        for (int i = 0; i < len; i++) {
            hash1 = (hash1 * BASE1 + nums[i]) % MOD1;
            hash2 = (hash2 * BASE2 + nums[i]) % MOD2;
        }

        Map<Long, Integer> frequency = new HashMap<>(n * 2);
        addHash(frequency, combine(hash1, hash2));

        for (int right = len; right < n; right++) {
            int outgoing = nums[right - len];
            int incoming = nums[right];

            hash1 = (hash1 * BASE1 + incoming
                    - outgoing * power1 % MOD1 + MOD1) % MOD1;

            hash2 = (hash2 * BASE2 + incoming
                    - outgoing * power2 % MOD2 + MOD2) % MOD2;

            addHash(frequency, combine(hash1, hash2));
        }

        for (int count : frequency.values()) {
            if (count == 1) {
                return true;
            }
        }

        return false;
    }

    private void addHash(Map<Long, Integer> frequency, long key) {
        int count = frequency.getOrDefault(key, 0);
        if (count < 2) {
            frequency.put(key, count + 1);
        }
    }

    private long combine(long hash1, long hash2) {
        return (hash1 << 32) | hash2;
    }
}