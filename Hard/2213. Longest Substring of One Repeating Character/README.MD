# 2213. Longest Substring of One Repeating Character

## 🟠 Difficulty

Hard

---

## 🔗 Problem Link

https://leetcode.com/problems/longest-substring-of-one-repeating-character/

---

## 🏷️ Tags

- String
- Segment Tree
- Point Update
- Range Query

---

## 📖 Problem Statement

You are given a string `s` and multiple queries.

For each query, one character of `s` is changed. After every change, find the length of the **longest substring consisting of the same character**.

Return the result for every query.

---

## 💡 Intuition

If we scan the complete string after every query, the solution can become too slow.

Since each query changes only **one character**, we can use a **Segment Tree** to update only the affected part of the string.

Each node stores information about its segment:

- `leftChar` → first character
- `rightChar` → last character
- `prefix` → longest repeating prefix
- `suffix` → longest repeating suffix
- `best` → longest repeating substring
- `len` → length of the segment

When two nodes are merged, their information is combined to calculate the answer for the larger segment.

---

## 🚀 Approach

1. Build a Segment Tree from the original string.
2. Store the required information for every segment.
3. For each query:
   - Change the character at the given index.
   - Perform a point update in the Segment Tree.
   - Merge the affected nodes.
4. The root node represents the entire string.
5. `tree[1].best` gives the longest repeating substring after the update.

---

## 🔍 Merge Logic 

Suppose two adjacent segments are:

```text
Left Segment | Right Segment
```

If:

```text
left.rightChar == right.leftChar
```

the repeating characters can continue across the boundary.

Therefore:

```text
left.suffix + right.prefix
```

can form a longer repeating substring.

So:

```text
best = max(
    left.best,
    right.best,
    left.suffix + right.prefix
)
```

We also update the prefix and suffix when an entire segment contains the same character.

---

## 🧪 Example

```text
s = "babacc"
```

After changing characters using the queries, suppose the string becomes:

```text
"bcbbcc"
```

The repeating substrings are:

```text
"bb"
"cc"
```

Both have length `2`.

Therefore, the answer for that query is:

```text
2
```

The process is repeated for every query.

---

## 🧠 Key Learning

### Pattern: Segment Tree + Point Update

This problem teaches how to maintain information about a changing string efficiently.

The general pattern is:

```text
Build Tree
    ↓
Point Update
    ↓
Merge Nodes
    ↓
Read Answer from Root
```

The important concept is that each Segment Tree node stores **custom information**, rather than just a simple sum or minimum.

---

## ⏱️ Complexity

**Build:** `O(n)`

**Each Query:** `O(log n)`

**Total:** `O(n + q log n)`

**Space:** `O(n)`

Where:

- `n` = length of the string
- `q` = number of queries

---

## 💻 Language

Java