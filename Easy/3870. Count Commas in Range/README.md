```markdown
# LeetCode 3870 - Count Commas in Range

**Difficulty:** Easy

**Problem Link:** https://leetcode.com/problems/count-commas-in-range/

**Tags:** Math, Counting

---

## Problem Statement

You are given an integer `n`.

Count the total number of commas that appear when writing all integers from `1` to `n` using standard comma formatting.

For example:

- `1` → `1` → 0 commas
- `999` → `999` → 0 commas
- `1000` → `1,000` → 1 comma
- `10000` → `10,000` → 1 comma
- `100000` → `100,000` → 1 comma

Return the total number of commas.

---

## Example 1

### Input

`n = 1002`

### Explanation

Numbers from `1` to `999` have no commas.

Starting from `1000`, every number has one comma:

`1000 → 1,000`  
`1001 → 1,001`  
`1002 → 1,002`

There are:

`1002 - 1000 + 1 = 3`

numbers having one comma.

### Output

`3`

---

## Example 2

### Input

`n = 999`

Numbers from `1` to `999` do not contain commas.

### Output

`0`

---

## Example 3

### Input

`n = 100000`

All numbers from `1000` to `100000` contain exactly one comma.

Number of such numbers:

`100000 - 1000 + 1 = 99001`

### Output

`99001`

---

# Intuition

The important thing is to understand when the first comma appears.

Numbers with fewer than 4 digits do not have commas:

`1` to `999`

Starting from `1000`, every number has one comma:

`1000 → 1,000`  
`9999 → 9,999`  
`10000 → 10,000`  
`100000 → 100,000`

Since the constraint is:

`n <= 10^5 = 100000`

we never need to deal with numbers having more than one comma.

Therefore, we only need to count how many numbers are between `1000` and `n`.

---

# Approach

### Case 1: `n < 1000`

No number contains a comma.

So:

`answer = 0`

### Case 2: `n >= 1000`

The first number containing a comma is `1000`.

The count of numbers from `1000` to `n` is:

`n - 1000 + 1`

which simplifies to:

`n - 999`

So the final formula is:

`max(0, n - 999)`

---

# Algorithm

1. Check whether `n` is less than `1000`.
2. If yes, return `0`.
3. Otherwise, return `n - 999`.

---

# Java Solution

    class Solution {
        public int countCommas(int n) {
            return Math.max(0, n - 999);
        }
    }

---

# Code Explanation

### `n - 999`

This calculates how many numbers from `1000` to `n` exist.

For example:

`n = 1002`

`1002 - 999 = 3`

Those three numbers are:

`1000, 1001, 1002`

Each contains exactly one comma.

Therefore, total commas = `3`.

---

### `Math.max(0, ...)`

Suppose:

`n = 500`

Then:

`500 - 999 = -499`

We cannot have a negative number of commas.

So:

`Math.max(0, -499) = 0`

---

# Dry Run

### Input

`n = 1005`

### Step 1

Calculate:

`n - 999`

`1005 - 999 = 6`

### Step 2

The numbers are:

`1000, 1001, 1002, 1003, 1004, 1005`

There are 6 numbers.

Each has one comma.

Therefore:

`Total commas = 6`

### Output

`6`

---

# Important Concept

Remember:

`10^5 = 100000`

So when you see a constraint such as:

`n <= 10^5`

it means:

`n <= 100000`

For powers of 10:

`10^1 = 10`  
`10^2 = 100`  
`10^3 = 1000`  
`10^4 = 10000`  
`10^5 = 100000`

---

# Complexity

**Time Complexity:** `O(1)`

Only one calculation is performed.

**Space Complexity:** `O(1)`

No extra data structure is used.

---

# Key Learning

- Understand powers of 10.
- Identify when numbers start containing commas.
- Count a range using:

`end - start + 1`

- Use `Math.max()` to prevent a negative answer.
- Sometimes a problem that looks like it requires looping can be solved directly using a mathematical formula.

---

## Final Formula

`answer = max(0, n - 999)`

---

**Language:** 
Java
```
