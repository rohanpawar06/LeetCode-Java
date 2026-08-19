# 1386. Cinema Seat Allocation

## 🟡 Difficulty

Medium

---

## 🔗 Problem Link

https://leetcode.com/problems/cinema-seat-allocation/

---

## 🏷️ Tags

- Array
- HashMap
- Bit Manipulation
- Greedy
- Simulation

---

## 📖 Problem Statement

A cinema has `n` rows, and each row contains 10 seats.

A family needs exactly 4 consecutive seats.

Some seats are already reserved. We need to find the maximum number of families that can be seated without using any reserved seat.

The possible groups of four seats are:

```text
[2,3,4,5]
[4,5,6,7]
[6,7,8,9]
```

A row can accommodate at most two families by using:

```text
[2,3,4,5] and [6,7,8,9]
```

---

## 💡 Intuition

Rows without any reserved seats can always accommodate `2` families.

Therefore, instead of checking every row, we only need to process the rows that contain reserved seats.

For each affected row, we check the three possible blocks:

```text
Left   → [2,3,4,5]
Middle → [4,5,6,7]
Right  → [6,7,8,9]
```

A reserved seat can make one or more of these blocks unavailable.

We use a `HashMap` to store the blocked blocks for each affected row using a bitmask.

---

## 🚀 Approach

1. Create a `HashMap` to store information for rows containing reserved seats.
2. For every reserved seat, determine which family blocks it affects.
3. Represent the blocked blocks using three bits.
4. Every row that is not in the `HashMap` can accommodate `2` families.
5. For each affected row:
   - If both left and right blocks are available, add `2`.
   - Otherwise, if at least one block is available, add `1`.
   - Otherwise, add `0`.
6. Return the total number of families.

---

## 🔍 Algorithm

1. Create a `HashMap<Integer, Integer>`.
2. For every reserved seat:
   - If the seat is between `2` and `5`, mark the left block.
   - If the seat is between `4` and `7`, mark the middle block.
   - If the seat is between `6` and `9`, mark the right block.
3. Initialize:

```text
answer = (n - affectedRows) * 2
```

4. For every affected row:
   - If left and right blocks are both available, add `2`.
   - Otherwise, if left, middle, or right is available, add `1`.
5. Return `answer`.

---

## 📌 Example

### Input

```text
n = 3
reservedSeats = [[1,2],[1,3],[1,8],[2,6]]
```

For row `1`:

```text
[2,3,4,5] → blocked
[4,5,6,7] → available
[6,7,8,9] → blocked
```

So row `1` can accommodate:

```text
1 family
```

For row `2`:

```text
[2,3,4,5] → available
[4,5,6,7] → blocked
[6,7,8,9] → blocked
```

So row `2` can accommodate:

```text
1 family
```

Row `3` has no reserved seats, so it can accommodate:

```text
2 families
```

Therefore:

```text
1 + 1 + 2 = 4
```

### Output

```text
4
```

---

## 🧠 Key Learning

### Pattern: HashMap + Bitmask

The important observation is that we do not need to process every seat or every row.

Only rows containing reserved seats need special handling.

The three possible family blocks can be represented using three bits:

```text
Bit 0 → [2,3,4,5]
Bit 1 → [4,5,6,7]
Bit 2 → [6,7,8,9]
```

This allows us to efficiently track which blocks are affected by reserved seats.

The main pattern is:

```text
Reserved Seats
      ↓
Group by Row
      ↓
Create Bitmask
      ↓
Check Available Blocks
      ↓
Calculate Maximum Families
```

---

## ⏱️ Time Complexity

**O(m)**

where `m` is the number of reserved seats.

Each reserved seat is processed once.

---

## 💾 Space Complexity

**O(m)**

The `HashMap` stores information only for rows containing reserved seats.

---

## 💻 Language

Java