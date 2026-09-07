Yes — you mean **exactly one outer Markdown code block**, and **inside it everything must be plain Markdown**, including the Java code. There must be **no separate nested triple-backtick code blocks**, because those break the single-copy requirement.

Here is the corrected README format:

~~~markdown
# 940. Distinct Subsequences II

## 🔴 Difficulty

Hard

---

## 🔗 Problem Link

https://leetcode.com/problems/distinct-subsequences-ii/

---

## 🏷️ Tags

- String
- Dynamic Programming
- Counting
- Subsequences

---

## 📖 Problem Statement

Given a string `s`, return the number of distinct non-empty subsequences of `s`.

Since the answer can be very large, return the answer modulo:

`10^9 + 7`

A subsequence is a sequence that can be obtained by deleting some or none of the characters from the string without changing the order of the remaining characters.

### Example

Input:

`s = "abc"`

The distinct non-empty subsequences are:

`a, b, c, ab, ac, bc, abc`

Therefore:

`Output = 7`

---

## 💡 Intuition

The main problem is that different selections of characters can produce the same subsequence.

For example:

`s = "aaa"`

If we consider every possible selection, there are:

`2^3 = 8`

possible selections.

But the distinct subsequences are only:

`a`

`aa`

`aaa`

So the answer is:

`3`

We need to count subsequences while avoiding duplicates.

The key idea is to keep track of how many distinct subsequences end with each character.

Since the string contains lowercase English letters, we can use an array of size `26`.

For example:

`dp[0]` → number of distinct subsequences ending with `a`

`dp[1]` → number of distinct subsequences ending with `b`

`dp[2]` → number of distinct subsequences ending with `c`

and so on.

---

## 🧠 Main Idea

Suppose we are processing a character `c`.

Let:

`total = sum of all dp values`

Every existing distinct subsequence can have the current character appended to it.

Therefore, we can create:

`total`

new subsequences.

The current character itself is also a new subsequence.

So:

`new subsequences = total + 1`

Therefore:

`dp[c] = total + 1`

Notice that we use `=` instead of `+=`.

This is extremely important.

If the same character appeared before, some subsequences ending with this character already existed.

Replacing `dp[c]` removes the duplicate count.

---

## 🚀 Approach

1. Create a `long[] dp` array of size `26`.
2. Traverse the string from left to right.
3. Convert the current character into an array index.
4. Calculate the total number of distinct subsequences currently stored in `dp`.
5. Set the current character's value to `total + 1`.
6. Continue until the complete string is processed.
7. Add all values in `dp`.
8. Return the result modulo `10^9 + 7`.

---

## 🔍 Algorithm

1. Initialize:

`dp = new long[26]`

Initially every value is `0`.

2. For every character `ch` in `s`:

`index = ch - 'a'`

3. Calculate:

`total = dp[0] + dp[1] + ... + dp[25]`

4. Update:

`dp[index] = total + 1`

5. After processing the entire string:

`answer = dp[0] + dp[1] + ... + dp[25]`

6. Return `answer % MOD`.

---

# 📌 Example 1

## Input

`s = "abc"`

---

## Step 1: Process `a`

Initially:

`dp = [0, 0, 0, ..., 0]`

Total:

`0`

For `a`:

`dp[a] = 0 + 1`

Therefore:

`dp[a] = 1`

The subsequence is:

`a`

---

## Step 2: Process `b`

Current values:

`a = 1`

`b = 0`

Total:

`1`

Now:

`dp[b] = 1 + 1`

Therefore:

`dp[b] = 2`

The subsequences are:

`a`

`b`

`ab`

Total:

`3`

---

## Step 3: Process `c`

Current values:

`a = 1`

`b = 2`

`c = 0`

Total:

`1 + 2 = 3`

Now:

`dp[c] = 3 + 1`

Therefore:

`dp[c] = 4`

The subsequences are:

`a`

`b`

`c`

`ab`

`ac`

`bc`

`abc`

Final answer:

`1 + 2 + 4 = 7`

Therefore:

`Output = 7`

---

# 📌 Example 2

## Input

`s = "aba"`

---

## Step 1: Process `a`

Total:

`0`

Update:

`dp[a] = 0 + 1 = 1`

Subsequence:

`a`

---

## Step 2: Process `b`

Total:

`1`

Update:

`dp[b] = 1 + 1 = 2`

Current distinct subsequences:

`a`

`b`

`ab`

---

## Step 3: Process second `a`

Current values:

`dp[a] = 1`

`dp[b] = 2`

Total:

`3`

Now:

`dp[a] = 3 + 1`

Therefore:

`dp[a] = 4`

The final distinct subsequences are:

`a`

`b`

`ab`

`aa`

`ba`

`aba`

Therefore:

`Output = 6`

---

# 📌 Example 3

## Input

`s = "aaa"`

---

## Step 1

Process first `a`.

`dp[a] = 1`

Distinct subsequences:

`a`

---

## Step 2

Process second `a`.

Current total:

`1`

Update:

`dp[a] = 1 + 1 = 2`

Distinct subsequences:

`a`

`aa`

---

## Step 3

Process third `a`.

Current total:

`2`

Update:

`dp[a] = 2 + 1 = 3`

Distinct subsequences:

`a`

`aa`

`aaa`

Therefore:

`Output = 3`

---

# 🧠 Why Does Replacing Prevent Duplicates?

Consider:

`s = "aba"`

After processing `ab`, we have:

`a`

`b`

`ab`

Now another `a` arrives.

We can append `a` to existing subsequences:

`a + a = aa`

`b + a = ba`

`ab + a = aba`

And the current `a` itself gives:

`a`

But `a` already existed.

So the new subsequences ending with `a` are:

`a`

`aa`

`ba`

`aba`

There are `4`.

Therefore:

`dp[a] = 4`

We replace the old value `1` with `4`.

If we added instead:

`dp[a] += 4`

we would count the old `a` again and produce an incorrect answer.

---

# 💻 Java Solution

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

---

# 🔍 Code Explanation

## 1. Create the DP Array

`long[] dp = new long[26];`

We use 26 positions because there are 26 lowercase English letters.

For example:

`dp[0]` represents `a`

`dp[1]` represents `b`

`dp[2]` represents `c`

---

## 2. Traverse the String

`for (char ch : s.toCharArray())`

We process every character one by one from left to right.

---

## 3. Convert Character to Index

`int index = ch - 'a';`

For example:

`'a' - 'a' = 0`

`'b' - 'a' = 1`

`'c' - 'a' = 2`

This allows us to use the character as an index in the `dp` array.

---

## 4. Calculate Total

`long total = 0;`

Then:

`for (int i = 0; i < 26; i++)`

we add all currently stored subsequences.

This gives the number of distinct subsequences before adding the current character.

---

## 5. Update Current Character

`dp[index] = (total + 1) % MOD;`

The `+1` represents the current character by itself.

For example, when processing `c`, the subsequence:

`c`

is always possible.

---

## 6. Calculate Final Answer

After processing every character, every subsequence ends with some character.

Therefore, we add all 26 values:

`answer = dp[0] + dp[1] + ... + dp[25]`

and return the result modulo `10^9 + 7`.

---

# ⚠️ Common Mistakes

## Mistake 1: Using `+=`

Incorrect:

`dp[index] += total + 1`

Correct:

`dp[index] = total + 1`

Using `+=` counts duplicate subsequences.

---

## Mistake 2: Forgetting the Current Character

Incorrect:

`dp[index] = total`

Correct:

`dp[index] = total + 1`

The `+1` represents the current character itself.

---

## Mistake 3: Using `int` Without Modulo

The number of subsequences can become extremely large.

Therefore, use:

`long`

and apply:

`% MOD`

during calculations.

---

# 🧠 Key Learning

The main pattern of this problem is:

`String`

↓

`Track subsequences ending with each character`

↓

`Calculate total`

↓

`dp[current] = total + 1`

↓

`Replace old value to remove duplicates`

↓

`Sum all dp values`

---

# 🎯 Quick Revision

Remember these three things:

### 1. DP Array

`dp[26]`

Each position represents a character.

### 2. Update

`dp[current] = total + 1`

### 3. Final Answer

`answer = sum of all dp values`

---

# 🆚 LeetCode 115 vs LeetCode 940

## LeetCode 115 - Distinct Subsequences

The question is:

How many different ways can string `s` form target string `t`?

Example:

`s = "rabbbit"`

`t = "rabbit"`

We count the number of ways to form the target.

---

## LeetCode 940 - Distinct Subsequences II

The question is:

How many different subsequence strings can be created from `s`?

Example:

`s = "aba"`

The answer is:

`6`

So remember:

`115 → Count ways to form a target`

`940 → Count distinct subsequence strings`

---

# ⏱️ Time Complexity

For every character, we scan 26 positions.

Therefore:

`O(26 × n)`

Since `26` is constant:

`O(n)`

---

# 💾 Space Complexity

We only use a 26-element array.

Therefore:

`O(26)`

which is:

`O(1)`

---

# 🎯 Interview Takeaway

When you see a problem asking:

"Count distinct subsequences"

think:

`26 Character DP`

↓

`Track subsequences ending with each character`

↓

`total = sum(dp)`

↓

`dp[current] = total + 1`

↓

`Sum all dp values`

The most important line is:

`dp[index] = (total + 1) % MOD;`

because replacing the previous value prevents duplicate subsequences.

---

## 💻 Language

Java
~~~