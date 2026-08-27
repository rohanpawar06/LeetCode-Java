class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        char[] ans = new char[n];

        // First try to match target as much as possible.
        for (int i = 0; i < n; i++) {
            int current = target.charAt(i) - 'a';

            if (freq[current] > 0) {
                ans[i] = target.charAt(i);
                freq[current]--;
            } else {
                // Cannot match target[i].
                // Try putting a larger character here.
                for (int bigger = current + 1; bigger < 26; bigger++) {
                    if (freq[bigger] > 0) {
                        ans[i] = (char) ('a' + bigger);
                        freq[bigger]--;

                        fillSmallest(ans, i + 1, freq);
                        return new String(ans);
                    }
                }

                // Otherwise backtrack.
                return backtrack(ans, i - 1, freq, target);
            }
        }

        // Exact target was formed, but answer must be strictly greater.
        return backtrack(ans, n - 1, freq, target);
    }

    private String backtrack(char[] ans, int pos, int[] freq, String target) {
        while (pos >= 0) {
            int old = ans[pos] - 'a';
            freq[old]++;

            int current = target.charAt(pos) - 'a';

            for (int bigger = current + 1; bigger < 26; bigger++) {
                if (freq[bigger] > 0) {
                    ans[pos] = (char) ('a' + bigger);
                    freq[bigger]--;

                    fillSmallest(ans, pos + 1, freq);
                    return new String(ans);
                }
            }

            pos--;
        }

        return "";
    }

    private void fillSmallest(char[] ans, int start, int[] freq) {
        int index = start;

        for (int ch = 0; ch < 26; ch++) {
            while (freq[ch] > 0) {
                ans[index++] = (char) ('a' + ch);
                freq[ch]--;
            }
        }
    }
}