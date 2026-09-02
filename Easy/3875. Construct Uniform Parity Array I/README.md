````markdown
# 3875. Construct Uniform Parity Array I

## 🟡 Difficulty

Medium

---

## 🔗 Problem Link

https://leetcode.com/problems/construct-uniform-parity-array-i/

---

## 🏷️ Tags

- Array
- Math
- Parity
- Greedy
- Simulation

---

## 📖 Problem Statement

You are given an array `nums1`.

The goal is to determine whether it is possible to construct a **uniform parity array** using the allowed operations.

An array has uniform parity when all of its elements have the same parity.

A number can have one of two parities:

- Even
- Odd

For example:

```text
[2, 4, 6, 8]
```

is a uniform parity array because all elements are even.

Similarly:

```text
[1, 3, 5, 7]
```

is a uniform parity array because all elements are odd.

---

## 💡 Intuition

The key concept in this problem is **parity**.

Parity tells us whether a number is even or odd.

We can check parity using the modulo operator:

```text
num % 2 == 0 → Even
num % 2 == 1 → Odd
```

The allowed operation changes the parity of two elements at the same time.

Adding `1` changes parity:

```text
Even + 1 → Odd
Odd + 1  → Even
```

Therefore, instead of focusing on the actual values, we focus on how many elements are odd.

The important observation is that the parity of two elements can be changed together.

Thus, the number of odd elements determines whether the required uniform parity can be constructed.

---

## 🚀 Approach

1. Traverse the array.
2. Count the number of odd elements.
3. Since one operation changes the parity of two elements, odd elements can be handled in pairs.
4. If the number of odd elements is even, the required transformation is possible.
5. Otherwise, it is not possible.

---

## 🔍 Algorithm

```text
1. Initialize:
       odd = 0

2. Traverse every element in nums1.

3. If:
       nums1[i] % 2 == 1

   then increment odd.

4. Check:
       odd % 2 == 0

5. If true:
       return true

6. Otherwise:
       return false
```

---

## 📌 Example 1

### Input

```text
nums1 = [2, 4, 6, 8]
```

All elements are even:

```text
2 → Even
4 → Even
6 → Even
8 → Even
```

Number of odd elements:

```text
odd = 0
```

Since:

```text
0 % 2 = 0
```

the condition is satisfied.

### Output

```text
true
```

---

## 📌 Example 2

### Input

```text
nums1 = [1, 2, 3, 4]
```

Parity:

```text
1 → Odd
2 → Even
3 → Odd
4 → Even
```

Number of odd elements:

```text
odd = 2
```

Since:

```text
2 % 2 = 0
```

the number of odd elements is even.

### Output

```text
true
```

---

## 📌 Example 3

### Input

```text
nums1 = [1, 2, 4]
```

Parity:

```text
1 → Odd
2 → Even
4 → Even
```

Number of odd elements:

```text
odd = 1
```

Since:

```text
1 % 2 = 1
```

the number of odd elements is odd.

### Output

```text
false
```

---

## 🧠 Parity Basics

For any integer `x`:

```text
x % 2 == 0 → Even
x % 2 == 1 → Odd
```

Examples:

```text
2 % 2 = 0 → Even

3 % 2 = 1 → Odd

10 % 2 = 0 → Even

15 % 2 = 1 → Odd
```

---

## 💻 Java Solution

```java
class Solution {
    public boolean uniformArray(int[] nums1) {
        int odd = 0;

        for (int num : nums1) {
            if (num % 2 == 1) {
                odd++;
            }
        }

        return odd % 2 == 0;
    }
}
```

---

## 🧠 Code Explanation

### 1. Initialize Counter

```java
int odd = 0;
```

This variable stores the number of odd elements.

---

### 2. Traverse the Array

```java
for (int num : nums1)
```

We check every element of the array.

---

### 3. Check Whether the Number Is Odd

```java
if (num % 2 == 1)
```

If the remainder after dividing by `2` is `1`, the number is odd.

For example:

```text
7 % 2 = 1
```

Therefore `7` is odd.

---

### 4. Count Odd Elements

```java
odd++;
```

Every time an odd element is found, we increase the counter.

---

### 5. Check Whether the Count Is Even

```java
return odd % 2 == 0;
```

If the number of odd elements is even, the required transformation is possible.

---

## 🧪 Dry Run

Consider:

```text
nums1 = [1, 2, 3, 4, 5, 6]
```

Start:

```text
odd = 0
```

Process each element:

```text
1 → odd → odd = 1
2 → even
3 → odd → odd = 2
4 → even
5 → odd → odd = 3
6 → even
```

Final:

```text
odd = 3
```

Check:

```text
3 % 2 == 0
```

This is false.

Therefore:

```text
false
```

---

## 🧠 Key Observation

The most important thing to remember is:

```text
Adding 1
   ↓
Parity changes
   ↓
Even ↔ Odd
```

And when the operation affects two elements:

```text
Two parities change together
```

Therefore, we need to reason about the parity count rather than the actual values.

---

## 🧠 Key Learning

### Pattern: Parity + Counting

Remember the problem as:

```text
Array
  ↓
Check Even / Odd
  ↓
Count Odd Elements
  ↓
Analyze Parity
  ↓
Return Answer
```

Important concepts:

```text
% operator
Array traversal
Counting
Parity
Boolean condition
```

---

## ⚠️ Common Mistakes

### Mistake 1: Comparing Actual Values

Do not compare values directly.

For example:

```text
[2, 4, 100, 200]
```

The values are very different, but all are even.

What matters is:

```text
Even Even Even Even
```

---

### Mistake 2: Forgetting `% 2`

Use:

```java
num % 2
```

to determine parity.

---

### Mistake 3: Checking Only the First Element

Do not assume that because the first element is even, the whole array is even.

You must inspect every element.

---

## ⏱️ Time Complexity

We traverse the array exactly once.

```text
O(n)
```

where `n` is the length of `nums1`.

---

## 💾 Space Complexity

Only one counter is used.

```text
O(1)
```

---

## 🎯 Interview Takeaway

Whenever you see a problem involving:

- Even and odd numbers
- Adding or subtracting `1`
- Operations affecting two elements
- Making elements have the same parity

Think:

```text
PARITY
   ↓
Even = 0
Odd  = 1
   ↓
Understand how the operation changes parity
   ↓
Count / track parity
   ↓
Determine the answer
```

---

## 💻 Language

Java
````
