# 3302. Find the Lexicographically Smallest Valid Sequence

## 🟡 Difficulty

Medium

---

## 🔗 Problem Link

https://leetcode.com/problems/find-the-lexicographically-smallest-valid-sequence/

---

## 🏷️ Tags

- String
- Two Pointers
- Greedy
- Subsequence

---

## 📖 Problem Statement

You are given two strings `word1` and `word2`.

You need to find a sequence of indices from `word1` such that:

- The indices are strictly increasing.
- The selected characters form a string that can be made equal to `word2` by changing at most one character.

Among all valid sequences, return the **lexicographically smallest sequence of indices**.

If no valid sequence exists, return an empty array.

---

## 💡 Intuition

We need to select characters from `word1` to match `word2`.

We are allowed to have **at most one mismatch**.

The main challenge is finding the **lexicographically smallest indices**.

To achieve this:

1. Process `word1` from right to left to determine whether the remaining part of `word2` can be matched.
2. Process `word1` from left to right.
3. Always choose the earliest possible index.
4. If the current characters do not match, use the one allowed mismatch only when the remaining characters can still be matched.
5. Continue until all characters of `word2` are selected.

---

## 🚀 Approach

### 1. Build Suffix Information

Traverse `word1` from right to left and match `word2` from right to left.

Store the latest possible position for each character of `word2`.

This allows us to check whether the remaining part of `word2` can still be formed after choosing a particular index.

### 2. Greedy Selection

Traverse `word1` from left to right.

For each character:

- If it matches the current character of `word2`, select its index.
- If it does not match, we can select it as the **one allowed mismatch**, but only if the rest of `word2` can still be matched.

Because we always select the earliest valid index, the resulting index sequence is lexicographically smallest.

---

## ✅ Algorithm

1. Create an array `last` for suffix matching.
2. Traverse `word1` and `word2` from right to left.
3. Store the latest valid position for every character of `word2`.
4. Traverse `word1` from left to right.
5. If characters match, add the current index.
6. Otherwise, use the allowed mismatch if the remaining characters can still be matched.
7. Continue until `word2` is completely matched.
8. If all characters are selected, return the answer.
9. Otherwise, return an empty array.

---

## 📌 Example

### Input

```text
word1 = "vbcca"
word2 = "abc"
```

### Indices

```text
index:  0 1 2 3 4
word1:  v b c c a
```

We need:

```text
a b c
```

Choose:

```text
[0, 1, 2]
```

The selected characters are:

```text
v b c
```

Compare with:

```text
a b c
```

Only the first character is different:

```text
v → a
```

So the sequence is valid because only **one mismatch** is allowed.

The answer is:

```text
[0, 1, 2]
```

---

## ⏱️ Time Complexity

**O(n + m)**

Where:

- `n` = length of `word1`
- `m` = length of `word2`

---

## 💾 Space Complexity

**O(m)**

The additional space is used for the suffix information and answer array.

---

## 🧠 Key Learning

- Greedy selection
- Two-pointer technique
- Subsequence matching
- Suffix feasibility
- Lexicographical ordering
- Handling one allowed mismatch

---

## 💻 Language

Java