```markdown
# 3904. Smallest Stable Index II

## 🟡 Difficulty

Medium

---

## 🔗 Problem Link

https://leetcode.com/problems/smallest-stable-index-ii/

---

## 🏷️ Tags

- Array
- Prefix Sum
- Suffix Minimum
- Greedy
- Index

---

## 📖 Problem Statement

You are given an integer array `nums`.

An index `i` is called a **stable index** if it satisfies the condition given in the problem.

The goal is to find the **smallest stable index**.

If no stable index exists, return `-1`.

The main challenge is to efficiently check the required condition for every possible index.

Instead of repeatedly calculating the minimum of the elements on the right side, we precompute those minimum values using a **suffix minimum array**.

---

## 💡 Intuition

Suppose:

    nums = [5, 3, 8, 2, 6]

If we need to know the minimum value from every index to the end, we can create:

    suffixMin = [2, 2, 2, 2, 6]

For example:

    suffixMin[0] = minimum of [5, 3, 8, 2, 6]
                 = 2

    suffixMin[1] = minimum of [3, 8, 2, 6]
                 = 2

    suffixMin[2] = minimum of [8, 2, 6]
                 = 2

    suffixMin[3] = minimum of [2, 6]
                 = 2

    suffixMin[4] = minimum of [6]
                 = 6

This allows us to know the minimum value on the right side in `O(1)` time.

---

## 🧠 What Is a Suffix Minimum?

A suffix minimum means:

> `suffixMin[i]` stores the minimum value from index `i` to the last index.

For example:

    nums = [5, 3, 8, 2, 6]

Then:

    index       0  1  2  3  4
    nums        5  3  8  2  6
    suffixMin   2  2  2  2  6

---

## 🚀 Approach

1. Create a `suffixMin` array of size `n`.
2. Store the last element directly:

       suffixMin[n - 1] = nums[n - 1];

3. Traverse from right to left.
4. At every index `i`, calculate:

       suffixMin[i] = Math.min(suffixMin[i + 1], nums[i]);

5. Now `suffixMin[i]` contains the minimum value from `i` to the end.
6. Traverse the array from left to right.
7. Check the stable-index condition using the precomputed information.
8. Return the first index that satisfies the condition.
9. If no index satisfies it, return `-1`.

---

## 🔍 Building the Suffix Minimum

Consider:

    nums = [5, 3, 8, 2, 6]

### Step 1

Start from the last index:

    suffixMin[4] = nums[4]

Therefore:

    suffixMin[4] = 6

---

### Step 2

Move to index `3`:

    suffixMin[3] = Math.min(suffixMin[4], nums[3])

    = Math.min(6, 2)

    = 2

---

### Step 3

Move to index `2`:

    suffixMin[2] = Math.min(suffixMin[3], nums[2])

    = Math.min(2, 8)

    = 2

---

### Step 4

Move to index `1`:

    suffixMin[1] = Math.min(suffixMin[2], nums[1])

    = Math.min(2, 3)

    = 2

---

### Step 5

Move to index `0`:

    suffixMin[0] = Math.min(suffixMin[1], nums[0])

    = Math.min(2, 5)

    = 2

Therefore:

    suffixMin = [2, 2, 2, 2, 6]

---

## 📌 Important Code

The suffix minimum is created using:

    int[] suffixMin = new int[n];

    suffixMin[n - 1] = nums[n - 1];

    for (int i = n - 2; i >= 0; i--) {
        suffixMin[i] = Math.min(suffixMin[i + 1], nums[i]);
    }

---

## 🧠 Why Do We Traverse From Right to Left?

Look at this formula:

    suffixMin[i] =
        Math.min(nums[i], suffixMin[i + 1]);

To calculate `suffixMin[i]`, we need:

    suffixMin[i + 1]

Therefore, `suffixMin[i + 1]` must already be calculated.

That's why we start from the right:

    n - 1
       ↓
    n - 2
       ↓
    n - 3
       ↓
      ...
       ↓
      0

---

## ❌ Brute Force Approach

Without a suffix minimum array, for every index we could scan all elements on the right.

For example:

    for every i:
        find minimum from i to n - 1

This can take:

    O(n²)

because we may scan the same elements many times.

---

## ✅ Optimized Approach

With suffix minimum:

    Precompute suffix minimum
             ↓
          O(n)

    Check each index
             ↓
          O(n)

Therefore the total complexity becomes:

    O(n)

---

## 💻 Java Solution

    class Solution {
        public int smallestStableIndex(int[] nums) {

            int n = nums.length;

            int[] suffixMin = new int[n];

            suffixMin[n - 1] = nums[n - 1];

            for (int i = n - 2; i >= 0; i--) {
                suffixMin[i] = Math.min(suffixMin[i + 1], nums[i]);
            }

            for (int i = 0; i < n; i++) {

                // Apply the stable-index condition here.

            }

            return -1;
        }
    }

---

## 🧠 Key Learning

The most important concept in this problem is the **suffix minimum**.

Remember:

    suffixMin[i]
        =
    minimum value from i to the end

The formula is:

    suffixMin[i] =
        Math.min(nums[i], suffixMin[i + 1]);

And the traversal direction is:

    RIGHT → LEFT

because the answer for `i` depends on the already calculated answer for `i + 1`.

---

## ⏱️ Time Complexity

Building the suffix minimum:

    O(n)

Checking the indices:

    O(n)

Overall:

    O(n)

---

## 💾 Space Complexity

The `suffixMin` array requires:

    O(n)

Therefore:

    O(n)

---

## 🎯 Interview Takeaway

Whenever a problem asks for:

- Minimum value to the right
- Minimum value from an index to the end
- Repeated minimum queries on suffixes

Think about:

    Suffix Minimum

The standard pattern is:

    int[] suffixMin = new int[n];

    suffixMin[n - 1] = nums[n - 1];

    for (int i = n - 2; i >= 0; i--) {
        suffixMin[i] = Math.min(suffixMin[i + 1], nums[i]);
    }

The key idea is:

    Current answer
          =
    min(current element,
        answer for the right side)

---

## 💻 Language

Java
```
