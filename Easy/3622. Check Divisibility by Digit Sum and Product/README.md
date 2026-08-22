# 3622. Check Divisibility by Digit Sum and Product

## 🟢 Difficulty

Easy

---

## 🔗 Problem Link

https://leetcode.com/problems/check-divisibility-by-digit-sum-and-product/

---

## 🏷️ Tags

- Math
- Simulation
- Digit Manipulation
- Modulo

---

## 📖 Problem Statement

You are given a positive integer `n`.

Calculate:

- The sum of all digits of `n`.
- The product of all digits of `n`.

Then check whether `n` is divisible by the sum of the digit sum and digit product.

Return `true` if:

```text
n % (digitSum + digitProduct) == 0
```

Otherwise, return `false`.

---

## 💡 Intuition

We can extract every digit of a number using:

```text
digit = n % 10
n = n / 10
```

For example:

```text
n = 99
```

The digits are:

```text
9, 9
```

Digit sum:

```text
9 + 9 = 18
```

Digit product:

```text
9 × 9 = 81
```

Now:

```text
18 + 81 = 99
```

Since:

```text
99 % 99 = 0
```

the answer is:

```text
true
```

We maintain two variables:

```text
sum = 0
product = 1
```

The product starts with `1` because multiplying by `1` does not change the result.

---

## 🚀 Approach

1. Store the original value of `n`.
2. Initialize:
   ```text
   sum = 0
   product = 1
   ```
3. Extract the last digit using `% 10`.
4. Add the digit to `sum`.
5. Multiply the digit with `product`.
6. Remove the last digit using `/ 10`.
7. Repeat until all digits are processed.
8. Calculate:
   ```text
   sum + product
   ```
9. Check whether the original `n` is divisible by this value.
10. Return `true` or `false`.

---

## 🔍 Algorithm

```text
1. sum = 0
2. product = 1
3. temp = n

4. While temp > 0:

       digit = temp % 10

       sum = sum + digit

       product = product * digit

       temp = temp / 10

5. divisor = sum + product

6. If n % divisor == 0:
       return true

7. Otherwise:
       return false
```

---

## 📌 Example

### Input

```text
n = 99
```

### Step 1: Calculate Digit Sum

```text
9 + 9 = 18
```

### Step 2: Calculate Digit Product

```text
9 × 9 = 81
```

### Step 3: Calculate Sum of Sum and Product

```text
18 + 81 = 99
```

### Step 4: Check Divisibility

```text
99 % 99 = 0
```

### Output

```text
true
```

---

## 📌 Example 2

### Input

```text
n = 23
```

Digit sum:

```text
2 + 3 = 5
```

Digit product:

```text
2 × 3 = 6
```

Sum:

```text
5 + 6 = 11
```

Check:

```text
23 % 11 != 0
```

### Output

```text
false
```

---

## 🧠 Key Learning

### Pattern: Digit Manipulation + Simulation

The main pattern is:

```text
Number
   ↓
Extract last digit using % 10
   ↓
Process the digit
   ↓
Remove last digit using / 10
   ↓
Repeat
```

Important operations:

```text
n % 10 → Get the last digit
n / 10 → Remove the last digit
```

This pattern is useful for many number and digit problems such as:

- Digit Sum
- Digit Product
- Reverse Number
- Palindrome Number
- Counting Digits
- Checking Digit Properties

---

## ⏱️ Time Complexity

**O(log n)**

If `n` contains `d` digits, every digit is processed exactly once.

Therefore:

```text
O(d)
```

which is `O(log n)` with respect to the value of `n`.

---

## 💾 Space Complexity

**O(1)**

Only a few variables are used.

---

## 💻 Language

Java