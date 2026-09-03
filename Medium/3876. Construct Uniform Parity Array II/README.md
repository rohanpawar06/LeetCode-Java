````markdown
# 3876. Construct Uniform Parity Array II

## 🟡 Difficulty

Medium

---

## 🔗 Problem Link

https://leetcode.com/problems/construct-uniform-parity-array-ii/

---

## 🏷️ Tags

- Array
- Math
- Parity
- Greedy

---

## 📖 Problem Statement

You are given an array `nums1` of positive integers.

You need to construct an array `nums2` of the same length such that all elements of `nums2` have the same parity.

That means either:

```text
All elements are even
```

or:

```text
All elements are odd
```

For every index `i`, you can choose either:

```text
nums2[i] = nums1[i]
```

or choose another index `j != i` such that:

```text
nums1[i] - nums1[j] >= 1
```

and set:

```text
nums2[i] = nums1[i] - nums1[j]
```

Return `true` if it is possible to construct such an array. Otherwise, return `false`.

---

## 💡 Intuition

The most important observation is that we only care about **parity**.

Recall:

```text
Even - Even = Even
Odd - Odd = Even

Even - Odd = Odd
Odd - Even = Odd
```

So subtracting an odd number from an even number changes its parity:

```text
Even - Odd = Odd
```

The key question is:

> Can every even number be changed into an odd number?

To do this, we need an odd number smaller than every even number.

Therefore, we find the **smallest odd number**.

Let:

```text
minOdd = smallest odd number
```

If every even number is greater than `minOdd`, then we can subtract `minOdd` from every even number:

```text
Even - minOdd = Odd
```

The odd numbers can simply remain unchanged.

Therefore, all elements can become odd.

---

## 🔥 Important Observation

Suppose:

```text
nums1 = [1, 4, 7]
```

The smallest odd number is:

```text
minOdd = 1
```

Now:

```text
1 → Odd
4 - 1 = 3 → Odd
7 → Odd
```

So we can construct:

```text
[1, 3, 7]
```

All elements are odd.

Therefore:

```text
true
```

---

## ❌ When Is It Impossible?

Consider:

```text
nums1 = [2, 3, 5]
```

The smallest odd number is:

```text
minOdd = 3
```

But:

```text
2 < 3
```

The number `2` cannot subtract `3` because:

```text
2 - 3 = -1
```

The result must be positive.

Therefore, `2` cannot be changed into an odd number.

It must remain:

```text
2 → Even
```

while:

```text
3 → Odd
5 → Odd
```

So we cannot make all elements have the same parity.

Therefore:

```text
false
```

---

## 🚀 Approach

1. Find the smallest odd number in `nums1`.
2. Store it in `minOdd`.
3. Traverse the array again.
4. If an even number is smaller than `minOdd`, return `false`.
5. Otherwise return `true`.

There is one special case:

If there is no odd number at all, then all elements are already even.

Therefore, the answer is:

```text
true
```

---

## 🔍 Algorithm

```text
1. Set:
       minOdd = infinity

2. Traverse nums1.

3. For every odd number:
       update minOdd

4. Traverse nums1 again.

5. If nums1[i] is even AND
   nums1[i] < minOdd:

       return false

6. Otherwise:

       return true
```

---

## 📌 Example 1

### Input

```text
nums1 = [1, 4, 7]
```

### Step 1: Find Smallest Odd

Odd numbers:

```text
1
7
```

Therefore:

```text
minOdd = 1
```

### Step 2: Check Even Numbers

The only even number is:

```text
4
```

Since:

```text
4 > 1
```

we can subtract `1`:

```text
4 - 1 = 3
```

Now:

```text
1 → Odd
3 → Odd
7 → Odd
```

### Output

```text
true
```

---

## 📌 Example 2

### Input

```text
nums1 = [2, 3]
```

### Step 1

Smallest odd:

```text
minOdd = 3
```

### Step 2

Check the even number:

```text
2
```

We have:

```text
2 < 3
```

So `2` cannot subtract `3`:

```text
2 - 3 = -1
```

Therefore, it cannot become odd.

### Output

```text
false
```

---

## 📌 Example 3

### Input

```text
nums1 = [4, 6, 8]
```

There are no odd numbers.

But all elements are already even:

```text
4 → Even
6 → Even
8 → Even
```

So we don't need to perform any operation.

### Output

```text
true
```

---

## 🧪 Dry Run

Consider:

```text
nums1 = [2, 5, 8, 11]
```

### Find odd numbers

```text
5
11
```

Smallest odd:

```text
minOdd = 5
```

Now check every even number.

### Number 2

```text
2 < 5
```

This is an even number smaller than the smallest odd.

Therefore:

```text
return false
```

We don't need to check the remaining elements.

---

## 🧪 Another Dry Run

Consider:

```text
nums1 = [3, 6, 8, 10]
```

Smallest odd:

```text
minOdd = 3
```

Check:

```text
6 > 3
8 > 3
10 > 3
```

Every even number is greater than the smallest odd.

Therefore:

```text
6 - 3 = 3 → Odd
8 - 3 = 5 → Odd
10 - 3 = 7 → Odd
```

The original odd number:

```text
3 → Odd
```

So we can construct:

```text
[3, 3, 5, 7]
```

All elements are odd.

Therefore:

```text
true
```

---

## 💻 Java Solution

```java
class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;

        for (int num : nums1) {
            if (num % 2 == 1) {
                minOdd = Math.min(minOdd, num);
            }
        }

        for (int num : nums1) {
            if (num % 2 == 0 && minOdd != Integer.MAX_VALUE && num < minOdd) {
                return false;
            }
        }

        return true;
    }
}
```

---

## 🧠 Code Explanation

### 1. Find the Smallest Odd Number

```java
int minOdd = Integer.MAX_VALUE;
```

Initially, we don't know any odd number.

Then:

```java
for (int num : nums1) {
    if (num % 2 == 1) {
        minOdd = Math.min(minOdd, num);
    }
}
```

The condition:

```java
num % 2 == 1
```

checks whether `num` is odd.

For example:

```text
5 % 2 = 1
```

Therefore `5` is odd.

---

### 2. Check Even Numbers

```java
for (int num : nums1) {
```

We traverse the array again.

Then:

```java
num % 2 == 0
```

checks whether the number is even.

---

### 3. Check Whether the Even Number Is Too Small

```java
num < minOdd
```

If an even number is smaller than the smallest odd number, it cannot subtract that odd number while keeping the result positive.

For example:

```text
num = 2
minOdd = 3
```

Then:

```text
2 - 3 = -1
```

Not allowed.

Therefore:

```java
return false;
```

---

## 🧠 Why Don't We Need to Actually Construct `nums2`?

This is the clever part.

We don't need to perform the operations.

We only need to determine whether a valid construction exists.

If there is no even number smaller than the smallest odd number, then:

```text
Every even number > smallest odd
```

Therefore every even number can subtract the smallest odd:

```text
Even - Odd = Odd
```

All original odd numbers can remain unchanged.

So everything becomes odd.

Thus:

```text
true
```

---

## ⭐ Main Rule to Remember

The entire problem can be reduced to:

```text
Find smallest ODD
       ↓
Check every EVEN
       ↓
Is EVEN < smallest ODD?
       ↓
   YES        NO
    ↓          ↓
  false       true
```

---

## 🧠 Important Parity Rules

Remember these:

```text
Even - Even = Even

Odd - Odd = Even

Even - Odd = Odd

Odd - Even = Odd
```

The important one for this problem is:

```text
Even - Odd = Odd
```

---

## ⚠️ Common Mistakes

### Mistake 1: Only Counting Odd Numbers

Simply counting odd numbers is not enough.

The actual values matter because the subtraction result must be positive.

For example:

```text
[2, 3]
```

There is one odd number and one even number, but `2` cannot subtract `3`.

---

### Mistake 2: Ignoring the Positive Result Condition

You cannot do:

```text
2 - 3 = -1
```

because the problem requires:

```text
nums1[i] - nums1[j] >= 1
```

---

### Mistake 3: Trying to Build Every Possible Array

That is unnecessary.

The smallest odd number gives us enough information to determine whether a valid construction exists.

---

## ⏱️ Time Complexity

We traverse the array twice.

```text
O(n)
```

where `n` is the length of `nums1`.

---

## 💾 Space Complexity

We only use one variable:

```text
minOdd
```

Therefore:

```text
O(1)
```

---

## 🎯 Interview Takeaway

When you see:

```text
Uniform Parity
+
Subtraction
+
Positive Result
```

Think:

```text
Find smallest odd
        ↓
Can every even number subtract it?
        ↓
If an even number is smaller
        ↓
Impossible
```

The final condition is:

```java
return noEvenNumberIsSmallerThanMinOdd;
```

---

## 💻 Language

Java
```
