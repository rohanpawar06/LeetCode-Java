# 3731. Find Missing Elements

## 🟢 Difficulty

Easy

---

## 🔗 Problem Link

https://leetcode.com/problems/find-missing-elements/

---

## 🏷️ Tags

- Array
- HashSet
- Sorting
- Simulation

---

## 📖 Problem Statement

You are given an integer array `nums` consisting of unique integers.

Originally, `nums` contained every integer within a certain range. However, some integers are missing from the array.

The smallest and largest integers of the original range are still present in the array.

Return a sorted list of all missing integers in the range.

---

## 💡 Intuition

The smallest and largest values define the complete range.

If we know every number that already exists, we can simply iterate through the range and collect the numbers that are missing.

A `HashSet` allows us to check whether a number exists in **O(1)** time.

---

## 🚀 Approach

1. Store every element of the array in a `HashSet`.
2. Find the minimum and maximum values.
3. Iterate from `min + 1` to `max - 1`.
4. If a number is not present in the set, add it to the answer list.
5. Return the answer.

---

## ✅ Algorithm

1. Create a `HashSet`.
2. Traverse the array:
   - Insert every number into the set.
   - Find the minimum value.
   - Find the maximum value.
3. Traverse from `min + 1` to `max - 1`.
4. If a number is absent from the set, add it to the answer.
5. Return the answer list.

---

## ⏱️ Time Complexity

- Building HashSet: **O(n)**
- Finding min & max: **O(n)**
- Traversing the range: **O(max - min)**

Overall:

**O(n + (max - min))**

---

## 💾 Space Complexity

**O(n)**

(HashSet stores all elements.)

---

## 📌 Example

### Input

```text
nums = [1,4,2,5]
```

### Output

```text
[3]
```

### Explanation

The complete range is:

```text
1 2 3 4 5
```

Array contains:

```text
1 2 4 5
```

Missing number:

```text
3
```

---

## 🧠 Key Learning

- Using a `HashSet` for constant-time lookups.
- Finding missing values within a range.
- Combining minimum and maximum value tracking in a single traversal.
- Improving from a sorting-based solution to an optimal solution.

---

## 💻 Language

Java