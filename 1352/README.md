# 1352. Product of the Last K Numbers

**Difficulty:** Medium

**Problem Link:** https://leetcode.com/problems/product-of-the-last-k-numbers/

**Tags:** Array, Design, Data Stream, Prefix Product

## Problem Statement

Design a class that supports adding numbers to a sequence and retrieving the product of the last `k` numbers. Implement the `ProductOfNumbers` class with an `add(int num)` method and a `getProduct(int k)` method.

## Intuition

Prefix products allow the product of a range to be calculated using division. However, a zero makes division impossible and causes every product containing it to be zero. We can handle this by resetting the prefix product list whenever zero is added.

The list always starts with 1, which acts as the identity value for multiplication.

## Approach

- Maintain a list of prefix products since the most recent zero.
- Initially, store 1 in the list.
- When a nonzero number is added, multiply it by the previous prefix product and append the result.
- When zero is added, reset the list to contain only 1.
- To get the product of the last `k` numbers:
  - If `k` is greater than or equal to the current prefix list size, the requested range includes a zero, so return 0.
  - Otherwise, divide the latest prefix product by the prefix product immediately before the requested range.

## Algorithm

1. Initialize the prefix list with 1.
2. In `add(num)`:
   - If `num == 0`, clear the list and add 1.
   - Otherwise, append the previous prefix product multiplied by `num`.
3. In `getProduct(k)`:
   - Let `n` be the prefix list size.
   - If `k >= n`, return 0.
   - Otherwise, return `prefix[n - 1] / prefix[n - 1 - k]`.

## Example

**Operations:**

```text
add(3)
add(0)
add(2)
add(5)
add(4)
getProduct(2) -> 20
getProduct(3) -> 40
getProduct(4) -> 0
```

The last four numbers include the zero, so their product is 0.

## Key Learning

Prefix products can answer range-product queries in O(1) time. Resetting the prefix state at zero avoids division by zero and keeps all stored products limited to the current zero-free suffix.

## Complexity Analysis

- **Time Complexity:** O(1) for both `add(num)` and `getProduct(k)`.
- **Space Complexity:** O(n) for the prefix products stored since the most recent zero, where n is the number of values in that zero-free suffix.

## Language

Java
