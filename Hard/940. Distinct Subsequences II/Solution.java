class Solution {
    public int distinctSubseqII(String s) {

        final int MOD = 1000000007;

        long[] dp = new long[26];

        for (char ch : s.toCharArray()) {

            int index = ch - 'a';

            long total = 0;

            for (int i = 0; i < 26; i++) {
                total = (total + dp[i]) % MOD;
            }

            dp[index] = (total + 1) % MOD;
        }

        long answer = 0;

        for (int i = 0; i < 26; i++) {
            answer = (answer + dp[i]) % MOD;
        }

        return (int) answer;
    }
}