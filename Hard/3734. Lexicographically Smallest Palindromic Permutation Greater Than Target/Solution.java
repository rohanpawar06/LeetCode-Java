class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int half = n / 2;

        int[] cnt = new int[26];

        // Count characters in s
        for (char c : s.toCharArray()) {
            cnt[c - 'a']++;
        }

        // A palindrome can have at most one character
        // with an odd frequency.
        int odd = 0;
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            if (cnt[i] % 2 == 1) {
                odd++;
                middle = (char) ('a' + i);
            }
        }

        if (odd > 1) {
            return "";
        }

        /*
         * cnt represents the characters available in pairs.
         *
         * We try to make the left half equal to target's
         * left half first.
         */
        int negative = 0;

        for (int i = 0; i < half; i++) {
            int x = target.charAt(i) - 'a';

            cnt[x] -= 2;

            if (cnt[x] < 0) {
                negative++;
            }
        }

        /*
         * Find the largest available character.
         */
        int maxAvailable = -1;

        for (int i = 25; i >= 0; i--) {
            if (cnt[i] > 0) {
                maxAvailable = i;
                break;
            }
        }

        /*
         * First check whether the palindrome whose left half
         * is exactly target's left half is already > target.
         */
        if (negative == 0) {
            StringBuilder candidate = new StringBuilder();

            candidate.append(target.substring(0, half));

            if (n % 2 == 1) {
                candidate.append(middle);
            }

            for (int i = half - 1; i >= 0; i--) {
                candidate.append(target.charAt(i));
            }

            if (candidate.toString().compareTo(target) > 0) {
                return candidate.toString();
            }
        }

        /*
         * We need to change one position in the left half.
         *
         * Work backwards so that we change the latest possible
         * position. This gives the lexicographically smallest
         * answer.
         */
        for (int i = half - 1; i >= 0; i--) {

            int x = target.charAt(i) - 'a';

            /*
             * Restore the pair used by target[i].
             */
            if (cnt[x] < 0) {
                negative--;
            }

            cnt[x] += 2;

            /*
             * Find the smallest character greater than target[i]
             * that is available as a pair.
             */
            int bigger = -1;

            for (int c = x + 1; c < 26; c++) {
                if (cnt[c] >= 2) {
                    bigger = c;
                    break;
                }
            }

            /*
             * We can only use this position if all characters
             * before it can match target.
             */
            if (negative == 0 && bigger != -1) {

                // Use the bigger character as a pair.
                cnt[bigger] -= 2;

                StringBuilder left = new StringBuilder();

                // Keep target's prefix unchanged.
                left.append(target, 0, i);

                // Make this position slightly larger.
                left.append((char) ('a' + bigger));

                /*
                 * Fill remaining positions with the smallest
                 * available characters.
                 */
                for (int c = 0; c < 26; c++) {
                    while (cnt[c] >= 2) {
                        left.append((char) ('a' + c));
                        cnt[c] -= 2;
                    }
                }

                // Build complete palindrome.
                StringBuilder answer = new StringBuilder();

                answer.append(left);

                if (n % 2 == 1) {
                    answer.append(middle);
                }

                for (int j = left.length() - 1; j >= 0; j--) {
                    answer.append(left.charAt(j));
                }

                return answer.toString();
            }
        }

        return "";
    }
}