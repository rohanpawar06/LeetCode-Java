# 2. Add Two Numbers

## 🟡 Difficulty

Medium

---

## 🔗 Problem Link

https://leetcode.com/problems/add-two-numbers/

---

## 🏷️ Tags

- Linked List
- Math
- Recursion
- Simulation

---

## 📖 Problem Statement

You are given two non-empty linked lists representing two non-negative integers.

The digits are stored in reverse order, and each node contains a single digit.

Add the two numbers and return the result as a linked list.

The digits in the result should also be stored in reverse order.

### Example

    l1 = [2,4,3]
    l2 = [5,6,4]

These represent:

    342 + 465 = 807

Therefore, the result is:

    [7,0,8]

---

## 💡 Intuition

This problem is similar to normal addition.

For example:

      342
    + 465
    -----
      807

We start from the rightmost digit.

First:

    2 + 5 = 7

Then:

    4 + 6 = 10

So we store `0` and carry `1`.

Then:

    3 + 4 + 1 = 8

The linked lists already store the digits in reverse order:

    2 → 4 → 3
    5 → 6 → 4

Therefore, we can traverse both lists from beginning to end.

The important concept is handling the carry.

---

## 🚀 Approach

1. Create a dummy node to simplify the creation of the result list.
2. Maintain a pointer `current` for the result list.
3. Maintain a `carry` variable.
4. Traverse both linked lists while at least one list still has nodes or a carry exists.
5. Add the values of the current nodes.
6. Add the previous `carry`.
7. Calculate the new digit using `sum % 10`.
8. Calculate the new carry using `sum / 10`.
9. Create a new node with the calculated digit.
10. Move to the next nodes.
11. Return `dummy.next`.

---

## 🔍 Algorithm

    1. Create:
           dummy = new ListNode(0)
           current = dummy
           carry = 0

    2. While:
           l1 != null OR l2 != null OR carry != 0

    3. Set:
           sum = carry

    4. If l1 exists:
           sum += l1.val
           move l1 to next

    5. If l2 exists:
           sum += l2.val
           move l2 to next

    6. Calculate:
           carry = sum / 10
           digit = sum % 10

    7. Create a new node using digit.

    8. Attach the node to the result.

    9. Move current forward.

    10. Return:
            dummy.next

---

## 📌 Example

### Input

    l1 = [2,4,3]
    l2 = [5,6,4]

### Step 1

    2 + 5 = 7

Result:

    [7]

Carry:

    0

### Step 2

    4 + 6 = 10

Digit:

    10 % 10 = 0

Carry:

    10 / 10 = 1

Result:

    [7,0]

### Step 3

    3 + 4 + 1 = 8

Result:

    [7,0,8]

Therefore:

    342 + 465 = 807

Output:

    [7,0,8]

---

## 📌 Important Edge Case

Consider:

    l1 = [9]
    l2 = [1]

Addition:

    9 + 1 = 10

Therefore:

    digit = 0
    carry = 1

The lists are now finished, but `carry` is still `1`.

Therefore, the loop must also continue when:

    carry != 0

Result:

    [0,1]

This is why the loop is:

    while (l1 != null || l2 != null || carry != 0)

---

## 💻 Java Solution

    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

            ListNode dummy = new ListNode(0);
            ListNode current = dummy;

            int carry = 0;

            while (l1 != null || l2 != null || carry != 0) {

                int sum = carry;

                if (l1 != null) {
                    sum += l1.val;
                    l1 = l1.next;
                }

                if (l2 != null) {
                    sum += l2.val;
                    l2 = l2.next;
                }

                carry = sum / 10;

                current.next = new ListNode(sum % 10);
                current = current.next;
            }

            return dummy.next;
        }
    }

---

## 🧠 Key Learning

### Pattern: Linked List + Carry Simulation

The main pattern is:

    Traverse both linked lists
            ↓
    Add corresponding digits
            ↓
    Add carry
            ↓
    Store sum % 10
            ↓
    Update carry using sum / 10
            ↓
    Create next node

The two most important operations are:

    sum % 10 → Current digit
    sum / 10 → Carry

For example:

    10 % 10 = 0
    10 / 10 = 1

The dummy node is also useful because it makes result-list construction easier and avoids special handling for the first node.

---

## ⏱️ Time Complexity

O(max(m, n))

Where:

- `m` = length of `l1`
- `n` = length of `l2`

Each node is processed once.

---

## 💾 Space Complexity

O(max(m, n))

The result linked list requires space for the output.

The algorithm itself uses O(1) extra working space apart from the result list.

---

## 💻 Language

Java