class Solution {
    public int numOfSubarrays(int[] arr) {
        int mod = 1_000_000_007;
        long odd = 0;
        long even = 1;
        long prefix = 0;
        long result = 0;

        for (int num : arr) {
            prefix += num;

            if (prefix % 2 == 0) {
                result += odd;
                even++;
            } else {
                result += even;
                odd++;
            }

            result %= mod;
        }

        return (int) result;
    }
}
