# 3720. Lexicographically Smallest Permutation Greater Than Target

## 🟡 Difficulty

Medium

---

## 🔗 Problem Link

https://leetcode.com/problems/lexicographically-smallest-permutation-greater-than-target/

---

## 🏷️ Tags

- String
- Greedy
- HashMap
- Counting
- Enumeration
- Lexicographical Order

---

## 📖 Problem Statement

You are given two strings `s` and `target`, both having the same length.

You need to rearrange the characters of `s` to create a permutation that is:

1. Strictly greater than `target` in lexicographical order.
2. The lexicographically smallest possible among all valid permutations.

If no permutation of `s` is strictly greater than `target`, return an empty string.

A string `a` is lexicographically greater than string `b` if, at the first position where they differ, `a` contains a character that comes later in the alphabet than `b`.

---

## 💡 Intuition

We cannot generate every permutation because there can be a huge number of permutations.

Instead, use a frequency array to store how many times each character occurs in `s`.

The main idea is:

    Try to keep the prefix equal to target
            ↓
    At some position, make the character slightly larger
            ↓
    Fill the remaining positions with the smallest possible characters

For example:

    s = "abc"
    target = "bba"

We want to stay equal to `target` for as long as possible.

At index `0`:

    target[0] = 'b'

We can use `b`.

Now:

    prefix = "b"

At index `1`:

    target[1] = 'b'

The remaining character is `a` or `c`.

We cannot use `b`, so we need to make the current character greater than `b`.

The smallest available character greater than `b` is:

    c

Therefore:

    "bc"

The remaining character is:

    a

So the answer becomes:

    "bca"

---

## 🚀 Approach

1. Count the frequency of every character in `s`.
2. Try to construct the answer while matching `target`.
3. At each position:
   - Try to use the same character as `target[i]`.
   - If possible, continue.
4. If matching `target[i]` is impossible, we need to make the answer greater.
5. Try to find the smallest available character strictly greater than `target[i]`.
6. If such a character exists:
   - Keep the prefix unchanged.
   - Put that larger character at the current position.
   - Fill the remaining positions using the smallest available characters.
   - Return the result.
7. If no larger character is available at the current position, backtrack to an earlier position.
8. At an earlier position, replace the matched character with the smallest available character greater than the corresponding target character.
9. Fill the remaining positions with the smallest available characters.
10. If no position can be increased, return `""`.

---

## 🔍 Algorithm

    1. Create a frequency array:

           int[] count = new int[26];

    2. Count all characters of s.

    3. Start from the last position of target and work backwards.

    4. At position i:

           Restore target[i] into the available character count.

    5. Check whether there is a character greater than target[i].

    6. If a larger character exists:

           Choose the smallest character greater than target[i].

           Put it at position i.

           Fill the remaining positions with
           the smallest available characters.

           Return the constructed string.

    7. If no larger character exists:

           Continue moving backwards.

    8. If no position can be increased:

           return ""

---

## 📌 Example 1

### Input

    s = "abc"
    target = "bba"

The permutations of `s` are:

    abc
    acb
    bac
    bca
    cab
    cba

We need a permutation greater than:

    bba

Check:

    abc < bba
    acb < bba
    bac < bba
    bca > bba

Therefore:

    bca

is the smallest valid permutation.

### Output

    "bca"

---

## 📌 Example 2

### Input

    s = "leet"
    target = "code"

A valid permutation is:

    eelt

Compare:

    eelt
    code

At the first character:

    e > c

Therefore:

    eelt > code

And it is the smallest valid permutation.

### Output

    "eelt"

---

## 📌 Example 3

### Input

    s = "baba"
    target = "bbaa"

The permutations include:

    aabb
    abab
    abba
    baab
    baba
    bbaa

The largest permutation is:

    bbaa

which is equal to `target`.

There is no permutation strictly greater than `bbaa`.

Therefore:

    ""

is returned.

---

## 🧠 Key Learning

### Pattern: Greedy + Frequency Counting + Backtracking

The main idea is:

    Match target as long as possible
            ↓
    If possible, continue
            ↓
    If we need to become greater,
    choose the smallest character > target[i]
            ↓
    Fill the rest in sorted order

The most important observation is:

> To get the lexicographically smallest string greater than `target`, we should keep the prefix equal to `target` for as long as possible.

When we finally need to become greater:

    Choose the smallest possible character
    that is greater than target[i].

Then:

    Fill all remaining positions
    with the smallest available characters.

This is a common **greedy lexicographical construction** pattern.

---

## 💻 Java Solution

    class Solution {
        public String lexGreaterPermutation(String s, String target) {
            int[] count = new int[26];

            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }

            int n = target.length();

            for (int i = n - 1; i >= 0; i--) {

                count[target.charAt(i) - 'a']++;

                int targetChar = target.charAt(i) - 'a';

                for (int j = targetChar + 1; j < 26; j++) {

                    if (count[j] > 0) {

                        count[j]--;

                        StringBuilder ans = new StringBuilder();

                        ans.append(target, 0, i);
                        ans.append((char) ('a' + j));

                        for (int k = 0; k < 26; k++) {
                            while (count[k] > 0) {
                                ans.append((char) ('a' + k));
                                count[k]--;
                            }
                        }

                        return ans.toString();
                    }
                }
            }

            return "";
        }
    }

---

## ⏱️ Time Complexity

**O(26 × n)**

At each position, we may check up to 26 possible characters.

Since the alphabet contains only 26 lowercase English letters, this is effectively:

    O(n)

---

## 💾 Space Complexity

**O(26)**

The frequency array contains only 26 entries.

Therefore, auxiliary space is:

    O(1)

---

## 💻 Language

Java