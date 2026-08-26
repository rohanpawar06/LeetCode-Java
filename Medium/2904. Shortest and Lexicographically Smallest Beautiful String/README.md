# 2904. Shortest and Lexicographically Smallest Beautiful String

## 🟡 Difficulty

Medium

---

## 🔗 Problem Link

https://leetcode.com/problems/shortest-and-lexicographically-smallest-beautiful-string/

---

## 🏷️ Tags

- String
- Sliding Window
- Two Pointers
- String Manipulation
- Lexicographical Order

---

## 📖 Problem Statement

You are given a binary string `s` and an integer `k`.

A substring is called **beautiful** if it contains exactly `k` occurrences of the character `'1'`.

Find the **shortest beautiful substring** of `s`.

If there are multiple beautiful substrings having the same shortest length, return the **lexicographically smallest** one.

If no beautiful substring exists, return an empty string.

### Example

    s = "100011001"
    k = 2

A substring containing exactly two `1`s can be:

    "1100"

The substring must contain exactly `k` ones.

Among all valid substrings:

1. Choose the shortest one.
2. If multiple substrings have the same length, choose the lexicographically smallest one.

---

## 💡 Intuition

The main condition is:

> We need a substring containing exactly `k` ones.

This naturally suggests a **Sliding Window**.

We maintain two pointers:

    left
    right

and keep track of how many `1`s are currently inside the window.

For example:

    s = "100011001"
             ↑
           window

Whenever we add a character at `right`:

    if s[right] == '1'
        count++

When:

    count > k

we move `left` forward until the window contains at most `k` ones.

When:

    count == k

we have a valid beautiful substring.

---

## 🚀 Approach

1. Use two pointers `left` and `right`.
2. Maintain `count` = number of `1`s in the current window.
3. Expand the window using `right`.
4. Whenever a `1` is found, increase `count`.
5. If `count > k`, move `left` forward until `count == k`.
6. When `count == k`, remove unnecessary leading zeroes from the window.
7. The resulting substring is a candidate beautiful substring.
8. Compare it with the current answer:
   - First compare lengths.
   - If lengths are equal, compare lexicographically.
9. Keep the best candidate.
10. Return the answer.

---

## 🔍 Algorithm

    1. Initialize:

           left = 0
           count = 0
           answer = ""

    2. Traverse the string using right.

    3. If s[right] == '1':

           count++

    4. While count > k:

           If s[left] == '1':
               count--

           left++

    5. If count == k:

           Move left forward while:

               s[left] == '0'

           This removes unnecessary leading zeroes.

    6. Create the current substring:

           s.substring(left, right + 1)

    7. Compare the current substring with answer.

           If answer is empty:
               store current

           Else if current.length() < answer.length():
               store current

           Else if lengths are equal and
           current is lexicographically smaller:
               store current

    8. Continue until all characters are processed.

    9. Return answer.

---

## 📌 Example

### Input

    s = "100011001"
    k = 2

We need exactly:

    2 ones

Consider the substring:

    "1100"

It contains:

    1 → one
    1 → two

Therefore it is beautiful.

The algorithm checks all possible windows containing exactly two `1`s.

For every valid window:

    1. Find its length.
    2. Compare it with the current shortest answer.
    3. If lengths are equal, compare lexicographically.

Finally, the shortest valid substring is returned.

---

## 📌 Why Remove Leading Zeroes?

Suppose the current window is:

    "00110"

It contains exactly two `1`s.

But:

    "110"

also contains exactly two `1`s.

And:

    "110"

is shorter.

Therefore, when we have exactly `k` ones, we can safely remove leading zeroes:

    "00110"
       ↓
     "110"

This helps us obtain the shortest possible substring for that right boundary.

---

## 📌 Lexicographical Comparison

Suppose two valid substrings have the same length:

    "101"
    "110"

Both have length `3`.

Compare from left to right:

    1 = 1

Then:

    0 < 1

Therefore:

    "101" < "110"

So:

    "101"

is lexicographically smaller.

In Java, we can use:

    current.compareTo(answer) < 0

to check whether `current` comes before `answer` lexicographically.

---

## 💻 Java Solution

    class Solution {
        public String shortestBeautifulSubstring(String s, int k) {
            int left = 0;
            int count = 0;
            String answer = "";

            for (int right = 0; right < s.length(); right++) {

                if (s.charAt(right) == '1') {
                    count++;
                }

                while (count > k) {
                    if (s.charAt(left) == '1') {
                        count--;
                    }
                    left++;
                }

                if (count == k) {

                    while (s.charAt(left) == '0') {
                        left++;
                    }

                    String current = s.substring(left, right + 1);

                    if (answer.equals("")
                            || current.length() < answer.length()
                            || (current.length() == answer.length()
                            && current.compareTo(answer) < 0)) {

                        answer = current;
                    }
                }
            }

            return answer;
        }
    }

---

## 🧠 Key Learning

### Pattern: Sliding Window + Two Pointers

The main pattern is:

    Expand Window
          ↓
    Count number of 1s
          ↓
    count > k
          ↓
    Move left pointer
          ↓
    count == k
          ↓
    Remove unnecessary leading zeroes
          ↓
    Compare candidate
          ↓
    Keep shortest / lexicographically smallest

The most important idea is:

    Exactly K occurrences
            ↓
    Sliding Window

This pattern can be used for many problems involving:

- Longest substring with at most K characters
- Shortest substring with exactly K occurrences
- Frequency constraints
- Binary strings
- Subarray/window conditions

---

## ⏱️ Time Complexity

**O(n²)** in the worst case.

The string is traversed using two pointers, but creating and comparing substrings can take `O(n)` time.

Therefore, in the worst case:

    O(n²)

---

## 💾 Space Complexity

**O(n)**

The answer and temporary substring can require `O(n)` space.

---

## 💻 Language

Java