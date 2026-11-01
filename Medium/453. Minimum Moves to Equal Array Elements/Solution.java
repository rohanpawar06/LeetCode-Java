class Solution {
    public int minMoves(int[] nums) {
        int min = nums[0];

        for (int num : nums) {
            min = Math.min(min, num);
        }

        long moves = 0;
        for (int num : nums) {
            moves += (long) num - min;
        }

        return (int) moves;
    }
}
