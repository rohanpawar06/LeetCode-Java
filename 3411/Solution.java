class Solution {
    public int maxLength(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int i = 0; i < n; i++) {
            long product = 1;
            int gcd = 0;
            int lcm = 1;

            for (int j = i; j < n; j++) {
                int num = nums[j];

                product *= num;
                gcd = gcd(gcd, num);
                lcm = lcm / gcd(lcm, num) * num;

                if (product == (long) gcd * lcm) {
                    ans = Math.max(ans, j - i + 1);
                }

                if (product > 25200) {
                    break;
                }
            }
        }

        return ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}