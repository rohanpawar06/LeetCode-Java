# 1140. Stone Game II

## 🟡 Difficulty

Medium

---

## 🔗 Problem Link

https://leetcode.com/problems/stone-game-ii/

---

## 🏷️ Tags

- Array
- Math
- Dynamic Programming
- Game Theory
- Prefix Sum
- Memoization

---

## 📖 Problem Statement

Alice and Bob play a game using an array `piles`.

Initially:

```text
M = 1
```

Alice starts the game.

On each turn, the current player can take the first `X` remaining piles, where:

```text
1 <= X <= 2 * M
```

After taking `X` piles:

```text
M = max(M, X)
```

The players continue until all piles are taken.

Both players play optimally.

Return the maximum number of stones Alice can obtain.

---

## 💡 Intuition

The important part of this problem is that the number of piles a player can take depends on `M`.

Initially:

```text
M = 1
```

So the player can take:

```text
1 or 2 piles
```

If the player takes `2` piles:

```text
M = max(1, 2)
M = 2
```

On the next turn, the player can take:

```text
1 to 4 piles
```

Therefore, the state of the game depends on:

- Current position `i`
- Current value of `M`

We use Dynamic Programming with these two values as the state.

---

## 🚀 Approach

1. Create a suffix sum array to quickly calculate the total number of stones remaining.
2. Define:

```text
dp[i][M]
```

as the maximum number of stones the current player can obtain starting from index `i` with the current `M`.

3. Try taking every possible number of piles:

```text
1 <= X <= 2 * M
```

4. After taking `X` piles:

```text
newM = max(M, X)
```

5. The opponent then plays optimally from the new state.
6. The current player's maximum result is:

```text
total remaining stones - opponent's maximum result
```

7. Store the result using memoization.

---

## ✅ Algorithm

1. Calculate suffix sums.
2. Start with:

```text
i = 0
M = 1
```

3. If:

```text
2 * M >= remaining piles
```

the current player can take all remaining piles.
4. Otherwise, try every:

```text
X = 1 ... 2 * M
```

5. Recursively calculate the opponent's best result.
6. Choose the maximum result.
7. Store it in `dp[i][M]`.
8. Return the result for `(0, 1)`.

---

## 📌 Example

### Input

```text
piles = [2,7,9,4,4]
```

Initially:

```text
M = 1
```

Alice can take either:

```text
1 pile
```

or:

```text
2 piles
```

If Alice takes 1:

```text
Alice gets 2
```

If Alice takes 2:

```text
Alice gets 2 + 7 = 9
```

The value of `M` changes depending on the number of piles taken.

By considering all possibilities with Dynamic Programming, Alice can obtain:

```text
10
```

### Output

```text
10
```

---

## 🧠 Key Learning

- Dynamic Programming
- Memoization
- Game Theory
- Suffix Sum
- Minimax-style thinking
- State representation

The most important state for this problem is:

```text
(index, M)
```

---

## ⏱️ Time Complexity

**O(n³)** in the straightforward DP formulation.

The number of states is approximately `O(n²)`, and each state can try up to `O(n)` choices.

---

## 💾 Space Complexity

**O(n²)**

Used for:

- DP table
- Suffix sum array

---

## 💻 Language

Java