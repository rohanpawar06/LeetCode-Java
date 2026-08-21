# 3116. Kth Smallest Amount With Single Denomination Combination

## 🔴 Difficulty

Hard

---

## 🔗 Problem Link

https://leetcode.com/problems/kth-smallest-amount-with-single-denomination-combination/

---

## 🏷️ Tags

- Array
- Binary Search
- Math
- Number Theory
- GCD / LCM
- Inclusion-Exclusion Principle

---

## 📖 Problem Statement

You are given an array `coins` containing different coin denominations and an integer `k`.

For each amount, you can use only **one type of coin denomination**.

For example:

```text
coins = [3, 5]
```

The possible amounts are:

```text
3, 5, 6, 9, 10, 12, 15, ...
```

We need to find the **k-th smallest distinct amount** that can be formed.

---

## 💡 Intuition

We cannot generate all possible amounts because `k` and the coin values can be very large.

Instead, we use **Binary Search on the Answer**.

For a given value `x`, we calculate how many distinct valid amounts are less than or equal to `x`.

For one coin, the number of multiples less than or equal to `x` is:

```text
x / coin
```

For example:

```text
coin = 3
x = 10

Multiples:
3, 6, 9

Count = 10 / 3 = 3
```

With multiple coins, some amounts can be counted more than once.

For example:

```text
coins = [2, 3]
```

The amount `6` is divisible by both `2` and `3`.

Therefore, we use the **Inclusion-Exclusion Principle** to count every distinct amount only once.

---

## 🚀 Approach

1. Use Binary Search to search for the answer.
2. For every `mid`, calculate how many distinct valid amounts are `<= mid`.
3. Use Inclusion-Exclusion to avoid duplicate counting.
4. If the count is at least `k`, move to the left half.
5. Otherwise, move to the right half.
6. Continue until `low == high`.
7. Return `low`.

---

## 🔍 Algorithm

```text
1. Set low = 1.
2. Set high to a sufficiently large upper bound.

3. While low < high:

       mid = low + (high - low) / 2

       Calculate the number of distinct
       valid amounts <= mid.

       If count >= k:
           high = mid
       Else:
           low = mid + 1

4. Return low.
```

For counting, use Inclusion-Exclusion:

```text
Multiples of coin 1
+ Multiples of coin 2
- Multiples of LCM(coin 1, coin 2)
+ ...
```

For example:

```text
coins = [2, 3]
x = 10
```

Multiples of `2`:

```text
2, 4, 6, 8, 10
```

Count = `5`

Multiples of `3`:

```text
3, 6, 9
```

Count = `3`

`6` was counted twice, so subtract multiples of:

```text
LCM(2, 3) = 6
```

There is one such multiple.

Therefore:

```text
5 + 3 - 1 = 7
```

---

## 📌 Example

### Input

```text
coins = [3, 5]
k = 5
```

The distinct amounts are:

```text
3, 5, 6, 9, 10, 12, ...
```

Therefore:

```text
1st → 3
2nd → 5
3rd → 6
4th → 9
5th → 10
```

### Output

```text
10
```

---

## 🧠 Key Learning

### Pattern: Binary Search on Answer + Inclusion-Exclusion

The main pattern is:

```text
Binary Search
      ↓
Count valid values <= mid
      ↓
Inclusion-Exclusion
      ↓
Avoid duplicate multiples
      ↓
Check count >= k
      ↓
Move left or right
```

Important concepts:

- Binary Search on Answer
- Counting Multiples
- GCD
- LCM
- Inclusion-Exclusion
- Avoiding Duplicate Counting

The key question to recognize in similar problems is:

> Can I efficiently calculate how many valid values exist up to `X`?

If yes, **Binary Search on the Answer** may be useful.

---

## ⏱️ Time Complexity

If there are `n` coin denominations, Inclusion-Exclusion considers subsets of the denominations.

```text
O(2^n × log(maxAnswer))
```

---

## 💾 Space Complexity

```text
O(n)
```

for storing the coin values and recursion/subset information.

---

## 💻 Language

Java