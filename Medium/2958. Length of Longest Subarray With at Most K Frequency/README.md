# 2958. Length of Longest Subarray With at Most K Frequency

## 🟡 Difficulty

Medium

---

## 🔗 Problem Link

https://leetcode.com/problems/length-of-longest-subarray-with-at-most-k-frequency/

---

## 🏷️ Tags

- Array
- HashMap
- Sliding Window
- Two Pointers
- Frequency Counting

---

## 📖 Problem Statement

Given an integer array `nums` and an integer `k`, find the length of the longest subarray such that every element appears at most `k` times.

A subarray must contain contiguous elements.

---

## 💡 Intuition

We can use the **Sliding Window** technique with a `HashMap`.

The `HashMap` stores the frequency of each element inside the current window.

We expand the window using the `right` pointer.

If any element appears more than `k` times, the window becomes invalid. We then move the `left` pointer forward until the window becomes valid again.

---

## 🚀 Approach

1. Create a `HashMap` to store the frequency of each element.
2. Initialize `left = 0`.
3. Move the `right` pointer through the array.
4. Add `nums[right]` to the frequency map.
5. If the frequency of `nums[right]` becomes greater than `k`, move `left` forward.
6. Decrease the frequency of elements removed from the window.
7. Calculate the current window length.
8. Keep track of the maximum length.

---

## 🔍 Algorithm

```text
Initialize left = 0
Initialize maxLength = 0
Create a HashMap for frequencies

For every right from 0 to n - 1:

    Add nums[right] to the frequency map

    While frequency of nums[right] > k:

        Decrease frequency of nums[left]
        Move left forward

    Calculate current window length

    Update maxLength

Return maxLength
```

---

## 🧪 Example

### Input

```text
nums = [1, 2, 2, 2, 3]
k = 2
```

### Dry Run

Start with:

```text
[1]
```

Valid.

Add `2`:

```text
[1, 2]
```

Valid.

Add another `2`:

```text
[1, 2, 2]
```

Frequency:

```text
1 → 1
2 → 2
```

Still valid.

Add another `2`:

```text
[1, 2, 2, 2]
```

Now:

```text
2 → 3
```

But:

```text
k = 2
```

So the window is invalid.

Move `left` forward:

```text
[2, 2, 2]
```

Still invalid.

Move `left` again:

```text
[2, 2]
```

Now:

```text
2 → 2
```

The window is valid again.

Continue processing the remaining elements and keep the maximum valid window length.

---

## ⏱️ Time Complexity

```text
O(n)
```

Each element is added to the window once and removed from the window at most once.

---

## 💾 Space Complexity

```text
O(n)
```

The `HashMap` stores the frequency of elements.

---

## 🧠 Key Learning

### Sliding Window + HashMap

This problem teaches the important pattern:

```text
Expand Window
      ↓
Update Frequency
      ↓
Window Invalid?
      ↓
Shrink Window
      ↓
Window Valid
      ↓
Update Maximum
```

Whenever a problem asks for:

```text
Longest/Shortest
+
Subarray/Substring
+
Frequency Constraint
```

consider using:

```text
Sliding Window + HashMap
```

---

## 💻 Language

Java