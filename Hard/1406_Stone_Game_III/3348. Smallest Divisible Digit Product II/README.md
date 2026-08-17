# 3348. Smallest Divisible Digit Product II

## 🔴 Difficulty

Hard

---

## 🔗 Problem Link

https://leetcode.com/problems/smallest-divisible-digit-product-ii/

---

## 🏷️ Tags

- Math
- Greedy
- String
- Prime Factorization
- Constructive Algorithm

---

## 📖 Problem Statement

You are given a numeric string `num` and an integer `t`.

Find the **smallest zero-free integer** that is **greater than or equal to** `num` such that the **product of its digits is divisible by `t`**.

If no such number exists, return `"-1"`.

---

## 💡 Intuition

Unlike Part I, brute force is not possible because `num` can contain up to **200,000 digits**.

Instead of checking every number one by one, we:

- Factorize `t` into its prime factors (`2`, `3`, `5`, `7`).
- Count the prime factors contributed by the digits of `num`.
- Traverse the number from right to left.
- Try replacing one digit with the next larger digit.
- Construct the smallest valid suffix that satisfies the remaining prime factor requirements.

If no number of the same length is possible, construct the smallest valid number with one extra digit.

---

## 🚀 Approach

1. Prime factorize `t`.
2. If `t` contains any prime factor other than `2`, `3`, `5`, or `7`, return `"-1"`.
3. Count the prime factors contributed by every digit in `num`.
4. Traverse the digits from right to left.
5. Try increasing the current digit.
6. Check whether the remaining suffix can satisfy the required prime factors.
7. Construct the lexicographically smallest valid suffix.
8. If no solution of the same length exists, build the smallest valid longer number.

---

## ✅ Algorithm

1. Factorize `t`.
2. Build the required prime factor count.
3. Compute the prime factor count of the current number.
4. Iterate from the last digit to the first:
   - Remove the current digit's contribution.
   - Try every larger digit.
   - Check whether the remaining digits can satisfy the required factors.
   - If yes, construct the answer.
5. If no answer exists, extend the number length and construct the smallest valid result.

---

## ⏱️ Time Complexity

**O(n)**

- `n` = Length of the string.

Each digit is processed a constant number of times.

---

## 💾 Space Complexity

**O(n)**

Used for:

- Prime factor maps
- Constructed answer
- Auxiliary data structures

---

## 📌 Example

### Input

```text
num = "1234"
t = 10
```

### Output

```text
1235
```

### Explanation

```
1234

1 × 2 × 3 × 4 = 24

24 % 10 ≠ 0
```

Next valid number:

```
1235

1 × 2 × 3 × 5 = 30

30 % 10 = 0
```

Hence,

```
1235
```

is the smallest valid answer.

---

## 🧠 Key Learning

- Prime Factorization
- Greedy Construction
- String Manipulation
- Lexicographical Ordering
- Constructive Algorithms
- Mathematical Optimization

---

## 📚 Concepts Used

- HashMap
- Greedy
- Prime Factorization
- StringBuilder
- Prefix & Suffix Processing
- Constructive Algorithms

---

## 💻 Language

Java