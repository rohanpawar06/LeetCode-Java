import java.util.Arrays;

class Solution {
    public int[] validSequence(String word1, String word2) {
        int[] ans = new int[word2.length()];
        int[] last = new int[word2.length()];

        Arrays.fill(last, -1);

        int i = word1.length() - 1;
        int j = word2.length() - 1;

        while (i >= 0 && j >= 0) {
            if (word1.charAt(i) == word2.charAt(j)) {
                last[j] = i;
                j--;
            }
            i--;
        }

        boolean canSkip = true;
        j = 0;

        for (i = 0; i < word1.length(); i++) {
            if (j == word2.length()) {
                break;
            }

            if (word1.charAt(i) == word2.charAt(j)) {
                ans[j] = i;
                j++;
            } else if (canSkip &&
                       (j == word2.length() - 1 || i < last[j + 1])) {
                ans[j] = i;
                j++;
                canSkip = false;
            }
        }

        return j == word2.length() ? ans : new int[0];
    }
}