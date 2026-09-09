class Solution {
    public long countCommas(long n) {
        long answer = 0;

        for (long start = 1000; start <= n; start *= 1000) {
            answer += n - start + 1;
        }

        return answer;

    }
}