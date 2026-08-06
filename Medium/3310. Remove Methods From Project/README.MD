#   

## 🟡 Difficulty

Medium

---

## 🔗 Problem Link

https://leetcode.com/problems/remove-methods-from-project/

---

## 🏷️ Tags

- Graph
- DFS
- BFS
- Directed Graph
- Traversal

---

## 📖 Problem Statement

You are given `n` methods numbered from `0` to `n - 1` and a list of method invocations.

A bug is found in method `k`.

Any method that is directly or indirectly invoked by method `k` becomes **suspicious**.

You can remove all suspicious methods **only if no non-suspicious method invokes any suspicious method**.

Return the list of remaining methods after removing the suspicious ones. If removal is not possible, return all methods.

---

## 💡 Intuition

Starting from the buggy method `k`, every reachable method becomes suspicious.

After identifying all suspicious methods, verify whether any non-suspicious method depends on a suspicious one.

- If such a dependency exists, removing suspicious methods would break the project.
- Otherwise, remove all suspicious methods and return the remaining methods.

---

## 🚀 Approach

1. Build the directed graph using an adjacency list.
2. Perform DFS from method `k` to mark all suspicious methods.
3. Traverse every invocation.
4. If a non-suspicious method invokes a suspicious method, return all methods.
5. Otherwise, return only the non-suspicious methods.

---

## ✅ Algorithm

1. Create an adjacency list.
2. Run DFS from `k`.
3. Mark every reachable node as suspicious.
4. Check every edge:
   - If `from` is non-suspicious and `to` is suspicious, return all methods.
5. Otherwise, collect and return all non-suspicious methods.

---

## ⏱️ Time Complexity

**O(n + m)**

- `n` = Number of methods
- `m` = Number of invocations

---

## 💾 Space Complexity

**O(n + m)**

- Adjacency List
- Visited Array
- DFS Recursion Stack

---

## 📌 Example

### Input

```text
n = 5
k = 0

invocations = [
    [0,1],
    [1,2]
]
```

### Output

```text
[3,4]
```

### Explanation

Suspicious methods are:

```text
0 → 1 → 2
```

Methods `3` and `4` are independent and can remain in the project.

---

## 🧠 Key Learning

- Represent dependencies using a directed graph.
- Use DFS/BFS to find all reachable nodes.
- Validate graph dependencies before removing nodes.
- Graph reachability is a common interview pattern.

---

## 💻 Language

Java