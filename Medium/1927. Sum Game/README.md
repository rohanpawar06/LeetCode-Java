# 1927. Sum Game

## 🟡 Difficulty

Medium

---

## 🔗 Problem Link

https://leetcode.com/problems/sum-game/

---

## 🏷️ Tags

- String
- Math
- Game Theory
- Simulation
- Greedy

---

## 📖 Problem Statement

You are given a string `num` containing digits and the character `?`.

The length of `num` is even.

The string is divided into two equal halves:

```text
Left Half | Right Half
```

Alice and Bob take turns replacing a `?` with a digit from `0` to `9`.

- Alice plays first.
- Alice wants the sum of the digits in the two halves to be **different**.
- Bob wants the sum of the digits in the two halves to be **equal**.

Return:

```text
true
```

if Alice can force a win.

Otherwise, return:

```text
false
```

---

## 💡 Intuition

Instead of trying every possible replacement of `?`, we only need to know:

1. The sum of the known digits in the left half.
2. The sum of the known digits in the right half.
3. The number of `?` in the left half.
4. The number of `?` in the right half.

We maintain:

```text
leftSum
rightSum
leftQ
rightQ
```

The important observation is that every `?` can be replaced by a digit from:

```text
0 to 9
```

Therefore, the maximum difference that one unmatched `?` can create is related to `9`.

---

## 🚀 Approach

### Step 1: Calculate the sums

Traverse the left and right halves separately.

For every normal digit:

```text
add it to the corresponding sum
```

For every `?`:

```text
increase the corresponding question-mark count
```

---

### Step 2: Check the total number of `?`

If the total number of `?` is odd:

```text
(leftQ + rightQ) % 2 == 1
```

Alice gets one extra move because Alice plays first.

Therefore, Alice can always force the sums to be different.

Return:

```text
true
```

---

### Step 3: Handle the even number of `?` case

When the number of `?` is even, the moves can be paired.

Bob can force the two sums to become equal only when:

```text
leftSum - rightSum
=
9 * (rightQ - leftQ) / 2
```

Therefore, Alice wins if this equality does **not** hold.

---

## 🔍 Algorithm

```text
1. Set:
       leftSum = 0
       rightSum = 0
       leftQ = 0
       rightQ = 0

2. Traverse the first half of the string.

       If character is '?':
           leftQ++

       Otherwise:
           leftSum += digit

3. Traverse the second half.

       If character is '?':
           rightQ++

       Otherwise:
           rightSum += digit

4. If the total number of '?' is odd:

       return true

5. Otherwise check:

       leftSum - rightSum
       ==
       9 * (rightQ - leftQ) / 2

6. If they are equal:

       Bob can force equal sums.
       return false

7. Otherwise:

       Alice can force different sums.
       return true
```

---

## 📌 Example 1

### Input

```text
num = "?3295???"
```

Split the string:

```text
?329 | 5???
```

### Left Half

```text
? 3 2 9
```

Known digit sum:

```text
3 + 2 + 9 = 14
```

So:

```text
leftSum = 14
leftQ = 1
```

### Right Half

```text
5 ? ? ?
```

Known digit sum:

```text
5
```

So:

```text
rightSum = 5
rightQ = 3
```

Now:

```text
leftSum - rightSum
= 14 - 5
= 9
```

And:

```text
9 * (rightQ - leftQ) / 2

= 9 * (3 - 1) / 2

= 9
```

The values are equal:

```text
9 == 9
```

Therefore Bob can force equality.

### Output

```text
false
```

---

## 📌 Example 2

### Input

```text
num = "5023"
```

There are no `?`.

Left half:

```text
50
```

Sum:

```text
5
```

Right half:

```text
23
```

Sum:

```text
5
```

The sums are already equal and there are no moves available.

Therefore Alice cannot make them different.

### Output

```text
false
```

---

## 📌 Example 3

### Input

```text
num = "??12"
```

Split:

```text
?? | 12
```

We have:

```text
leftQ = 2
rightQ = 0
```

Total number of `?`:

```text
2
```

which is even.

Known sums:

```text
leftSum = 0
rightSum = 3
```

Check:

```text
leftSum - rightSum
= -3
```

and:

```text
9 * (rightQ - leftQ) / 2
= 9 * (0 - 2) / 2
= -9
```

They are not equal.

Therefore Alice can force the sums to be different.

### Output

```text
true
```

---

## 🧠 Key Learning

### Pattern: Game Theory + Mathematical Observation

The main idea is to avoid simulating every possible move.

Instead, reduce the problem to four values:

```text
leftSum
rightSum
leftQ
rightQ
```

Then use the mathematical relationship between:

```text
Current sum difference
```

and:

```text
Difference in the number of '?'
```

The key condition is:

```text
Odd number of '?' → Alice wins

Even number of '?' →
    if

    leftSum - rightSum
    ==
    9 * (rightQ - leftQ) / 2

    Bob wins

    otherwise
    Alice wins
```

---

## ⚠️ Important Detail

Do **not** use:

```java
Math.abs(leftSum - rightSum)
```

for the final condition.

The sign is important because we need to know **which half has the larger sum** and **which half has more `?` characters**.

Therefore, use:

```java
leftSum - rightSum
```

and:

```java
rightQ - leftQ
```

directly.

---

## 💻 Java Solution

```java
class Solution {
    public boolean sumGame(String num) {
        int n = num.length();

        int leftSum = 0;
        int rightSum = 0;
        int leftQ = 0;
        int rightQ = 0;

        for (int i = 0; i < n / 2; i++) {
            if (num.charAt(i) == '?') {
                leftQ++;
            } else {
                leftSum += num.charAt(i) - '0';
            }
        }

        for (int i = n / 2; i < n; i++) {
            if (num.charAt(i) == '?') {
                rightQ++;
            } else {
                rightSum += num.charAt(i) - '0';
            }
        }

        if ((leftQ + rightQ) % 2 == 1) {
            return true;
        }

        return leftSum - rightSum != 9 * (rightQ - leftQ) / 2;
    }
}
```

---

## ⏱️ Time Complexity

**O(n)**

We traverse the string once.

---

## 💾 Space Complexity

**O(1)**

Only a few integer variables are used.

---

## 💻 Language

Java