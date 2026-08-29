# 2948. Make Lexicographically Smallest Array by Swapping Elements

## 🟡 Difficulty

Medium

---

## 🔗 Problem Link

https://leetcode.com/problems/make-lexicographically-smallest-array-by-swapping-elements/

---

## 🏷️ Tags

- Array
- Sorting
- Greedy
- Grouping

---

## 📖 Problem Statement

You are given a 0-indexed array of positive integers `nums` and a positive integer `limit`.

In one operation, you can choose two indices `i` and `j` and swap `nums[i]` and `nums[j]` if:

`|nums[i] - nums[j]| <= limit`

You can perform the operation any number of times.

Return the lexicographically smallest array that can be obtained.

An array is lexicographically smaller than another array if, at the first position where they differ, the first array has a smaller value.

---

## 💡 Intuition

The key observation is that elements can be connected through a chain of valid swaps.

For example:

```text
nums = [1, 3, 5]
limit = 2
```

We have:

```text
3 - 1 = 2
5 - 3 = 2
```

Therefore:

```text
1 ↔ 3 ↔ 5
```

Even though:

```text
5 - 1 = 4 > 2
```

all three elements can still be rearranged through the intermediate value `3`.

So, after sorting the elements by value, we divide them into groups.

If the difference between two consecutive sorted values is greater than `limit`, a new group starts.

Inside each group, the values can be rearranged among their original positions.

To obtain the lexicographically smallest array, assign the smallest value to the smallest original index, the second smallest value to the second smallest index, and so on.

---

## 🚀 Approach

1. Create an array containing all original indices.
2. Sort the indices according to the values in `nums`.
3. Traverse the sorted indices and identify groups.
4. Put consecutive values in the same group if their difference is at most `limit`.
5. For every group:
   - Extract its original indices.
   - Sort those original indices.
   - The values of the group are already sorted.
   - Assign the smallest values to the smallest original indices.
6. Return the resulting array.

---

## 🔍 Algorithm

1. Create:
   `indices = [0, 1, 2, ..., n - 1]`

2. Sort `indices` according to `nums[index]`.

3. Start from `i = 0`.

4. Find the end of the current group while:

   `nums[indices[j]] - nums[indices[j - 1]] <= limit`

5. Extract the original indices of this group.

6. Sort the original indices.

7. Assign the sorted values to the sorted original indices.

8. Move `i` to the next group.

9. Return the answer.

---

## 📌 Example

### Input

```text
nums = [1, 5, 3, 9, 8]
limit = 2
```

### Step 1: Sort by Value

```text
Value    Original Index

1        0
3        2
5        1
8        4
9        3
```

So the sorted indices are:

```text
[0, 2, 1, 4, 3]
```

---

### Step 2: Create Groups

First group:

```text
[1, 3, 5]
```

because:

```text
3 - 1 = 2 <= limit
5 - 3 = 2 <= limit
```

Now:

```text
8 - 5 = 3 > limit
```

so `8` starts a new group.

Second group:

```text
[8, 9]
```

because:

```text
9 - 8 = 1 <= limit
```

Therefore:

```text
Group 1 = [1, 3, 5]
Group 2 = [8, 9]
```

---

## 📌 Rearranging Group 1

Values:

```text
[1, 3, 5]
```

Original indices:

```text
[0, 2, 1]
```

Sort the original indices:

```text
[0, 1, 2]
```

Assign the values:

```text
index 0 → 1
index 1 → 3
index 2 → 5
```

---

## 📌 Rearranging Group 2

Values:

```text
[8, 9]
```

Original indices:

```text
[4, 3]
```

Sort the original indices:

```text
[3, 4]
```

Assign:

```text
index 3 → 8
index 4 → 9
```

---

## ✅ Output

```text
[1, 3, 5, 8, 9]
```

---

## 🧠 Why Does Grouping Work?

Suppose the sorted values are:

```text
1, 4, 7
```

and:

```text
limit = 3
```

We have:

```text
4 - 1 = 3
7 - 4 = 3
```

Therefore:

```text
1 ↔ 4 ↔ 7
```

Even though:

```text
7 - 1 = 6 > 3
```

the values can still be connected through `4`.

So all three values belong to the same group and can be rearranged among their original positions.

This is the most important observation in the problem.

---

## 💻 Java Solution

```java
import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {

        int n = nums.length;

        Integer[] indices = new Integer[n];

        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        Arrays.sort(indices, (a, b) -> Integer.compare(nums[a], nums[b]));

        int[] ans = new int[n];

        int i = 0;

        while (i < n) {

            int j = i + 1;

            while (j < n &&
                   nums[indices[j]] - nums[indices[j - 1]] <= limit) {
                j++;
            }

            Integer[] group = Arrays.copyOfRange(indices, i, j);

            Arrays.sort(group);

            for (int k = 0; k < group.length; k++) {
                ans[group[k]] = nums[indices[i + k]];
            }

            i = j;
        }

        return ans;
    }
}
```

---

## 🧠 Code Explanation

### 1. Store Original Indices

```java
Integer[] indices = new Integer[n];

for (int i = 0; i < n; i++) {
    indices[i] = i;
}
```

Initially:

```text
indices = [0, 1, 2, 3, ...]
```

We store indices because we need to remember the original positions after sorting.

---

### 2. Sort Indices According to Values

```java
Arrays.sort(indices, (a, b) -> Integer.compare(nums[a], nums[b]));
```

For:

```text
nums = [1, 5, 3, 9, 8]
```

we get:

```text
indices = [0, 2, 1, 4, 3]
```

because:

```text
nums[0] = 1
nums[2] = 3
nums[1] = 5
nums[4] = 8
nums[3] = 9
```

---

### 3. Find a Group

```java
while (j < n &&
       nums[indices[j]] - nums[indices[j - 1]] <= limit) {
    j++;
}
```

This finds all consecutive sorted values that belong to the same connected group.

---

### 4. Sort Original Indices

```java
Arrays.sort(group);
```

The values are already sorted.

We sort the original indices so that the smallest value can be placed at the smallest position.

---

### 5. Assign Values

```java
for (int k = 0; k < group.length; k++) {
    ans[group[k]] = nums[indices[i + k]];
}
```

For example:

```text
Values:
[1, 3, 5]

Original indices:
[0, 2, 1]

Sorted indices:
[0, 1, 2]
```

Assignment:

```text
ans[0] = 1
ans[1] = 3
ans[2] = 5
```

---

## ⚠️ Common Mistake

Do not simply sort the entire array.

For example:

```text
nums = [10, 1, 20]
limit = 2
```

The values cannot be freely swapped because:

```text
10 - 1 = 9
20 - 10 = 10
```

They belong to different groups.

Therefore, the answer remains:

```text
[10, 1, 20]
```

We can only rearrange values inside their valid groups.

---

## 🧠 Key Learning

### Pattern: Sorting + Grouping + Greedy

Remember this problem as:

```text
Restricted Swapping
        ↓
Sort Values
        ↓
Find Connected Groups
        ↓
Collect Original Indices
        ↓
Sort Original Indices
        ↓
Assign Smallest Values
        ↓
Lexicographically Smallest Array
```

The most important rule is:

```text
Adjacent sorted difference <= limit
                ↓
          Same Group
                ↓
       Values can be rearranged
```

---

## ⏱️ Time Complexity

Sorting all indices:

```text
O(n log n)
```

Sorting the original indices inside the groups:

```text
O(n log n)
```

Overall:

```text
O(n log n)
```

---

## 💾 Space Complexity

We use:

- `indices` → `O(n)`
- `ans` → `O(n)`
- Temporary group → `O(n)` in the worst case

Therefore:

```text
O(n)
```

---

## 🎯 Interview Takeaway

When you see a problem involving:

- Restricted swaps
- Difference between values
- Lexicographically smallest array
- Elements that can indirectly swap

Think:

```text
SORT
  ↓
GROUP
  ↓
SORT ORIGINAL INDICES
  ↓
GREEDILY ASSIGN VALUES
```

---

## 💻 Language

Java