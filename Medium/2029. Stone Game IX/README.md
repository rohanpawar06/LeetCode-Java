# 2029. Stone Game IX

## 🟡 Difficulty

Medium

---

## 🔗 Problem Link

https://leetcode.com/problems/stone-game-ix/

---

## 🏷️ Tags

- Array
- Math
- Game Theory
- Modular Arithmetic
- Greedy
- Counting

---

## 📖 Problem Statement

Alice and Bob take turns removing one stone from the array.

The score is the sum of all removed stones.

If a player makes the sum divisible by `3`, that player **loses immediately**.

Alice goes first.

Return `true` if Alice can win, otherwise return `false`.

The important observation is that we only care about the remainder of each stone when divided by `3`.

Every stone belongs to one of three groups:

```text
stone % 3 = 0
stone % 3 = 1
stone % 3 = 2
```

---

## 💡 Intuition

Instead of working with the actual values of the stones, count how many stones have each remainder.

For example:

```text
stones = [2, 3, 5, 8, 10]
```

Their remainders are:

```text
2 → remainder 2
3 → remainder 0
5 → remainder 2
8 → remainder 2
10 → remainder 1
```

So:

```text
count[0] = 1
count[1] = 1
count[2] = 3
```

The game depends only on these three counts.

A remainder `0` does not change the current remainder of the sum.

A remainder `1` and remainder `2` affect the sum in opposite ways:

```text
1 + 2 = 0 (mod 3)
2 + 1 = 0 (mod 3)
```

Therefore, players must carefully choose between remainder `1` and `2` stones to avoid making the sum divisible by `3`.

---

## 🚀 Approach

1. Count the number of stones having remainder `0`, `1`, and `2`.
2. Alice cannot start with a remainder `0` stone because the sum would immediately become divisible by `3`.
3. The number of remainder `0` stones affects the turn order.
4. If `count[0]` is even, Alice can win when both remainder `1` and remainder `2` stones are available.
5. If `count[0]` is odd, Alice needs the difference between `count[1]` and `count[2]` to be greater than `2`.
6. Return the result based on these conditions.

---

## ✅ Algorithm

```text
1. Create count[3].

2. For every stone:
       count[stone % 3]++

3. If count[0] is even:
       Return true if both count[1] and count[2] are greater than 0.

4. If count[0] is odd:
       Return true if
       abs(count[1] - count[2]) > 2.

5. Otherwise return false.
```

---

## 📌 Example

### Input

```text
stones = [2, 1]
```

Count remainders:

```text
2 % 3 = 2
1 % 3 = 1
```

Therefore:

```text
count[0] = 0
count[1] = 1
count[2] = 1
```

`count[0]` is even and both remainder `1` and `2` stones are available.

Alice can choose `2`:

```text
sum = 2
```

Bob must choose `1`:

```text
sum = 2 + 1 = 3
```

Bob makes the sum divisible by `3`, so Bob loses.

Therefore:

```text
Output = true
```

---

## ⏱️ Time Complexity

**O(n)**

We traverse the array once to count the three remainder groups.

---

## 💾 Space Complexity

**O(1)**

Only three counters are required:

```text
count[0]
count[1]
count[2]
```

---

## 🧠 Key Learning

- Use `modulo 3` instead of actual stone values.
- Reduce the problem to three remainder groups.
- Understand how the current sum's remainder changes after each move.
- Use counting instead of simulating every possible game.
- This is a **Game Theory + Modular Arithmetic** problem.

The important pattern is:

```text
Actual Values
     ↓
Calculate % 3
     ↓
Count [0, 1, 2]
     ↓
Apply Game Theory
     ↓
Determine Winner
```

---

## 💻 Language

Java.