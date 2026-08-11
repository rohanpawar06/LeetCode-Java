# 2996. Smallest Missing Integer Greater Than Sequential Prefix Sum

## 🟢 Difficulty

Easy

---

## 🔗 Problem Link

https://leetcode.com/problems/smallest-missing-integer-greater-than-sequential-prefix-sum/

---

## 🏷️ Tags

- Array
- HashSet
- Sequential Prefix
- Simulation

---

## 📖 Problem Statement

You are given an array of positive integers `nums`.

A sequential prefix is a prefix where every element after the first is exactly one greater than the previous element.

Find the sum of the elements in the longest sequential prefix.

Then find the smallest positive integer greater than or equal to this sum that does not exist in `nums`.

Return that integer.

---

## 💡 Intuition

First, find the longest sequential prefix of the array.

For example:

```text
nums = [1, 2, 3, 5, 6]
```

The longest sequential prefix is:

```text
[1, 2, 3]
```

Its sum is:

```text
1 + 2 + 3 = 6
```

Now check whether `6` exists in the array.

Since `6` exists, check:

```text
7
```

`7` does not exist, so the answer is:

```text
7
```

A `HashSet` provides fast lookup to check whether a number exists in the array.

---

## 🚀 Approach

1. Store all elements of `nums` in a `HashSet`.
2. Start the sequential prefix with the first element.
3. Traverse the array from the second element.
4. Continue adding elements while:
   ```text
   nums[i] == nums[i - 1] + 1
   ```
5. Stop when the sequential pattern breaks.
6. The resulting sum is the initial candidate answer.
7. While the candidate exists in the `HashSet`, increment it.
8. Return the first number that is not present.

---

## ✅ Algorithm

```text
1. Create a HashSet.
2. Add every element of nums to the set.
3. Initialize sum = nums[0].
4. Traverse nums from index 1.
5. If nums[i] == nums[i-1] + 1:
       Add nums[i] to sum.
   Otherwise:
       Stop.
6. Set ans = sum.
7. While ans exists in the HashSet:
       ans++
8. Return ans.
```

---

## 📌 Example

### Input

```text
nums = [1,2,3,5,6]
```

### Step 1: Sequential Prefix

```text
1 → 2 → 3
```

The sequence breaks at `5`.

### Step 2: Calculate Sum

```text
1 + 2 + 3 = 6
```

### Step 3: Find Missing Integer

```text
6 → Present
7 → Missing
```

### Output

```text
7
```

---

## ⏱️ Time Complexity

**O(n)**

- Building the HashSet: `O(n)`
- Finding the sequential prefix: `O(n)`
- Finding the missing integer: `O(n)` in the worst case

---

## 💾 Space Complexity

**O(n)**

The HashSet stores the elements of the array.

---

## 🧠 Key Learning

- Finding a sequential prefix.
- Using `HashSet` for fast existence checking.
- Separating the problem into prefix calculation and missing-element search.
- Using `HashSet.contains()` for efficient lookup.

---

## 💻 Language

Java