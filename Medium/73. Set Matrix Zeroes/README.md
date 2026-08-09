# 73. Set Matrix Zeroes

## 🟡 Difficulty

Medium

---

## 🔗 Problem Link

https://leetcode.com/problems/set-matrix-zeroes/

---

## 🏷️ Tags

- Array
- Matrix
- In-Place Algorithm

---

## 📖 Problem Statement

Given an `m x n` integer matrix, if an element is `0`, set its entire row and column to `0`.

The operation must be performed **in-place**.

---

## 💡 Intuition

The important point is that we should not immediately convert a row or column to zero while scanning the matrix.

If we do that, the newly created zeroes could affect other rows and columns incorrectly.

Instead, we use two separate arrays:

```text
dummy1 → stores which rows should become zero
dummy2 → stores which columns should become zero
```

When we find an original zero at:

```text
matrix[i][j] == 0
```

we mark:

```text
dummy1[i] = 1
dummy2[j] = 1
```

After finding all original zeroes, we traverse the matrix again and set an element to zero if its row or column has been marked.

---

## 🚀 Approach

### Step 1 — Create Row and Column Arrays

```java
int[] dummy1 = new int[matrix.length];
int[] dummy2 = new int[matrix[0].length];
```

`dummy1` stores information about rows.

`dummy2` stores information about columns.

---

### Step 2 — Find Zeroes

Traverse the complete matrix.

If:

```java
matrix[i][j] == 0
```

mark:

```java
dummy1[i] = 1;
dummy2[j] = 1;
```

---

### Step 3 — Set Rows and Columns to Zero

Traverse the matrix again.

If:

```java
dummy1[i] == 1 || dummy2[j] == 1
```

set:

```java
matrix[i][j] = 0;
```

---

## ✅ Algorithm

1. Create an array for rows.
2. Create an array for columns.
3. Traverse the matrix.
4. Whenever a zero is found, mark its row and column.
5. Traverse the matrix again.
6. Set an element to zero if its row or column is marked.
7. Return the modified matrix.

---

## 📌 Example

### Input

```text
[
  [1, 2, 3],
  [4, 0, 6],
  [7, 8, 9]
]
```

The zero is at:

```text
matrix[1][1]
```

Therefore:

```text
dummy1 = [0, 1, 0]
dummy2 = [0, 1, 0]
```

Set row `1` and column `1` to zero.

### Output

```text
[
  [1, 0, 3],
  [0, 0, 0],
  [7, 0, 9]
]
```

---

## ⏱️ Time Complexity

**O(m × n)**

The matrix is traversed twice.

Where:

- `m` = number of rows
- `n` = number of columns

---

## 💾 Space Complexity

**O(m + n)**

We use:

```text
dummy1 → O(m)
dummy2 → O(n)
```

---

## 🧠 Key Learning

- Matrix traversal
- Two-dimensional arrays
- Row and column tracking
- In-place modification
- Avoiding modification during the first traversal

---

## 💻 Language

Java