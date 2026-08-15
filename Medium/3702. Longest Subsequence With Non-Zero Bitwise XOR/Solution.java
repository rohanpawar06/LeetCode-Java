class Solution {
    public int longestSubsequence(int[] nums) {
        int xor=0;
        int zerocount=0;

        for(int num : nums) {
            xor ^= num;
            if(num == 0) {
                zerocount++;
            }
        }
        if(xor != 0) {
            return nums.length;
        }
        if(zerocount == nums.length) {
            return 0;
        }
        return nums.length - 1;
    }
}