````markdown
# 3903. Smallest Stable Index I

## 🟡 Difficulty

Medium

---

## 🔗 Problem Link

https://leetcode.com/problems/smallest-stable-index-i/

---

## 🏷️ Tags

- Array
- Prefix Maximum
- Suffix Minimum
- Greedy

---

## 📖 Problem Statement

You are given an integer array `nums` and an integer `k`.

An index `i` is called a **stable index** if:

```text
max(nums[0], nums[1], ..., nums[i])
-
min(nums[i], nums[i + 1], ..., nums[n - 1])
<= k
```

Return the **smallest stable index**.

If there is no stable index, return:

```text
-1
```

---

## 💡 Intuition

For every index `i`, we need two values:

```text
1. Maximum value from index 0 to i
2. Minimum value from index i to n - 1
```

Then we calculate:

```text
maximum on the left - minimum on the right
```

If the result is at most `k`, then `i` is stable.

The important observation is that:

```text
maximum from 0 to i
```

can be maintained while traversing from left to right.

But:

```text
minimum from i to n - 1
```

looks toward the right.

So we calculate all the right-side minimums beforehand using a **suffix minimum array**.

---

## 🧠 What Is a Suffix Minimum?

A suffix minimum means:

```text
suffixMin[i] = minimum value from i to the end
```

For example:

```text
nums = [5, 0, 1, 4]
```

The suffix minimum array is:

```text
index:      0  1  2  3
nums:       5  0  1  4
suffixMin:  0  0  1  4
```

Why?

```text
suffixMin[3] = min([4]) = 4

suffixMin[2] = min([1,4]) = 1

suffixMin[1] = min([0,1,4]) = 0

suffixMin[0] = min([5,0,1,4]) = 0
```

Therefore:

```text
suffixMin = [0, 0, 1, 4]
```

---

## 🚀 Approach

We use two steps.

### Step 1: Build Suffix Minimum Array

Traverse the array from right to left.

```text
suffixMin[i] = min(nums[i], suffixMin[i + 1])
```

This gives us the minimum value from `i` to the end.

---

### Step 2: Find the First Stable Index

Traverse from left to right.

Maintain:

```text
maxi = maximum value seen so far
```

At every index:

```text
maxi = max(maxi, nums[i])
```

Then calculate:

```text
cal = maxi - suffixMin[i]
```

If:

```text
cal <= k
```

then index `i` is stable.

Since we are checking indices from left to right, the first valid index is automatically the smallest stable index.

---

## 🔍 Algorithm

```text
1. Let n = nums.length.

2. Create an array:
       suffixMin[n]

3. Set:
       suffixMin[n - 1] = nums[n - 1]

4. Traverse from right to left.

5. For every index i:
       suffixMin[i] = min(nums[i], suffixMin[i + 1])

6. Set:
       maxi = Integer.MIN_VALUE

7. Traverse nums from left to right.

8. Update:
       maxi = max(maxi, nums[i])

9. Calculate:
       cal = maxi - suffixMin[i]

10. If:
        cal <= k

        return i

11. If no index satisfies the condition:
        return -1
```

---

## 📌 Example

### Input

```text
nums = [5, 0, 1, 4]
k = 3
```

Indexes:

```text
        0  1  2  3
nums = [5, 0, 1, 4]
```

---

## Step 1: Build Suffix Minimum

Starting from the right:

```text
suffixMin[3] = 4
```

Then:

```text
suffixMin[2] = min(1, 4)
             = 1
```

Then:

```text
suffixMin[1] = min(0, 1)
             = 0
```

Then:

```text
suffixMin[0] = min(5, 0)
             = 0
```

Therefore:

```text
suffixMin = [0, 0, 1, 4]
```

---

## Step 2: Check Each Index

### Index 0

Current maximum:

```text
maxi = 5
```

Minimum from index `0` to the end:

```text
suffixMin[0] = 0
```

Calculate:

```text
5 - 0 = 5
```

Check:

```text
5 <= 3
```

False.

So index `0` is not stable.

---

### Index 1

Current maximum:

```text
maxi = max(5, 0)
     = 5
```

Suffix minimum:

```text
suffixMin[1] = 0
```

Calculate:

```text
5 - 0 = 5
```

Check:

```text
5 <= 3
```

False.

Index `1` is not stable.

---

### Index 2

Current maximum:

```text
maxi = max(5, 1)
     = 5
```

Suffix minimum:

```text
suffixMin[2] = 1
```

Calculate:

```text
5 - 1 = 4
```

Check:

```text
4 <= 3
```

False.

Index `2` is not stable.

---

### Index 3

Current maximum:

```text
maxi = max(5, 4)
     = 5
```

Suffix minimum:

```text
suffixMin[3] = 4
```

Calculate:

```text
5 - 4 = 1
```

Check:

```text
1 <= 3
```

True.

Therefore:

```text
answer = 3
```

---

## ✅ Output

```text
3
```

---

## 🧠 Another Example

Consider:

```text
nums = [3, 2, 1]
k = 1
```

Suffix minimum:

```text
suffixMin = [1, 1, 1]
```

Now check the indices.

### Index 0

```text
maxi = 3
minimum = 1

3 - 1 = 2
```

```text
2 <= 1
```

False.

---

### Index 1

```text
maxi = 3
minimum = 1

3 - 1 = 2
```

False.

---

### Index 2

```text
maxi = 3
minimum = 1

3 - 1 = 2
```

False.

Therefore:

```text
answer = -1
```

---

## 💻 Java Solution

```java
class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;

        int[] suffixMin = new int[n];

        suffixMin[n - 1] = nums[n - 1];

        for (int i = n - 2; i >= 0; i--) {
            suffixMin[i] = Math.min(suffixMin[i + 1], nums[i]);
        }

        int maxi = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            maxi = Math.max(maxi, nums[i]);

            int cal = maxi - suffixMin[i];

            if (cal <= k) {
                return i;
            }
        }

        return -1;
    }
}
```

---

## 🧠 Code Explanation

### 1. Get Array Length

```java
int n = nums.length;
```

This gives the number of elements in the array.

---

### 2. Create Suffix Minimum Array

```java
int[] suffixMin = new int[n];
```

This array stores:

```text
suffixMin[i] = minimum from i to n - 1
```

---

### 3. Initialize the Last Position

```java
suffixMin[n - 1] = nums[n - 1];
```

The last position has only one element.

For:

```text
nums = [5, 0, 1, 4]
```

we get:

```text
suffixMin[3] = 4
```

---

### 4. Calculate Remaining Suffix Minimums

```java
for (int i = n - 2; i >= 0; i--) {
    suffixMin[i] = Math.min(suffixMin[i + 1], nums[i]);
}
```

We move from right to left.

For example:

```text
nums[i] = 1
suffixMin[i + 1] = 4
```

Therefore:

```text
suffixMin[i] = min(1, 4)
             = 1
```

---

### 5. Maintain Maximum From the Left

```java
int maxi = Integer.MIN_VALUE;
```

Then:

```java
maxi = Math.max(maxi, nums[i]);
```

This means:

```text
maxi = maximum value from index 0 to i
```

For:

```text
nums = [5, 0, 1, 4]
```

the values of `maxi` are:

```text
i = 0 → 5
i = 1 → 5
i = 2 → 5
i = 3 → 5
```

---

### 6. Calculate the Stability Value

```java
int cal = maxi - suffixMin[i];
```

This exactly represents:

```text
max(nums[0...i]) - min(nums[i...n-1])
```

---

### 7. Check Whether the Index Is Stable

```java
if (cal <= k) {
    return i;
}
```

If the difference is at most `k`, we found a stable index.

We immediately return `i` because we are traversing from:

```text
0 → 1 → 2 → 3 → ...
```

Therefore, it is guaranteed to be the smallest stable index.

---

## ⚠️ Common Mistakes

### Mistake 1: Calculating Minimum Only From the Left

This is incorrect:

```java
mini = Math.min(mini, nums[i]);
```

The problem requires:

```text
minimum from i to the END
```

not:

```text
minimum from 0 to i
```

That's why we need `suffixMin`.

---

### Mistake 2: Using the Index Instead of the Value

Incorrect:

```java
Math.max(maxi, i)
```

Correct:

```java
Math.max(maxi, nums[i])
```

`i` is the position.

`nums[i]` is the actual value.

---

### Mistake 3: Returning `k`

The question asks for an index.

So:

```java
return i;
```

not:

```java
return k;
```

---

### Mistake 4: Returning the Last Stable Index

We need the **smallest** stable index.

Therefore, traverse from left to right and return immediately when the condition is satisfied.

---

## 🧩 Why Do We Use Two Directions?

This is the main idea of the problem.

The required expression is:

```text
maximum from LEFT
        -
minimum from RIGHT
```

Therefore:

```text
LEFT → RIGHT
     maximum
     
RIGHT → LEFT
      minimum
```

We calculate the right-side minimum first, then scan from the left.

---

## 🧠 Key Learning

Remember the problem using this pattern:

```text
          nums
           ↓
    ┌──────┴──────┐
    ↓             ↓
  LEFT           RIGHT
    ↓             ↓
Maximum         Minimum
    ↓             ↓
    └──────┬──────┘
           ↓
       difference
           ↓
        <= k ?
       /      \
     YES       NO
      ↓         ↓
  return i    continue
```

The two important formulas are:

```text
Prefix Maximum:

maxi = Math.max(maxi, nums[i])
```

and:

```text
Suffix Minimum:

suffixMin[i] = Math.min(nums[i], suffixMin[i + 1])
```

Then:

```text
maxi - suffixMin[i] <= k
```

means the current index is stable.

---

## ⏱️ Time Complexity

Building the suffix minimum:

```text
O(n)
```

Finding the first stable index:

```text
O(n)
```

Total:

```text
O(n)
```

---

## 💾 Space Complexity

We create:

```text
suffixMin → O(n)
```

Therefore:

```text
O(n)
```

extra space.

---

## 🎯 Interview Takeaway

When you see a problem that asks for:

```text
maximum from the beginning
+
minimum from the current position to the end
```

think:

```text
Prefix Maximum + Suffix Minimum
```

For this problem:

```text
1. Build suffix minimum
2. Maintain prefix maximum
3. Calculate maximum - minimum
4. Check <= k
5. Return the first valid index
```

The most important line to remember is:

```java
int cal = maxi - suffixMin[i];
```

If:

```java
cal <= k
```

then:

```java
return i;
```

---

## 💻 Language

Java
````
