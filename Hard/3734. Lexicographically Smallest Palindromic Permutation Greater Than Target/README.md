Yes buddy — for **3734. Lexicographically Smallest Palindromic Permutation Greater Than Target**, here is the README in **one single Markdown block**, so you can copy the entire thing at once. The official problem is Hard and has `n <= 300`. ([LeetCode][1])

```markdown
# 3734. Lexicographically Smallest Palindromic Permutation Greater Than Target

## 🔴 Difficulty

Hard

---

## 🔗 Problem Link

https://leetcode.com/problems/lexicographically-smallest-palindromic-permutation-greater-than-target/

---

## 🏷️ Tags

- String
- Two Pointers
- Greedy
- Backtracking
- Enumeration
- Palindrome
- Frequency Counting

---

## 📖 Problem Statement

You are given two strings `s` and `target`, both having the same length.

We need to rearrange the characters of `s` to create a string that satisfies both conditions:

1. The string must be a palindrome.
2. The string must be lexicographically greater than `target`.

Among all valid strings, return the **lexicographically smallest** one.

If no valid string exists, return:

    ""

---

## 💡 Intuition

The difficult part of this problem is that the answer must satisfy two conditions at the same time:

    Permutation of s
            +
    Palindrome
            +
    Greater than target

We cannot generate all permutations because there can be an enormous number of them.

The key observation is:

> A palindrome is completely determined by its left half and its middle character.

For example:

    "baab"

can be divided into:

    left half = "ba"
    right half = "ab"

The right half is simply the reverse of the left half.

Therefore, instead of constructing the entire palindrome, we mainly need to construct its left half.

---

## 🔑 Palindrome Observation

For a string to have a palindromic permutation:

- At most one character can have an odd frequency.
- Every other character must have an even frequency.

For example:

    s = "baba"

Frequencies:

    a → 2
    b → 2

Therefore, a palindrome is possible.

Possible palindromes include:

    abba
    baab

But:

    s = "abc"

has:

    a → 1
    b → 1
    c → 1

There are three odd frequencies.

Therefore, no palindrome can be created.

---

## 🚀 Approach

We use a frequency array of size `26`.

For every character:

    count[c] = frequency of c

Each character contributes:

    count[c] / 2

copies to the left half.

If the length is odd, the character with an odd frequency becomes the middle character.

---

## 🔍 Main Idea

We try to construct the answer from left to right.

At every position:

    1. Try to keep the current character equal to target.
    2. If possible, continue.
    3. If equality cannot eventually produce a valid answer,
       try a larger character.
    4. Choose the smallest larger character that can
       produce a valid palindrome.
    5. Once the answer becomes greater than target,
       fill the remaining positions with the smallest
       available characters.

This gives the lexicographically smallest possible answer.

---

## 🧠 Why Do We Build Only Half?

Suppose:

    left = "ba"

Then the palindrome is automatically:

    "ba" + reverse("ba")

which gives:

    "baab"

For an odd-length string:

    left + middle + reverse(left)

For example:

    left = "ac"
    middle = "b"

Then:

    "ac" + "b" + "ca"

gives:

    "acbca"

Therefore, once the left half and middle are known, the entire palindrome is fixed.

---

## 🔄 Greedy Construction

Suppose we have:

    target = "abba"

and:

    s = "baba"

The possible palindromes are:

    abba
    baab

We need a palindrome strictly greater than:

    abba

Try:

    abba

It is equal to target, so it is not valid.

Try:

    baab

Now:

    baab > abba

Therefore:

    baab

is the answer.

---

## 🔍 Algorithm

    1. Count the frequency of every character in s.

    2. Check whether a palindrome can be formed.

       If more than one character has an odd frequency:

           return ""

    3. Create the frequency of characters available
       for the left half.

    4. Try to construct the left half while comparing
       it with the corresponding characters of target.

    5. Keep matching target as long as possible.

    6. If we need to make the result greater:

       Choose the smallest available character
       greater than target[i].

    7. Once a larger character is chosen:

       Fill all remaining left-half positions
       with the smallest available characters.

    8. Construct the palindrome:

       left + middle + reverse(left)

    9. Check that the result is strictly greater than target.

    10. Return the result.

    11. If no valid construction exists:

        return ""

---

## 📌 Example 1

### Input

    s = "baba"
    target = "abba"

Frequency:

    a → 2
    b → 2

Possible palindromic permutations:

    abba
    baab

Compare with target:

    abba == abba

This is not strictly greater.

Next:

    baab > abba

Therefore:

    Output = "baab"

---

## 📌 Example 2

### Input

    s = "baba"
    target = "bbaa"

Possible palindromes:

    abba
    baab

Compare:

    abba < bbaa
    baab < bbaa

There is no palindrome greater than target.

Therefore:

    Output = ""

---

## 📌 Example 3

### Input

    s = "abc"
    target = "abb"

Frequency:

    a → 1
    b → 1
    c → 1

There are three characters with odd frequencies.

A palindrome cannot be formed.

Therefore:

    Output = ""

---

## 📌 Example 4

### Input

    s = "aac"
    target = "abb"

Frequency:

    a → 2
    c → 1

The palindrome must be:

    aca

Compare:

    aca
    abb

At the first character:

    a == a

At the second character:

    c > b

Therefore:

    aca > abb

So:

    Output = "aca"

---

## 💻 Java Solution

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

---

## 🧠 Key Learning

### Pattern: Greedy + Frequency Counting + Palindrome Construction

The important pattern is:

    Count characters
          ↓
    Check palindrome possibility
          ↓
    Build only the left half
          ↓
    Match target as long as possible
          ↓
    Make one position slightly larger
          ↓
    Fill remaining positions minimally
          ↓
    Mirror the left half
          ↓
    Get the final palindrome

The most important greedy idea is:

> Keep the prefix equal to `target` for as long as possible. When equality can no longer work, make the smallest possible increase at the latest possible position.

This is a common pattern for:

- Next permutation
- Lexicographically smallest greater string
- Palindromic permutation
- Frequency-based string construction
- Greedy string problems

---

## ⚠️ Important Edge Cases

### Case 1: More than one odd frequency

    s = "abc"

No palindrome is possible.

Return:

    ""

---

### Case 2: Only palindrome equals target

    s = "aaaa"
    target = "aaaa"

The only possible palindrome is equal to target.

It must be strictly greater.

Therefore:

    ""

---

### Case 3: Target is already larger than every possible palindrome

Example:

    s = "baba"
    target = "bbaa"

Possible:

    abba
    baab

Both are smaller.

Therefore:

    ""

---

### Case 4: Odd length

Example:

    s = "aac"

The middle character is:

    c

The palindrome is:

    aca

---

## ⏱️ Time Complexity

**O(26 × n)**

Since there are only 26 lowercase English characters, the alphabet factor is constant.

Therefore, the effective complexity is:

    O(n)

---

## 💾 Space Complexity

**O(n)**

We store the left half and construct the final palindrome.

The frequency arrays themselves use:

    O(26)

which is effectively:

    O(1)

---

## 💻 Language

Java
```

[1]: https://leetcode.com/problems/lexicographically-smallest-palindromic-permutation-greater-than-target/description/?utm_source=chatgpt.com "Lexicographically Smallest Palindromic Permutation Greater Than Target - LeetCode"
