Yes, exactly. You mean **the entire README must be inside ONE single code block**, so you press **Copy once** and get everything.

For **3069**, use this:

````markdown
# 3069. Distribute Elements Into Two Arrays I

## 🟢 Difficulty

Easy

---

## 🔗 Problem Link

https://leetcode.com/problems/distribute-elements-into-two-arrays-i/

---

## 🏷️ Tags

- Array
- Simulation
- ArrayList

---

## 📖 Problem Statement

You are given an integer array `nums`.

Create two arrays `arr1` and `arr2`.

- Put `nums[0]` into `arr1`.
- Put `nums[1]` into `arr2`.
- For every remaining element, compare the last elements of `arr1` and `arr2`.
- If the last element of `arr1` is greater than the last element of `arr2`, add the current element to `arr1`.
- Otherwise, add it to `arr2`.

Finally, concatenate `arr1` and `arr2` and return the resulting array.

---

## 💡 Intuition

This problem can be solved by directly simulating the given rules.

Use two `ArrayList<Integer>` objects to store the elements of `arr1` and `arr2`.

For every element starting from index `2`, compare the last elements of both lists:

```text
arr1 last element > arr2 last element
```

If true, add the element to `arr1`; otherwise, add it to `arr2`.

After processing all elements, combine both lists into the final result array.

---

## 🚀 Approach

1. Create two `ArrayList<Integer>` objects.
2. Add the first element of `nums` to `arr1`.
3. Add the second element to `arr2`.
4. Traverse the remaining elements.
5. Compare the last elements of `arr1` and `arr2`.
6. Add the current element to the appropriate list.
7. Create a result array of size `nums.length`.
8. Copy all elements of `arr1` followed by all elements of `arr2`.
9. Return the result.

---

## ✅ Algorithm

```text
1. Create arr1 and arr2.

2. Add nums[0] to arr1.
3. Add nums[1] to arr2.

4. For i = 2 to n - 1:

       If last element of arr1 > last element of arr2:
           Add nums[i] to arr1.
       Otherwise:
           Add nums[i] to arr2.

5. Create result array of size n.

6. Copy arr1 into result.

7. Copy arr2 into result.

8. Return result.
```

---

## 📌 Example

### Input

```text
nums = [5, 4, 3, 2, 1]
```

Initially:

```text
arr1 = [5]
arr2 = [4]
```

For `3`:

```text
5 > 4
```

So:

```text
arr1 = [5, 3]
arr2 = [4]
```

For `2`:

```text
3 > 4 → false
```

So:

```text
arr1 = [5, 3]
arr2 = [4, 2]
```

For `1`:

```text
3 > 2 → true
```

So:

```text
arr1 = [5, 3, 1]
arr2 = [4, 2]
```

Finally:

```text
arr1 + arr2
= [5, 3, 1, 4, 2]
```

### Output

```text
[5, 3, 1, 4, 2]
```

---

## ⏱️ Time Complexity

**O(n)**

Every element is processed once.

---

## 💾 Space Complexity

**O(n)**

The two lists and the result array store the elements.

---

## 🧠 Key Learning

### Pattern: Array + Simulation

This problem teaches how to simulate a set of rules using two dynamic arrays.

Useful Java operations:

```java
arr1.add(value);
arr2.add(value);
```

To access the last element:

```java
arr1.get(arr1.size() - 1);
arr2.get(arr2.size() - 1);
```

The overall pattern is:

```text
Initialize Two Arrays
        ↓
Process Each Element
        ↓
Compare Last Elements
        ↓
Add to Appropriate Array
        ↓
Combine Both Arrays
```

---

## 💻 Language

Java
````
